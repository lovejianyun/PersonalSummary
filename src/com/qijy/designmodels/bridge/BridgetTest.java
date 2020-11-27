package com.qijy.designmodels.bridge;

public class BridgetTest {
    public static void main(String[] args) {
        Color red = new Red();
        Color yellow = new Yellow();
        Bag songl = new SonglBag(red);
        Bag qijy = new QijyBag(yellow);
        System.out.println(songl.getName());
        System.out.println(qijy.getName());
    }
}
