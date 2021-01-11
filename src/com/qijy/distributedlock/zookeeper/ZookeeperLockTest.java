package com.qijy.distributedlock.zookeeper;
/*
 * @ Description   :  zookeeper分布式锁测试
 * @ Author        :  qijy
 * @ CreateDate    :  2021/1/4 17:08
 */
public class ZookeeperLockTest {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            public void run() {
                ZooKeeperLock lock = null;
                lock = new ZooKeeperLock("127.0.0.1:2181","/locks", "test521");
                if (lock.lock()) {    // 获取zookeeper的节点锁
                    doSomething();
                    try {
                        Thread.sleep(1000);
                        lock.unlock();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(runnable);
            t.start();
        }
    }

    public static void doSomething() {
        System.out.println("线程【" + Thread.currentThread().getName() + "】正在运行...");
    }

}
