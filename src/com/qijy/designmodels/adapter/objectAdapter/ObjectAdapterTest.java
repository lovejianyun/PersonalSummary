package com.qijy.designmodels.adapter.objectAdapter;

import com.qijy.designmodels.adapter.classAdapter.Adaptee;
import com.qijy.designmodels.adapter.classAdapter.Target;

public class ObjectAdapterTest {
    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        Target target = new ObjectAdapter(adaptee);
        target.request();
    }
}
