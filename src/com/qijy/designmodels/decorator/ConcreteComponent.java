package com.qijy.designmodels.decorator;

public class ConcreteComponent implements Component {
    @Override
    public void saylove() {
        System.out.println("i love you");
    }
}
