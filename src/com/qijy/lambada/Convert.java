package com.qijy.lambada;

public interface Convert<A,B> {
    B convert (A a);
    default void getxxxx(){
        System.out.println("xxxxxxxx");
    }
}
