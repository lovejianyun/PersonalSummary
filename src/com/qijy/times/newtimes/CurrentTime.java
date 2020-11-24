package com.qijy.times.newtimes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentTime {
    public static void main(String[] args) throws Exception{
//        Date date = new Date();
//        long time = date.getTime();
//        System.out.println(time);
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String format = simpleDateFormat.format(time);
//        System.out.println(format);

        String va = "1606190400000";
        System.out.println(formatTime(va));

    }

    private static String formatTime(String s1){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long aLong = Long.valueOf(s1.trim());
        String format = simpleDateFormat.format(aLong);
        return format;
    }
}
