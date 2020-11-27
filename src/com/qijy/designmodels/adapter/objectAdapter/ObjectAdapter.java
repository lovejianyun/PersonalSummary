package com.qijy.designmodels.adapter.objectAdapter;

import com.qijy.designmodels.adapter.classAdapter.Adaptee;
import com.qijy.designmodels.adapter.classAdapter.Target;

public class ObjectAdapter implements Target {
    private Adaptee adaptee;

    public ObjectAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.sayLove();
    }
}
