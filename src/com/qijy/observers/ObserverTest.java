package com.qijy.observers;

import java.util.concurrent.TimeUnit;

public class ObserverTest {
    public static void main(String[] args) {
        try {
            RunThread runThread = new RunThread("任务开始启动!");
            Listenner listenner = new Listenner();
            runThread.addObserver(listenner);
            Thread thread = new Thread(runThread);
            thread.start();
            TimeUnit.SECONDS.sleep(5);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
