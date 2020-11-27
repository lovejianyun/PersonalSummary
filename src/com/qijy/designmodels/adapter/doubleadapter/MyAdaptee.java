package com.qijy.designmodels.adapter.doubleadapter;

public class MyAdaptee implements MyAdapter,MyTarget {
    private MyTarget myTarget;
    private MyAdapter myAdapter;

    public MyAdaptee(MyTarget myTarget) {
        this.myTarget = myTarget;
    }

    public MyAdaptee(MyAdapter myAdapter) {
        this.myAdapter = myAdapter;
    }

    @Override
    public void acceptlove() {
        myTarget.speaklove();
    }

    @Override
    public void speaklove() {
        myAdapter.acceptlove();
    }
}
