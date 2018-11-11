package com.gezhiwe.lotterydlt.service.impl;

import com.gezhiwe.lotterydlt.common.CalendarUtils;
import com.gezhiwe.lotterydlt.dao.cmapper.LotteryNumCMapper;
import com.gezhiwe.lotterydlt.dao.entity.LotteryNum;
import com.gezhiwe.lotterydlt.service.LotteryService;
import com.gezhiwe.lotterydlt.service.ProxyService;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class LotteryServiceImpl implements LotteryService {

    private static final String CSS_QUERY_QICI = "#dlt > a > h1 > em:nth-child(1)";
    private static final String CSS_QUERY_OPEN_DAY = "#dlt > a > h1 > em:nth-child(2)";
    private static final String CSS_QUERY_LOTTERY_NUM = "#dlt > a > div > ul";

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private LotteryNumCMapper lotteryNumCMapper;
    @Autowired
    private ProxyService proxyService;

    @Override
    public boolean exsitLotteryNumInSchedule(Date date) {
        Date standartOpenDay = CalendarUtils.parseStandardOpenday(date);
        Date maxOPenDay = CalendarUtils.parseMaxOpenDay(date);
        Long count = lotteryNumCMapper.exsitLotteryNumInSchedule(standartOpenDay, maxOPenDay);
        return count > 0;
    }

    @Override
    public void saveNewLotteryNum() {
        try {
            //https://m.500.com/info/kaijiang/
            HttpHost target = new HttpHost("m.500.com", 443, "https");
            //获取代理
            HttpHost proxy = proxyService.getProxy();
            System.out.println("使用 " + proxy.getHostName() + " " + proxy.getPort() + " 进行http代理");
            RequestConfig config = RequestConfig.custom()
                    .setProxy(proxy)
                    .build();
            HttpGet request = new HttpGet("/info/kaijiang/");
            request.setConfig(config);

            CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

            CloseableHttpResponse response = closeableHttpClient.execute(target, request);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                System.out.println("代理失败，重新走一遍");
                saveNewLotteryNum();
            }
            handler(EntityUtils.toString(response.getEntity()));


        } catch (Exception e) {

        }
    }

    private void handler(String html) {
        LotteryNum lotteryNum = new LotteryNum();

        Document root = Jsoup.parse(html);
        Elements qiciNode = root.select(CSS_QUERY_QICI);
        for (Element element : qiciNode) {
            String qici = element.text().trim();
            String num = qici.replaceAll("期", "");
            lotteryNum.setOpenNum(Long.parseLong(num));
        }
        lotteryNum.setOpenDay(new Date());
        Elements elements = root.select(CSS_QUERY_LOTTERY_NUM);
        for (Element element : elements) {
            String nums = element.text().trim();
            String[] numss = nums.split(" ");
            for (int i = 0; i < numss.length; i++) {
                switch (i) {
                    case 0:
                        lotteryNum.setFirstNum(Integer.valueOf(numss[i]));
                        break;
                    case 1:
                        lotteryNum.setSecondNum(Integer.valueOf(numss[i]));
                        break;
                    case 2:
                        lotteryNum.setThirdNum(Integer.valueOf(numss[i]));
                        break;
                    case 3:
                        lotteryNum.setFourthNum(Integer.valueOf(numss[i]));
                        break;
                    case 4:
                        lotteryNum.setFifthNum(Integer.valueOf(numss[i]));
                        break;
                    case 5:
                        lotteryNum.setSixthNum(Integer.valueOf(numss[i]));
                        break;
                    case 6:
                        lotteryNum.setSeventhNum(Integer.valueOf(numss[i]));
                        break;
                }
            }
        }
        if (!selectExsit(lotteryNum.getOpenNum())) {

            lotteryNumCMapper.insert(lotteryNum);
        }
    }

    private boolean selectExsit(Long openNum) {
        Long count = lotteryNumCMapper.selectExsitByNum(openNum);
        return count > 0;
    }

    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(new Date()));
    }
}