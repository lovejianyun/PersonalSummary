package com.qijy.proxys.dynamics;

public class CommInterfaceImpl2 implements CommonInterface {
    @Override
    public void print(String name) {
        System.out.println("FOCK:" + name);
    }

    @Override
    public String printName(String name) {
        return "FOCK:"+name;
    }
}
