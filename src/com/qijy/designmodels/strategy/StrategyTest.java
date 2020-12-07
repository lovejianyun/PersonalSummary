package com.qijy.designmodels.strategy;

public class StrategyTest {
    public static void main(String[] args) {
        String [] keys = new String[]{"+","-","*"};
        Context context = new Context(keys);
        System.out.println(context.excute(10, 2, "*"));

    }
}
