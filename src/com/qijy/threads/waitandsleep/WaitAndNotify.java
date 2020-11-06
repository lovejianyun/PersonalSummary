package com.qijy.threads.waitandsleep;

import java.util.concurrent.TimeUnit;

/*
 * @ Description   :  wait 和notify 测试
 * @ Author        :  qijy
 * @ CreateDate    :  2020/6/3 15:30
 */
public class WaitAndNotify {
    // 是否有奶茶
    private static volatile  boolean isDrinking = false;
    // 是否有烟
    private static volatile boolean isSomking= false;
    // 锁
    private static final Object obj = new Object();
    public static void main(String[] args) {


        Thread t3 = new Thread(()->{
            synchronized (obj){
                try {
                if(!isDrinking){
                    System.out.println("小明没有奶茶老子不干活!");

                        obj.wait();

                }

                if(isDrinking){
                    System.out.println("小明开始干活了");
                }else{
                    System.out.println("小明没有干活");
                }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"小明");
        t3.start();

        Thread t1 = new Thread(() -> {
            synchronized (obj){
                try {
                    if(!isSomking){
                        System.out.println("小三没有烟老子不干活!");
                        obj.wait();
                    }
                    if(isSomking){
                        System.out.println("小三开始干活了");
                    }else{
                        System.out.println("小三没有干活");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"小三");
        t1.start();




        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread t2 = new Thread(()->{
            synchronized (obj){
                System.out.println("外卖到了!");
                isSomking = true;
//                isDrinking = true;
                obj.notify();

            }
        },"外卖员");
        t2.start();
    }
}
