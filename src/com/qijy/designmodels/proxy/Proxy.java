package com.qijy.designmodels.proxy;

public class Proxy implements Subject {
    private RealSubject realSubject;

    @Override
    public void request() {
        if(realSubject == null){
            realSubject = new RealSubject();
        }
        preRequest();
        realSubject.request();
        postRequest();


    }

    private void preRequest(){
        System.out.println("开心");
    }

    private void postRequest(){
        System.out.println("好开心");
    }
}
