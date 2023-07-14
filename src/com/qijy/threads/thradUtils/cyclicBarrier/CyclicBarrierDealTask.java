package com.qijy.threads.thradUtils.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author qijy
 * @version 1.0
 * @description: TODO
 * @date 2023/7/13 14:33
 */
public class CyclicBarrierDealTask implements Runnable{
    private CyclicBarrier cyclicBarrier;

    public CyclicBarrierDealTask(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            // 执行接口请求的操作，这里可以使用HttpURLConnection或者HttpClient等方法发送请求
            // 这里只是简单地模拟发送请求，并打印每个线程请求的结果
            // 等待其他线程准备就绪
            cyclicBarrier.await();
            String result = sendRequest();
            System.out.println(Thread.currentThread().getName() + " - 请求结果: " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
    private String sendRequest() {
        // 发送接口请求的代码，这里只是简单地返回一个字符串作为示例
        return "请求成功";
    }
}
