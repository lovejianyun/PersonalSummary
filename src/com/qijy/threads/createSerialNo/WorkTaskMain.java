package com.qijy.threads.createSerialNo;

public class WorkTaskMain {
    public static void main(String[] args) {
        Thread [] threads = new Thread[20];
        for (int i=0;i<20;i++){
            threads[i] = new Thread(new WorkTask(10),"workthread-"+i);
        }
        // 启动线程
        for (Thread thread : threads) {
            thread.start();
        }



    }

}
