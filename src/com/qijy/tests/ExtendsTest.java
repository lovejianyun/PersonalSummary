package com.qijy.tests;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author qijy
 * @version 1.0
 * @description: TODO
 * @date 2023/2/7 15:40
 */
public class ExtendsTest {
    public static void main(String[] args) {
        // java 只支持向上转型，不支持向下转型
//        A a = new A();
//        B b = new B();
//        A x = (A) b;
//        B xb = (B)A;

        System.out.println(3*0.1 == 0.3);
        // a = a+b a+=b 的区别
        int a = 1;
        double b=0.1d;
        // 由于不支持数据数字运算由高精度转低精度 编译报错
//        a = a+b;
        a += b;
        System.out.println(a);

        //
        A<String> x = new A<String>(){};
        Type genericSuperclass = x.getClass().getGenericSuperclass();
        Type type = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
        System.out.println(type);

        Array.newInstance(String.class, 10);

    }
}
