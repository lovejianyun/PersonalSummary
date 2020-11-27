package com.qijy.designmodels.bridge;

public class SonglBag extends Bag {
    public SonglBag(Color color) {
        super(color);
    }

    @Override
    String getName() {
        return color.getColor()+":songlBag";
    }
}
