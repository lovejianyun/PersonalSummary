package com.qijy.algorithm.threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 * @ Description   :  我们提供一个类：

class FooBar {
  public void foo() {
    for (int i = 0; i < n; i++) {
      print("foo");
    }
  }

  public void bar() {
    for (int i = 0; i < n; i++) {
      print("bar");
    }
  }
}
两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。

请设计修改程序，以确保 "foobar" 被输出 n 次。

 

示例 1:

输入: n = 1
输出: "foobar"
解释: 这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。
示例 2:

输入: n = 2
输出: "foobarfoobar"
解释: "foobar" 将被输出两次。
 * @ Author        :  qijy
 * @ CreateDate    :  2020/7/7 13:38
 */
public class FooBar {
    private int n;
    ReentrantLock lock = new ReentrantLock();
    Condition c1 = null;
    Condition c2 = null;
    private volatile boolean flag = true;
    public FooBar(int n) {
        this.n = n;
        c1 = lock.newCondition();
        c2 = lock.newCondition();
        Foo foo = new Foo();
        Bar bar = new Bar();
        Thread t1 = new Thread(foo);
        Thread t2 = new Thread(bar);
        t1.start();
        t2.start();
    }

    class Foo implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i <n ; i++) {
                try {
                    lock.lock();
                    while(!flag){
                        c1.await();
                    }
                    System.out.print("foo");
                    flag = false;
                    c2.signalAll();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }
    }

    class Bar implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i <n ; i++) {
                try {
                    lock.lock();
                    while(flag){
                        c2.await();
                    }
                    System.out.print("bar");
                    flag = true;
                    c1.signalAll();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }
    }
}
