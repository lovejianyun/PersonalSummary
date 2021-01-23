package com.qijy.reflects.array;

import java.lang.reflect.Array;

/*
 * @ Description   :  创建动态数组,并且求最小值
 * @ Author        :  qijy
 * @ CreateDate    :  2021/1/23 17:14
 */
public class DaynmicArray {
    public  <T extends Comparable<T>> void min(T[] a) {
        //通过反射创建相同类型的数组
        T[] b = (T[]) Array.newInstance(a.getClass().getComponentType(), a.length);
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        T min = null;
        boolean flag = true;
        for (int i = 0; i < b.length; i++) {
            if (flag) {
                min = b[i];
                flag = false;
            }
            if (b[i].compareTo(min) < 0) {
                min = b[i];
            }
        }
        System.out.println(min);
    }
}
