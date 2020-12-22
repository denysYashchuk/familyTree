package com.example.family_tree.util;

import java.util.Calendar;
import java.util.Date;

/*
 * Written by Denys Yashchuk denys.yashchuk@gmail.com, Dec 2020
 */
public class DateUtil {

    public static Date getCurrentYearStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }

    public static Date getStartOfYearAgo(int ago) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - ago);
        return calendar.getTime();
    }

    public static Date getZeroYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.YEAR, 0);
        return calendar.getTime();
    }

}
