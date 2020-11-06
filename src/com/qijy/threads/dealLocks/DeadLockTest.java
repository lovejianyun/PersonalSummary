package com.qijy.threads.dealLocks;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 * @ Description   :
 * @ Author        :  qijy
 * @ CreateDate    :  2020/11/5 14:33
 */
public class DeadLockTest {
    private static Object a = new Object();
    private static Object b = new Object();

//    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor();
   static Runnable runnable = new Runnable() {
        @Override
        public void run() {

            while (true){
                synchronized (a){
                    synchronized (b){
                        try {
                            TimeUnit.SECONDS.sleep(1);
                            System.out.println("你是个傻逼!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }

        }
    };

    static Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            while (true){
                synchronized (b){
                    synchronized (a){
                        try {
                            TimeUnit.SECONDS.sleep(1);
                            System.out.println("你是一个逗比!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }


        }
    };


    public static void main(String[] args) {
        new Thread(runnable).start();
        new Thread(runnable1).start();

    }


}
