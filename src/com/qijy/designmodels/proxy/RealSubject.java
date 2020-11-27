package com.qijy.designmodels.proxy;

public class RealSubject implements Subject{
    @Override
    public void request() {
        System.out.println("qijy"+"    love    "+"songl");
    }
}
