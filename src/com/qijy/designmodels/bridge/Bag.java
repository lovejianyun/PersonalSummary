package com.qijy.designmodels.bridge;

public abstract class Bag {
    protected Color color;

    public Bag(Color color) {
        this.color = color;
    }

    abstract String getName();
}
