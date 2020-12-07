package com.qijy.designmodels.chainofResponsibility;

public abstract class AbstractLogger {
    public static int INFO = 2;
    public static int DEBUG = 1;
    public static int ERROR = 3;
    protected int level;

    private AbstractLogger abstractLogger;
    public void setAbstractLogger(AbstractLogger abstractLogger){
        this.abstractLogger = abstractLogger;
    }

    public void logMessage(int level,String message){
        if (this.level<=level){
            write(message);
        }
        if(this.abstractLogger != null){
            abstractLogger.logMessage(level,message);
        }

    }

     protected abstract void write(String message);
}
