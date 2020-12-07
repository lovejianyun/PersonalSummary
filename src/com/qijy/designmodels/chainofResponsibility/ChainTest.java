package com.qijy.designmodels.chainofResponsibility;

public class ChainTest {
    private static AbstractLogger abstractLogger;
    static {
        AbstractLogger debugLogger = new DebugLogger(1);
        AbstractLogger infoLogger = new InfoLogger(2);
        AbstractLogger errorLogger = new ErrorLogger(3);
        debugLogger.setAbstractLogger(infoLogger);
        infoLogger.setAbstractLogger(errorLogger);
        abstractLogger = debugLogger;
    }

    public static void main(String[] args) {
        abstractLogger.logMessage(AbstractLogger.INFO,"qijy");
    }
}
