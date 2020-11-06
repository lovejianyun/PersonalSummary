package com.qijy.times;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTest {
    public static void main(String[] args) {
        for (int i = 0; i <10 ; i++) {
            Thread thread = new Thread(new TimeTest().new aaa());
            thread.start();
        }
    }

    class  aaa implements Runnable{

        @Override
        public void run() {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String format = sdf.format(new Date());
            System.out.println(format);
        }
    }



}
