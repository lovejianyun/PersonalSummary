package com.qijy.times;

import java.util.Calendar;

public class TimeTest2 {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        long nowTime = cal.getTimeInMillis();
        cal.add(Calendar.DAY_OF_YEAR,1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 3);
        cal.set(Calendar.MILLISECOND, 0);
        long timeInMillis = cal.getTimeInMillis();
        System.out.println((timeInMillis - nowTime) / (1000 * 60));

        String str = "[]";
        int i = str.lastIndexOf(",");
        System.out.println(i);
    }
}
