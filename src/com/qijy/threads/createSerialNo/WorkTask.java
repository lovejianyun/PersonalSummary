package com.qijy.threads.createSerialNo;

import java.util.concurrent.TimeUnit;

/*
 * @ Description   :  任务线程
 * @ Author        :  qijy
 * @ CreateDate    :  2020/11/3 10:35
 */
public class WorkTask implements Runnable {
    private int limitCount;

    public WorkTask(int limitCount) {
        this.limitCount = limitCount;
    }

    @Override
    public void run() {
        try {
            while (limitCount-->0){
                String serialNo = CreateSerialNo.getInstance().nextSerialNo();
//                TimeUnit.MILLISECONDS.sleep(1000);
                System.out.printf("%s get SerialNo: %s %n",Thread.currentThread().getName(),serialNo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
