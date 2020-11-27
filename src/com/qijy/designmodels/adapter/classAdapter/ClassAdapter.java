package com.qijy.designmodels.adapter.classAdapter;

public class ClassAdapter extends Adaptee implements Target {
    @Override
    public void request() {
        super.sayLove();
    }
}
