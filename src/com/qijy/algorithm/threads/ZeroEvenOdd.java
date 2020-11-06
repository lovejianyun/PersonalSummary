package com.qijy.algorithm.threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 * @ Description   :  假设有这么一个类：

class ZeroEvenOdd {
  public ZeroEvenOdd(int n) { ... }      // 构造函数
  public void zero(printNumber) { ... }  // 仅打印出 0
  public void even(printNumber) { ... }  // 仅打印出 偶数
  public void odd(printNumber) { ... }   // 仅打印出 奇数
}
相同的一个 ZeroEvenOdd 类实例将会传递给三个不同的线程：

线程 A 将调用 zero()，它只输出 0 。
线程 B 将调用 even()，它只输出偶数。
线程 C 将调用 odd()，它只输出奇数。
每个线程都有一个 printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列 010203040506... ，其中序列的长度必须为 2n。

 

示例 1：

输入：n = 2
输出："0102"
说明：三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用odd()。正确的输出为 "0102"。
示例 2：

输入：n = 5
输出："0102030405"

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/print-zero-even-odd
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ Author        :  qijy
 * @ CreateDate    :  2020/7/7 15:15
 */
public class ZeroEvenOdd {
    private int n;
    private ReentrantLock lock = new ReentrantLock();
    private Condition zero = null;
    private Condition even = null;
    private Condition odd = null;
    private volatile int count = 1;
    private volatile int xxx = 1;
    public ZeroEvenOdd(int n) {
        this.n = n;
        zero = lock.newCondition();
        even = lock.newCondition();
        odd = lock.newCondition();
        Thread t1 = new Thread(new Zero());
        Thread t2 = new Thread(new Even());
        Thread t3 = new Thread(new Odd());
        t1.start();
        t2.start();
        t3.start();
    }

    class Zero implements Runnable{
        @Override
        public void run() {
            for (int i = 1; i <= n; i++) {
                try {
                    lock.lock();
                    while (count != 1){
                        zero.await();
                    }
                    System.out.print("0");
                    if(xxx == 1){
                        count = 2;
                    }else{
                        count = 3;
                    }
                    odd.signalAll();
                    even.signalAll();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }
    }
    /*
     * @ Description   :  偶数
     * @ Author        :  qijy
     * @ CreateDate    :  2020/7/7 15:26
     */
    class Even implements Runnable{
        @Override
        public void run() {
            for (int i = 1; i <=n ; i++) {
                try {
                    lock.lock();
                    while (count != 3){
                        odd.await();
                    }
                    if(i%2 == 0){
                        System.out.print(i+"");
                        count = 1;
                        xxx = 1;
                        zero.signalAll();
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }
    }
    /*
     * @ Description   :  奇数
     * @ Author        :  qijy
     * @ CreateDate    :  2020/7/7 15:26
     */
    class Odd implements Runnable{
        @Override
        public void run() {
            for (int i = 1; i <= n; i++) {
                try {
                    lock.lock();
                    while (count != 2){
                        odd.await();
                    }
                    if(i%2!=0){
                        System.out.print(i+"");
                        count = 1;
                        xxx = 2;
                        zero.signalAll();
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }
    }
}
