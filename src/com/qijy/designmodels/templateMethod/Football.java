package com.qijy.designmodels.templateMethod;

public class Football extends Game {
    @Override
    void initialize() {
        System.out.println("足球游戏开始加载......");
    }

    @Override
    void startPlay() {
        System.out.println("足球游戏开始......");
    }

    @Override
    void endPlay() {
        System.out.println("足球游戏结束......");
    }
}
