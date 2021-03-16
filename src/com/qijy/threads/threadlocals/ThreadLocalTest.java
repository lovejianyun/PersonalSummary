package com.qijy.threads.threadlocals;

public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

        new Runnable(){
            @Override
            public void run() {

            }
        };
    }
}
