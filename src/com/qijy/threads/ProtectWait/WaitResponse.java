package com.qijy.threads.ProtectWait;
/*
 * @ Description   :
 * @ Author        :  qijy
 * @ CreateDate    :  2020/6/3 16:46
 */
public class WaitResponse<T> {
    private T t;
    public Object get(long timeout){
        synchronized (this){
            while(null == t){
                long start = System.currentTimeMillis();
                long process = 0;
                long waittime = timeout-process;
                try {
                    if(waittime <= 0){
                        break;
                    }
                    this.wait(waittime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                process = System.currentTimeMillis()- start;
                System.out.println("经历时间:" + process);
            }
        }
        return t;
    }
    public void complete(Object response){
        synchronized (this){
            t = (T) response;
            this.notifyAll();
        }
    }
}
