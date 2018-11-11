package com.gezhiwe.lotterydlt.schedule;

import com.gezhiwe.lotterydlt.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class ScheduleTask {

    @Autowired
    private LotteryService lotteryService;

    private static final List<Integer> INTEGERS = new ArrayList<>(3);

    static {
        INTEGERS.add(2);
        INTEGERS.add(4);
        INTEGERS.add(7);
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void getLotteryDlt() {
        System.out.println("开始循环了");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = calendar.get(Calendar.DAY_OF_WEEK);
        if (!INTEGERS.contains(i)) {
            System.out.println("今天不是开奖日期");
            return;
        }

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        if (hour < 20 || (hour == 20 && min < 35)) {
            System.out.println("开奖时间未到");
            return;
        }

        if (lotteryService.exsitLotteryNumInSchedule(date)) {
            System.out.println("已经记录开奖号码");
            return;
        }

        lotteryService.saveNewLotteryNum();

    }
}
