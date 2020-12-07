package com.qijy.designmodels.facade;
/*
 * @ Description   :  门面
 * @ Author        :  qijy
 * @ CreateDate    :  2020/11/27 14:20
 */
public class ShapeMaker {
    private Shape rectangle;
    private Shape square;
    private Shape circle;

    public ShapeMaker(Shape rectangle, Shape square, Shape circle) {
        this.rectangle = rectangle;
        this.square = square;
        this.circle = circle;
    }

    public void drawRectangle(){
        rectangle.draw();
    }

    public void drawSquare(){
        square.draw();
    }

    public void drawCircle(){
        circle.draw();
    }
}
