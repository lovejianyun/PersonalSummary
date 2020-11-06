package com.qijy.threads.ProtectWait;

import java.util.concurrent.TimeUnit;

public class ProtectWaitTest {
    public static void main(String[] args) {
        WaitResponse<String> waitResponse = new WaitResponse<>();
        new Thread(()->{
            //等待线程结果
            Object o = waitResponse.get(10000L);
            System.out.println("等待结果为:"+o.toString());

        }).start();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            waitResponse.complete("qijy");
        }).start();

    }
}
