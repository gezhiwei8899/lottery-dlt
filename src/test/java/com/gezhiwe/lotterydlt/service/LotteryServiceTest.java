package com.gezhiwe.lotterydlt.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LotteryServiceTest {

    @Autowired
    private LotteryService lotteryService;

    @Test
    public void exsitLotteryNumInSchedule() {
    }

    @Test
    public void saveNewLotteryNum() {
        lotteryService.saveNewLotteryNum();
    }
}