package com.qijy.proxys.dynamics;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/*
 * @ Description   :  代理类对象
 * @ Author        :  qijy
 * @ CreateDate    :  2020/9/1 16:33
 */
public class CommInterfaceProxy implements InvocationHandler {
    private CommonInterface commonInterface;

    public CommInterfaceProxy(CommonInterface commonInterface) {
        this.commonInterface = commonInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("------begin------");
        // 执行被代理对象的方法
        Object invoke = method.invoke(commonInterface, args);
        if(null != invoke){
            System.out.println(invoke.toString());
        }
        System.out.println(("--------end--------"));
        return invoke;
    }
}
