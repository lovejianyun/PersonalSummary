package com.qijy.pids;
import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

public class Pids {
    public static void main(String[] args) {
        try {
            String runTimeName = ManagementFactory.getRuntimeMXBean().getName();
            String pid = runTimeName.split("@")[0];
            System.out.println("      pid【" + pid + "】");

            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
