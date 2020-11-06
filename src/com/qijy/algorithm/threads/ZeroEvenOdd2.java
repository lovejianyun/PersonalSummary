package com.qijy.algorithm.threads;

import java.util.concurrent.Semaphore;

public class ZeroEvenOdd2 {

    private int n;
    private Semaphore zeroSemaphore = new Semaphore(0);
    private Semaphore evenSemaphore = new Semaphore(0);
    private Semaphore oddSemaphore = new Semaphore(1);
    private int i = 1;

    public ZeroEvenOdd2(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero() {
        while (true) {
            try {
                zeroSemaphore.acquire();
                if (i > n) {
                    return;
                }
                System.out.print("0");
                oddSemaphore.release();// 打印完0后继续执行odd方法
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void even() {
        while (true) {
            try {
                evenSemaphore.acquire();
                if (i > n) {
                    return;
                }
                System.out.print(""+i);
                oddSemaphore.release();// 打印完偶数后继续执行odd方法
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void odd()  {
        while (i <= n) {
            try {
                oddSemaphore.acquire();
                zeroSemaphore.release();// 打印0
                oddSemaphore.acquire();// 阻塞当前线程，等待0打印完成
                if ((i & 1) != 0) {
                    System.out.print(""+i);// 打印奇数
                } else {
                    evenSemaphore.release();// 打印偶数
                    oddSemaphore.acquire();// 阻塞当前线程，等待偶数打印完成
                }
                i++;
                oddSemaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 释放最后遗留的锁
        zeroSemaphore.release();
        evenSemaphore.release();
    }

    public static void main(String[] args) throws InterruptedException {
        ZeroEvenOdd2 zeroEvenOdd2 = new ZeroEvenOdd2(100);
        new Thread(zeroEvenOdd2::zero).start();
        new Thread(zeroEvenOdd2::odd).start();
        new Thread(zeroEvenOdd2::even).start();
    }

}
