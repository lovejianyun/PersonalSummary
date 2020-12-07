package com.qijy.designmodels.chainofResponsibility;

public class ErrorLogger extends AbstractLogger{
    public ErrorLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error日志:"+message);
    }
}
