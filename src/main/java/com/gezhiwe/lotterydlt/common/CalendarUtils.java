package com.gezhiwe.lotterydlt.common;

import java.util.Calendar;
import java.util.Date;

public class CalendarUtils {


    public static Date parseStandardOpenday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        return calendar.getTime();
    }

    public static Date parseMaxOpenDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        return calendar.getTime();
    }
}
