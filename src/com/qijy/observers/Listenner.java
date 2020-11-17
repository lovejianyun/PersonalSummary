package com.qijy.observers;

import java.util.Observable;
import java.util.Observer;

public class Listenner implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        RunThread runThread = new RunThread("任务开始重新启动!");
        runThread.addObserver(this);
        new Thread(runThread).start();
    }
}
