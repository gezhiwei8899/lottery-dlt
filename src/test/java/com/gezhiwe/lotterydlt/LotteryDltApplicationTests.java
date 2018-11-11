package com.gezhiwe.lotterydlt;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LotteryDltApplicationTests {

    private static final String REX_IP = "^(((\\d{1,2})|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d{1,2})|(1\\d{2})|(2[0-4]\\d)|(25[0-5])):[0-9]+$";

    @Test
    public void contextLoads() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse execute = httpClient.execute(new HttpGet("http://www.66ip.cn/nmtq.php?getnum=&isp=0&anonymoustype=0&start=&ports=&export=&ipaddress=&area=0&proxytype=2&api=66ip"));
        String html = EntityUtils.toString(execute.getEntity(), "utf-8");
//		System.out.println(html);
        Document doc = Jsoup.parse(html);
        Element body = doc.body();
        String ips = body.toString().replaceAll(" ", "").replaceAll("<br>", "").replaceAll("<body>", "");
        String substring = ips.substring(0, ips.indexOf("<scripttype"));
        String trim = substring.trim();
        System.out.println("----------------------------------------------------------------------");
        System.out.println(trim);
        System.out.println("----------------------------------------------------------------------");

        String[] split = trim.split("\n");
        for (String s : split) {
            System.out.println(s.trim());
        }
    }

}
