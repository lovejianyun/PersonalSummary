package com.qijy.designmodels.decorator;

public class Decorator implements Component{

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void saylove() {
        component.saylove();
    }
}
