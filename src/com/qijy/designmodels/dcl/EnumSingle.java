package com.qijy.designmodels.dcl;
/*
 * @ Description   :  枚举单利,多线程安全
 * @ Author        :  qijy
 * @ CreateDate    :  2020/11/4 14:07
 */
public enum  EnumSingle {
    INSTANCE;
    private EnumSingle(){}

    public EnumSingle getInstance(){
        return INSTANCE;
    }
}
