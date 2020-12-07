package com.qijy.designmodels.facade;

public class FacadePatternDemo {
    public static void main(String[] args) {
        ShapeMaker shapeMaker = new ShapeMaker(new Rectangle(),new Square(),new Circle());
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();
        shapeMaker.drawCircle();
    }
}
