package com.gezhiwe.lotterydlt.service.impl;

import com.gezhiwe.lotterydlt.service.ProxyService;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Service
public class ProxyServiceImpl implements ProxyService {

    private static final BlockingQueue<HttpHost> HTTP_HOSTS = new ArrayBlockingQueue<>(100);

    @Override
    public HttpHost getProxy() {
        HTTP_HOSTS.clear();
        HttpHost httpHost = null;
        while (null == httpHost) {
            httpHost = pollHost();
        }
        return httpHost;
    }

    private HttpHost pollHost() {
        try {
            if (HTTP_HOSTS.isEmpty()) {
                fetchHosts();
            }
            HttpHost poll = HTTP_HOSTS.poll();
            Socket socket = new Socket(poll.getHostName(), poll.getPort());
            if (socket.isConnected()) {
                System.out.println("当前ip成功 " + socket.getInetAddress() + " " + socket.getPort());
                socket.close();
                return poll;
            }
        } catch (Exception e) {
        }
        return null;
    }

    private void fetchHosts() {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse execute = httpClient.execute(new HttpGet("http://www.66ip.cn/nmtq.php?getnum=&isp=0&anonymoustype=0&start=&ports=&export=&ipaddress=&area=0&proxytype=2&api=66ip"));
            String html = EntityUtils.toString(execute.getEntity(), "utf-8");
//		System.out.println(html);
            Document doc = Jsoup.parse(html);
            Element body = doc.body();
            String ips = body.toString().replaceAll(" ", "").replaceAll("<br>", "").replaceAll("<body>", "");
            String substring = ips.substring(0, ips.indexOf("<scripttype"));
            String trim = substring.trim();

            String[] split = trim.split("\n");
            for (String host : split) {
                HTTP_HOSTS.offer(createHost(host));
            }
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    private HttpHost createHost(String host) throws Exception {
        String[] split = host.split(":");
        HttpHost httpHost = new HttpHost(split[0], Integer.parseInt(split[1]));
        return httpHost;
    }
}
