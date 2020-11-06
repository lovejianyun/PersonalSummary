package com.qijy.designmodels.dcl;

public class InnerStaticSigle {
    private InnerStaticSigle() {
    }

    private static class InstanceHolder{
        final static  InnerStaticSigle instance = new InnerStaticSigle();
    }

    public InnerStaticSigle getInstance(){
        return InstanceHolder.instance;
    }
}
