package com.qijy.reflects;

import java.lang.reflect.Field;

public class ReflectTest {
    public static void main(String[] args) {
        Class<BReflects> bReflectsClass = BReflects.class;
        Field[] declaredFields = bReflectsClass.getDeclaredFields();
        Field[] declaredFields1 = bReflectsClass.getSuperclass().getDeclaredFields();
        System.out.println(declaredFields.length);
        System.out.println(declaredFields1.length);
    }
}
