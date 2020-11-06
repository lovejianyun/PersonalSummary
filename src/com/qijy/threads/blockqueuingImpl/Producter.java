package com.qijy.threads.blockqueuingImpl;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producter implements Runnable{
    private BlockingQueue<Integer> blockingQueue;
    private Integer count = 0;

    public Producter(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1000);
                blockingQueue.put(produce());
                System.out.println("队列中的数量:"+blockingQueue.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private Integer produce (){
        count++;
        System.out.println("生产者线程:"+Thread.currentThread().getName()+"生产："+count);
        return count;
    }
}
