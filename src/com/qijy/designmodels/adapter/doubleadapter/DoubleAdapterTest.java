package com.qijy.designmodels.adapter.doubleadapter;
/*
 * @ Description   :  双向适配
 * @ Author        :  qijy
 * @ CreateDate    :  2020/11/26 13:47
 */
public class DoubleAdapterTest {
    public static void main(String[] args) {
        MyAdapterImpl myAdapter = new MyAdapterImpl();
        MyAdaptee myAdaptee = new MyAdaptee(myAdapter);
        myAdaptee.speaklove();
        MyTarget myTarget = new MyTargetImpl();
        MyAdaptee myAdaptee1 = new MyAdaptee(myTarget);
        myAdaptee1.acceptlove();
    }
}
