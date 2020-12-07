package com.qijy.designmodels.decorator;

public class DecoratorTest {
    public static void main(String[] args) {
        // 数据增强型
        Component component = new ConcreteComponent();
        ConcreteDecorator concreteDecorator = new ConcreteDecorator(component);
        concreteDecorator.saylove();
    }
}
