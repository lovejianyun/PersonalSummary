package com.qijy.threads.threadsafe;

public class SafeThread implements Runnable {
    private String content;

    public SafeThread(String content) {
        this.content = content;
    }

    @Override
    public void run() {
        String xxx = content+getUserid();
        System.out.println("当前线程:"+Thread.currentThread().getName()+"   ;content:"+xxx);
    }
    private synchronized String getUserid(){
        String result = "";
        int random = (int)Math.random() * 100;
        result = random+"";
        System.out.println("随机数:"+result);
        return result;
    }
}
