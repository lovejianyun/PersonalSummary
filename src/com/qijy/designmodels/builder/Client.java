package com.qijy.designmodels.builder;

public class Client {
    public static void main(String[] args) {
        ConcreteBuilder concreteBuilder = new ConcreteBuilder();
        Director director = new Director(concreteBuilder);
        Product construct = director.construct();
        System.out.println(construct);
    }

}
