package com.qijy.algorithm.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 * @ Description   :  
 * @ Author        :  qijy
 * @ CreateDate    :  2020/10/16 15:19
 */
public class ABCWhilePrint2 {
    private CountDownLatch latchA = new CountDownLatch(1);
    private CountDownLatch latchB = new CountDownLatch(1);
    private CountDownLatch latchC = new CountDownLatch(2);
    private int i=1;
    private int n;

    public ABCWhilePrint2(int n) {
        this.n = n;
    }
    public void printA(){
        try {
            latchA.await();
            if(i>n){
                return;
            }
            System.out.print("A");
            latchC.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }

    public void printB(){
        try {
            while (true){
                latchA.await();
                if(i>n){
                    return;
                }
                System.out.print("B");
                latchC.countDown();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }

    public void printC(){
        while (i<=n){

        }
    }




    public static void main(String[] args) {
        ABCWhilePrint2 abcWhilePrint2 = new ABCWhilePrint2(10);
        new Thread(abcWhilePrint2::printA).start();
        new Thread(abcWhilePrint2::printB).start();
        new Thread(abcWhilePrint2::printC).start();
    }

}
