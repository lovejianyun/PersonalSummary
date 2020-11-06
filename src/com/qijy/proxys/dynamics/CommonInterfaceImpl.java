package com.qijy.proxys.dynamics;

public class CommonInterfaceImpl implements CommonInterface {
    @Override
    public void print(String name) {
        System.out.println("HELLO:" + name);
    }

    @Override
    public String printName(String name) {
        return "HELLO:"+name;
    }
}
