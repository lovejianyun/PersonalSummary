package com.qijy.threads.reetranctLockImpl;

public class ConsumerReetrant implements Runnable{
    private ResourceData1 resourceData1;

    public ConsumerReetrant(ResourceData1 resourceData1) {
        this.resourceData1 = resourceData1;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.exit(0);
            }
            resourceData1.descret();
        }

    }
}
