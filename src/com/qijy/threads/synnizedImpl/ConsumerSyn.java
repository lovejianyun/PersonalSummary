package com.qijy.threads.synnizedImpl;

public class ConsumerSyn implements Runnable {
    private ResourceData resourceData;

    public ConsumerSyn(ResourceData resourceData) {
        this.resourceData = resourceData;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resourceData.descret();
        }

    }
}
