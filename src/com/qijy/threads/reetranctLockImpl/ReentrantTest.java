package com.qijy.threads.reetranctLockImpl;

public class ReentrantTest {
    public static void main(String[] args) {
        ResourceData1 resourceData1 = new ResourceData1();
        ProducerReentrant producerReentrant = new ProducerReentrant(resourceData1);
        ConsumerReetrant consumerReetrant = new ConsumerReetrant(resourceData1);
        new Thread(producerReentrant).start();
        new Thread(consumerReetrant).start();

    }
}
