package com.qijy.threads.completableFutureTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author qijy
 * @version 1.0
 * @description: CompletableFuture 测试
 * @date 2022/9/20 9:51
 */
public class CompletableFutureTest {
    public static void main(String[] args) throws Exception{
//        test001();
//        test002();
        test003();
//        test004();
    }
    /**
     * @description: 处理A,B异步，并且等A,B全部执行完毕，执行C
     * @author qijy
     * @date 2022/9/20 11:23
     * @version 1.0
     */
    private static void test001() throws Exception {
        CompletableFuture<Void> A = CompletableFuture.runAsync(() -> {
            CompletableFutureTest completableFutureTest = new CompletableFutureTest();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            completableFutureTest.printA();
        });
        CompletableFuture<Void> B = CompletableFuture.runAsync(() -> {
            CompletableFutureTest completableFutureTest = new CompletableFutureTest();
            completableFutureTest.printB();
        });
        CompletableFuture<Void> run = CompletableFuture.allOf(A, B).thenRun(() -> {
            CompletableFutureTest completableFutureTest = new CompletableFutureTest();
            completableFutureTest.printC();
        });
        run.get();
        System.out.println("任务执行完毕！");
    }
    /**
     * @description: 处理A,B异步，并且等A,B全部执行完毕，执行C
     * @author qijy
     * @date 2022/9/20 11:23
     * @version 1.0
     */
    private static void test002() throws Exception{
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            CompletableFutureTest completableFutureTest = new CompletableFutureTest();
            completableFutureTest.printA();
        }).runAfterBothAsync(CompletableFuture.runAsync(() -> {
            CompletableFutureTest completableFutureTest = new CompletableFutureTest();
            completableFutureTest.printB();
        }), () -> {
            CompletableFutureTest completableFutureTest = new CompletableFutureTest();
            completableFutureTest.printC();
        });
        voidCompletableFuture.get();
        System.out.println("任务执行完毕！");
    }
    /**
     * @description: 当A,B,中任意一个执行完成，然后执行C ,未完成的任务，自动取消了，并没有执行
     * @author qijy
     * @date 2022/9/20 17:03
     * @version 1.0
     */
    private static void test003() throws Exception{
        CompletableFuture<Void> A = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            CompletableFutureTest completableFutureTest = new CompletableFutureTest();
            completableFutureTest.printA();
        });
        CompletableFuture<Void> B = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            CompletableFutureTest completableFutureTest = new CompletableFutureTest();
            completableFutureTest.printB();
        });
        CompletableFuture<Void> completableFuture = CompletableFuture.anyOf(A, B).thenRun(() -> {
            CompletableFutureTest completableFutureTest = new CompletableFutureTest();
            completableFutureTest.printC();
        });
        completableFuture.get();
        System.out.println("任务执行完毕！");

    }
    /**
     * @description: 测试等所有任务都执行完成，才返回耗时。
     * @author qijy
     * @date 2023/7/20 10:33
     * @version 1.0
     */
    private static void test004() throws Exception{
        List<CompletableFuture<String>> list = new ArrayList<>();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(100,100,10,TimeUnit.SECONDS,new LinkedBlockingQueue<>(1000));
        long start = System.currentTimeMillis();
        for(int i=0;i<100;i++){
            CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("测试有返回值："+"qijy");
                return "qijy";
            },pool);
            list.add(supplyAsync);
        }
        CompletableFuture<Void> future = CompletableFuture.allOf(list.toArray(new CompletableFuture[0]));
        future.get();
        long end = System.currentTimeMillis();
        System.out.println("所有执行完成耗时："+(end-start));
        // 关闭线程池
        pool.shutdown();
    }



    private void printA(){
        System.out.println("A");
    }

    private void printB(){
        System.out.println("B");
    }

    private void printC(){
        System.out.println("C");
    }
}
