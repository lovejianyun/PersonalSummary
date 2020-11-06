package com.qijy.threads.synnizedImpl;

public class ResourceData {
    private Integer count = 0;
    private boolean flag = false;
    public void create(){
        synchronized (this) {
            while (flag){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count ++;
            System.out.println("线程"+Thread.currentThread().getName()+"生产"+count);
            flag = true;
            this.notifyAll();

        }
    }

    public void descret(){
        synchronized(this){
            while (!flag){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("线程："+Thread.currentThread().getName()+"消费"+count);
            flag = false;
            this.notifyAll();
        }
    }
}
