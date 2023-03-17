package com.qijy.threads.completableFutureTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

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
