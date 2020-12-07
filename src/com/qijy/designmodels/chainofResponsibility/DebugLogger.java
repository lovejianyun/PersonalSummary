package com.qijy.designmodels.chainofResponsibility;

public class DebugLogger extends AbstractLogger {
    public DebugLogger(int level){
        this.level = level;
    }
    @Override
    protected void write(String message) {
        System.out.println("debug日志:"+message);
    }
}
