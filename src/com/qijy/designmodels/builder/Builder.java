package com.qijy.designmodels.builder;

public abstract class Builder {
    protected Product product = new Product();

    public abstract void builderPartA();
    public abstract void builderPartB();
    public abstract void builderPartC();

    public Product getProduct(){
        return product;
    }
}
