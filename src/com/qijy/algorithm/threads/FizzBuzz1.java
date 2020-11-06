package com.qijy.algorithm.threads;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*
 * @ Description   :  编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：

如果这个数字可以被 3 整除，输出 "fizz"。
如果这个数字可以被 5 整除，输出 "buzz"。
如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
例如，当 n = 15，输出： 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz。

假设有这么一个类：

class FizzBuzz {
  public FizzBuzz(int n) { ... }               // constructor
  public void fizz(printFizz) { ... }          // only output "fizz"
  public void buzz(printBuzz) { ... }          // only output "buzz"
  public void fizzbuzz(printFizzBuzz) { ... }  // only output "fizzbuzz"
  public void number(printNumber) { ... }      // only output the numbers
}
请你实现一个有四个线程的多线程版  FizzBuzz， 同一个 FizzBuzz 实例会被如下四个线程使用：

线程A将调用 fizz() 来判断是否能被 3 整除，如果可以，则输出 fizz。
线程B将调用 buzz() 来判断是否能被 5 整除，如果可以，则输出 buzz。
线程C将调用 fizzbuzz() 来判断是否同时能被 3 和 5 整除，如果可以，则输出 fizzbuzz。
线程D将调用 number() 来实现输出既不能被 3 整除也不能被 5 整除的数字。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/fizz-buzz-multithreaded
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ Author        :  qijy
 * @ CreateDate    :  2020/7/6 17:25
 */
public class FizzBuzz1 {
    private int n;
    CyclicBarrier c = new CyclicBarrier(4);
    public FizzBuzz1(int n) {
        this.n = n;
        PrintNumber printNumber = new PrintNumber();
        PrintFizz printFizz = new PrintFizz();
        PrintBuzz printBuzz = new PrintBuzz();
        PrintFizzBuzz printFizzBuzz = new PrintFizzBuzz();
        Thread t1 = new Thread(printNumber,"打印数字线程");
        Thread t2 = new Thread(printFizz,"打印fizz线程");
        Thread t3 = new Thread(printBuzz,"打印buzz线程");
        Thread t4 = new Thread(printFizzBuzz,"打印fizzbuzz线程");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    class PrintNumber implements Runnable{

        @Override
        public void run() {
            for(int i = 1;i<=n;i++){
                if(i%3!=0&&i%5!=0){
                    System.out.print(i+",");
                }
                try {
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    class PrintFizz implements Runnable{

        @Override
        public void run() {
            for (int i=1;i<=n;i++){
                if (i%3==0 && i%5!=0){
                    System.out.print("fizz"+",");
                }
                try {
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class PrintBuzz implements Runnable{
        @Override
        public void run() {
            for (int i=1;i<=n;i++){
                if (i%3!=0 && i%5==0){
                    System.out.print("buzz"+",");
                }
                try {
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class PrintFizzBuzz implements Runnable{
        @Override
        public void run() {
            for (int i=1;i<=n;i++){
                if (i%3==0 && i%5==0){
                    System.out.print("fizzbuzz"+",");
                }
                try {
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
