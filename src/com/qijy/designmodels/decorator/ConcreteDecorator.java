package com.qijy.designmodels.decorator;

public class ConcreteDecorator extends Decorator {
    public ConcreteDecorator(Component component) {
        super(component);
    }

    @Override
    public void saylove() {
        songli();
        super.saylove();
    }

    public void songli(){
        System.out.println("宋丽:");
    }
}
