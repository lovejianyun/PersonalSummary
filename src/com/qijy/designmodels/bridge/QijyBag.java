package com.qijy.designmodels.bridge;

public class QijyBag extends Bag {
    public QijyBag(Color color) {
        super(color);
    }
    @Override
    String getName() {
        return color.getColor()+":qijyBag";
    }
}
