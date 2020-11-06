package com.qijy.threads.threadlocals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateTest implements Runnable{
    private SimpleDateFormat sdf;
    private String datestr;
    private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<>();

    public SimpleDateTest(SimpleDateFormat sdf, String datestr) {
        this.sdf = sdf;
        this.datestr = datestr;
    }

    @Override
    public void run() {
        try {
            if (datestr==null || "".equals(datestr)){
                return;
            }
            Date parse = sdf.parse(datestr);
            String s = sdf.format(parse);
            if(!datestr.equals(s)){
                System.out.println(Thread.currentThread().getName() + "报错:" + "日期字符串:" + datestr + ";转换后字符串:" + s);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String datestrs[]=new String []{"2020-07-03","2020-07-05","2020-07-06","2020-07-07","2020-07-08"};
        for (int i=0;i<5;i++){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateTest spdt = new SimpleDateTest(sdf,datestrs[i]);
            new Thread(spdt).start();
        }
    }

    public static SimpleDateFormat getSdf(String strpart){
        SimpleDateFormat sdf = null;
        sdf = threadLocal.get();
        if(sdf == null){
            sdf = new SimpleDateFormat(strpart);
            threadLocal.set(sdf);
        }
        return sdf;
    }

}
