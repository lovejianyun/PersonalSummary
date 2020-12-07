package com.qijy.designmodels.templateMethod;

public class Cricket extends Game{

    @Override
    void initialize() {
        System.out.println("蟋蟀游戏加载中");
    }

    @Override
    void startPlay() {
        System.out.println("蟋蟀游戏开始");
    }

    @Override
    void endPlay() {
        System.out.println("蟋蟀游戏结束");
    }
}
