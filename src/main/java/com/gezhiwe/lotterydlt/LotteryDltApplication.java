package com.gezhiwe.lotterydlt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LotteryDltApplication {

    public static void main(String[] args) {
        SpringApplication.run(LotteryDltApplication.class, args);
    }
}
