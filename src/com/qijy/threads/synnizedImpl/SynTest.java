package com.qijy.threads.synnizedImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynTest {
    public static void main(String[] args) {
        ResourceData resourceData = new ResourceData();
//        ExecutorService executorService = Executors.newFixedThreadPool(4);
        ProducerSyn producerSyn = new ProducerSyn(resourceData);
        ConsumerSyn consumerSyn = new ConsumerSyn(resourceData);
//        executorService.execute(producerSyn);
//        executorService.execute(consumerSyn);

        new Thread(producerSyn).start();
        new Thread(producerSyn).start();
        new Thread(producerSyn).start();
        new Thread(consumerSyn).start();
        new Thread(consumerSyn).start();
        new Thread(consumerSyn).start();
    }
}
