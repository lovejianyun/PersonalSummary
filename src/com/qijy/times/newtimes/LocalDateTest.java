package com.qijy.times.newtimes;

import javax.swing.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

/*
 * @ Description   :  localDate测试
 * @ Author        :  qijy
 * @ CreateDate    :  2020/11/16 15:52
 */
public class LocalDateTest {
    public static void main(String[] args) {
//        test001();
//        test002();
//        test003();
//        test004();
        test005();
    }

    private static void test001(){
        LocalDate date = LocalDate.of(2020, 11, 16);
        // 指定日期的年份
        int year = date.getYear();
        // 指定日期的月份
        Month month = date.getMonth();
        // 指定日期多少号
        int day = date.getDayOfMonth();
        // 今天星期几
        DayOfWeek dow = date.getDayOfWeek();
        // 当前月份的天数
        int len = date.lengthOfMonth();
        // 是否为闰年
        boolean leap = date.isLeapYear();
        System.out.println("year:"+year+"    |month:"+month+"    |day:"+day+"    |dow:"+dow+"    |len:"+len+"    |"+leap);

    }


    private static void test002(){
        LocalDate now = LocalDate.now();
        int year = now.get(ChronoField.YEAR);

        int month = now.get(ChronoField.MONTH_OF_YEAR);

        int day = now.get(ChronoField.DAY_OF_MONTH);
        // 字符串转成日期
        LocalDate parse = LocalDate.parse("2020-11-16");

        System.out.println("year:"+year+"|month:"+month+"|day:"+day);

    }

    private static void test003(){
        LocalTime time = LocalTime.of(13, 45, 20);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        LocalTime parse = LocalTime.parse("23:59:59");
        System.out.println("hour:"+hour+"|minute:"+minute+"|second:"+second);
    }

    private static void test004(){
        LocalDateTime now = LocalDateTime.now();
        String format = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println(format);
    }

    private static void test005(){
        LocalDate date = LocalDate.of(2014, 3, 18);
        date = date.with(ChronoField.MONTH_OF_YEAR, 9);
        date = date.plusYears(2).minusDays(10);
        System.out.println(date.toString());
        LocalDate localDate = date.withYear(2011);

        System.out.println(localDate.toString());

        date.with(TemporalAdjusters.firstDayOfMonth());
    }
}
