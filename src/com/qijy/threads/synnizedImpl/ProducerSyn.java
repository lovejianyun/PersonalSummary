package com.qijy.threads.synnizedImpl;

import jdk.nashorn.internal.ir.WhileNode;

public class ProducerSyn implements Runnable {
    private ResourceData resourceData;

    public ProducerSyn(ResourceData resourceData) {
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
            resourceData.create();
        }
    }
}
