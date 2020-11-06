package com.qijy.threads.reetranctLockImpl;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ResourceData1 {

    private Integer count = 0;
    private volatile boolean flag = false;
    ReentrantLock reentrantLock = new ReentrantLock();
    Condition pro = reentrantLock.newCondition();
    Condition con = reentrantLock.newCondition();


    public void create(){
        try {
            //加锁
            reentrantLock.lock();
            while (flag) {
                try {
                    pro.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count ++;
            System.out.println("线程"+Thread.currentThread().getName()+"生产"+count);
            flag = true;
            con.signalAll();
        }finally {
            //解锁
            reentrantLock.unlock();
        }

    }

    public void descret(){
            try {
                //加锁
                reentrantLock.lock();
                while (!flag){
                    try {
                        con.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("线程："+Thread.currentThread().getName()+"消费"+count);
                flag = false;
                pro.signalAll();
            }finally {
                //解锁
                reentrantLock.unlock();
            }
    }
}
