package com.qijy.designmodels.dcl;
/*
 * @ Description   :  懒加载 双重检测锁
 * @ Author        :  qijy
 * @ CreateDate    :  2020/11/4 13:54
 */
public class LazySingle {
    private static volatile LazySingle instance = null;

    private LazySingle() {
    }

    public static LazySingle getInstance(){
        if (null == instance){
            synchronized (LazySingle.class){
                if (null == instance){
                    instance = new LazySingle();
                }
            }
        }
        return instance;
    }
}
