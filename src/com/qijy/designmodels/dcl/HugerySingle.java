package com.qijy.designmodels.dcl;
/*
 * @ Description   :  饿汉式单利模式
 * @ Author        :  qijy
 * @ CreateDate    :  2020/10/28 17:30
 */
public class HugerySingle {
    private HugerySingle instance = new HugerySingle();

    private HugerySingle() {
    }

    public HugerySingle getInstance(){
        return instance;
    }

}
