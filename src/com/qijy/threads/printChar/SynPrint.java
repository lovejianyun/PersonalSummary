package com.qijy.threads.printChar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SynPrint implements Runnable{

    private Integer count;
    private ReentrantLock lock;
    private String str;
    private Condition thisCondition;
    private Condition nextCondition;

    public SynPrint(ReentrantLock lock, String str, Condition thisCondition, Condition nextCondition,Integer count) {
        this.lock = lock;
        this.str = str;
        this.thisCondition = thisCondition;
        this.nextCondition = nextCondition;
        this.count = count;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            for (int i=0 ; i<count ;i++){
                System.out.print(str);
                nextCondition.signalAll();
                if(i<count-1){
                    try {
                        thisCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition conditionA = reentrantLock.newCondition();
        Condition conditionB = reentrantLock.newCondition();
        Condition conditionC = reentrantLock.newCondition();
        SynPrint synPrint = new SynPrint(reentrantLock,"A",conditionA,conditionB,50);
        SynPrint synPrint1 = new SynPrint(reentrantLock,"B",conditionB,conditionC, 50);
        SynPrint synPrint2 = new SynPrint(reentrantLock,"C",conditionC,conditionA,50);
//        new Thread(synPrint).start();
//        Thread.sleep(100);
//        new Thread(synPrint1).start();
//        Thread.sleep(100);
//        new Thread(synPrint2).start();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Executors.newScheduledThreadPool(2);
        executorService.execute(synPrint);
        Thread.sleep(100);
        executorService.execute(synPrint1);
        Thread.sleep(100);
        executorService.execute(synPrint2);
        executorService.shutdown();
    }
}
