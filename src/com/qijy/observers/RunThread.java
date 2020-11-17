package com.qijy.observers;

import java.util.Observable;
import java.util.concurrent.TimeUnit;

public class RunThread extends Observable implements Runnable {
    private String msg;

    public RunThread(String msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        try {
            System.out.println(msg);
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            doBusiness();
        }
    }

    private void doBusiness(){
        super.setChanged();
        super.notifyObservers(msg);
    }
}
