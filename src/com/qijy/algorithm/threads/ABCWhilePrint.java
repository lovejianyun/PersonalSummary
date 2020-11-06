package com.qijy.algorithm.threads;

import java.util.concurrent.Semaphore;
/*
 * @ Description   :  信号量的循环打印
 * @ Author        :  qijy
 * @ CreateDate    :  2020/10/16 15:10
 */
public class ABCWhilePrint {
    private Semaphore semaphoreA = new Semaphore(0);
    private Semaphore semaphoreB = new Semaphore(0);
    private Semaphore semaphoreC = new Semaphore(1);
    private int n;
    private int i=1;

    public ABCWhilePrint(int n) {
        this.n = n;
    }

    public void printA(){
        try {
            while(true){
                semaphoreA.acquire();
                if(i>n){
                    return;
                }
                System.out.print("A");
                semaphoreC.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }

    public void printB(){
        try {
            while (true){
                semaphoreB.acquire();
                // 如果大于n退出程序
                if(i>n){
                    return;
                }
                System.out.print("B");
                semaphoreC.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

        }
    }

    public void printC(){
        try {
            while(i<=n){
                semaphoreC.acquire();
                semaphoreA.release();
                semaphoreC.acquire();
                semaphoreB.release();
                semaphoreC.acquire();
                System.out.print("C");
                semaphoreC.release();
                i++;
            }
            semaphoreA.release();
            semaphoreB.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }

    public static void main(String[] args) {
        ABCWhilePrint abcWhilePrint = new ABCWhilePrint(5);
        new Thread(abcWhilePrint::printA).start();
        new Thread(abcWhilePrint::printB).start();
        new Thread(abcWhilePrint::printC).start();
    }
}
