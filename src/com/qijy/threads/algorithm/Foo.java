package com.qijy.threads.algorithm;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * @ Description   :  我们提供了一个类：

public class Foo {
  public void one() { print("one"); }
  public void two() { print("two"); }
  public void three() { print("three"); }
}
三个不同的线程将会共用一个 Foo 实例。

线程 A 将会调用 one() 方法
线程 B 将会调用 two() 方法
线程 C 将会调用 three() 方法
请设计修改程序，以确保 two() 方法在 one() 方法之后被执行，three() 方法在 two() 方法之后被执行。

 

示例 1:

输入: [1,2,3]
输出: "onetwothree"
解释:
有三个线程会被异步启动。
输入 [1,2,3] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 two() 方法，线程 C 将会调用 three() 方法。
正确的输出是 "onetwothree"。
示例 2:

输入: [1,3,2]
输出: "onetwothree"
解释:
输入 [1,3,2] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 three() 方法，线程 C 将会调用 two() 方法。
正确的输出是 "onetwothree"。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/print-in-order
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ Author        :  qijy
 * @ CreateDate    :  2020/7/6 15:24
 */
public class Foo {
    public Foo() {
    }
    public static void main(String[] args) {
        CountDownLatch c2 = new CountDownLatch(1) ;
        CountDownLatch c3 = new CountDownLatch(1);
        A a = new A(c2);
        B b = new B(c2,c3);
        C c = new C(c3);
        Thread ta = new Thread(a);
        Thread tb = new Thread(b);
        Thread tc = new Thread(c);
        tc.start();
        tb.start();
        ta.start();


    }

   static class A implements Runnable{
        private CountDownLatch countDownLatch;

       public A(CountDownLatch countDownLatch) {
           this.countDownLatch = countDownLatch;
       }

       @Override
        public void run() {

            try {
                System.out.print("A");
                countDownLatch.countDown();
            } finally {
            }
        }
    }

    static class B implements Runnable{
        private CountDownLatch c2;
        private CountDownLatch c3;

        public B(CountDownLatch c2, CountDownLatch c3) {
            this.c2 = c2;
            this.c3 = c3;
        }

        @Override
        public void run() {
            try {
                c2.await();
                System.out.print("B");
                c3.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

            }
        }
    }

   static class C implements Runnable{

       private CountDownLatch c3;

       public C( CountDownLatch c3) {

           this.c3 = c3;
       }
        @Override
        public void run() {

            try {
                c3.await();
                System.out.print("C");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }
        }
    }

}
