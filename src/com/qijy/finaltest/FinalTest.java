package com.qijy.finaltest;

/**
 * @author qijy
 * @version 1.0
 * @description: TODO
 * @date 2023/4/12 14:46
 */
public class FinalTest {
    public static void main(String[] args) {
        final byte b1=1;
        final byte b2=3;
        byte b3=b1+b2;
        System.out.println(b3);
        String str = "2023/3/4";
        String s = dealDate(str);
        System.out.println(s);
    }


    public static String dealDate(String dateStr){
            String[] split = dateStr.split("\\/");
            String year = split[0];
            String month = split[1];
            if(!month.contains("0") && month.length() == 1 ){
                month = "0" + month;
            }
            String date = split[2];
            if(!date.contains("0") && date.length() == 1){
                date = "0"+date;
            }
            return year+ "/" + month + "/" + date;
    }
}
