package com.qijy.designmodels.facade;

public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("draw:rectangle");
    }
}
