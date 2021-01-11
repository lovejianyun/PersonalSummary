package com.qijy.distributedlock.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/*
 * @ Description   :  zookeeper分布式锁
 * @ Author        :  qijy
 * @ CreateDate    :  2021/1/4 16:53
 *（1）创建一个目录mylock；
 *（2）线程A想获取锁就在mylock目录下创建临时顺序节点；
 *（3）获取mylock目录下所有的子节点，然后获取比自己小的兄弟节点，如果不存在，则说明当前线程顺序号最小，获得锁；
 *（4）线程B获取所有节点，判断自己不是最小节点，设置监听比自己次小的节点；
 *（5）线程A处理完，删除自己的节点，线程B监听到变更事件，判断自己是不是最小的节点，如果是则获得锁。
 *
 *
 */
public class ZooKeeperLock implements Watcher {
    private ZooKeeper zk = null;
    private String rootLockNode;            // 锁的根节点  locks
    private String lockName;                // 竞争资源，用来生成子节点名称  test1
    private String currentLock;             // 当前锁
    private String waitLock;                // 等待的锁（前一个锁）
    private CountDownLatch countDownLatch;  // 计数器（用来在加锁失败时阻塞加锁线程）
    private int sessionTimeout = 30000;     // 超时时间

    // 1. 构造器中创建ZK链接，创建锁的根节点
    public ZooKeeperLock(String zkAddress, String rootLockNode, String lockName) {
        this.rootLockNode = rootLockNode;
        this.lockName = lockName;
        try {
            // 创建连接，zkAddress格式为：IP:PORT
            zk = new ZooKeeper(zkAddress,this.sessionTimeout,this);
            // 检测锁的根节点是否存在，不存在则创建
            Stat stat = zk.exists(rootLockNode,false);
            if (null == stat) {
                zk.create(rootLockNode, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    // 2. 加锁方法，先尝试加锁，不能加锁则等待上一个锁的释放
    public boolean lock() {
        if (this.tryLock()) {
            System.out.println("线程【" + Thread.currentThread().getName() + "】加锁（" + this.currentLock + "）成功！");
            return true;
        } else {
            return waitOtherLock(this.waitLock, this.sessionTimeout);
        }
    }

    public boolean tryLock() {
        // 分隔符
        String split = "_lock_";
        if (this.lockName.contains("_lock_")) {
            throw new RuntimeException("lockName can't contains '_lock_' ");
        }
        try {
            // 创建锁节点（临时有序节点）
            this.currentLock = zk.create(this.rootLockNode + "/" + this.lockName + split, new byte[0],
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println("线程【" + Thread.currentThread().getName()
                    + "】创建锁节点（" + this.currentLock + "）成功，开始竞争...");
            // 取所有子节点
            List<String> nodes = zk.getChildren(this.rootLockNode, false);
            // 取所有竞争lockName的锁
            List<String> lockNodes = new ArrayList<String>();
            for (String nodeName : nodes) {
                if (nodeName.split(split)[0].equals(this.lockName)) {
                    lockNodes.add(nodeName);
                }
            }
            Collections.sort(lockNodes);
            // 取最小节点与当前锁节点比对加锁
            String currentLockPath = this.rootLockNode + "/" + lockNodes.get(0);
            if (this.currentLock.equals(currentLockPath)) {
                return true;
            }
            // 加锁失败，设置前一节点为等待锁节点
            String currentLockNode = this.currentLock.substring(this.currentLock.lastIndexOf("/") + 1);
            int preNodeIndex = Collections.binarySearch(lockNodes, currentLockNode) - 1;
            this.waitLock = lockNodes.get(preNodeIndex);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean waitOtherLock(String waitLock, int sessionTimeout) {
        boolean islock = false;
        try {
            // 监听等待锁节点
            String waitLockNode = this.rootLockNode + "/" + waitLock;
            Stat stat = zk.exists(waitLockNode,true); // watcher等待的节点
            if (null != stat) {
                System.out.println("线程【" + Thread.currentThread().getName()
                        + "】锁（" + this.currentLock + "）加锁失败，等待锁（" + waitLockNode + "）释放...");
                // 设置计数器，使用计数器阻塞线程
                this.countDownLatch = new CountDownLatch(1);
                islock = this.countDownLatch.await(sessionTimeout, TimeUnit.MILLISECONDS);
                this.countDownLatch = null;
                if (islock) {
                    System.out.println("线程【" + Thread.currentThread().getName() + "】锁（"
                            + this.currentLock + "）加锁成功，锁（" + waitLockNode + "）已经释放");
                } else {
                    System.out.println("线程【" + Thread.currentThread().getName() + "】锁（"
                            + this.currentLock + "）加锁失败...");
                }
            } else {
                islock = true;
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return islock;
    }

    // 3. 释放锁
    public void unlock() throws InterruptedException {
        try {
            Stat stat = zk.exists(this.currentLock,false);
            if (null != stat) {
                System.out.println("线程【" + Thread.currentThread().getName() + "】释放锁 " + this.currentLock);
                zk.delete(this.currentLock, -1);
                this.currentLock = null;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        } finally {
            zk.close();
        }
    }

    // 4. 监听器回调
    @Override
    public void process(WatchedEvent watchedEvent) {
        if (null != this.countDownLatch && watchedEvent.getType() == Watcher.Event.EventType.NodeDeleted) {
            // 计数器减一，恢复线程操作
            this.countDownLatch.countDown();
        }
    }


}
