package com.gezhiwe.lotterydlt.service;

import java.util.Date;

public interface LotteryService {
    boolean exsitLotteryNumInSchedule(Date date);

    void saveNewLotteryNum();
}
