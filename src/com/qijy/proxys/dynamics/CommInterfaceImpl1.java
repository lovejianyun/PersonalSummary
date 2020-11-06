package com.qijy.proxys.dynamics;

public class CommInterfaceImpl1 implements CommonInterface {
    @Override
    public void print(String name) {
        System.out.println("hello:"+name);
    }

    @Override
    public String printName(String name) {
        return "hello:"+name;
    }
}
