package com.qijy.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.CONSTRUCTOR)
@TestB
public @interface TestA {

}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
@interface TestB{

}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.LOCAL_VARIABLE)
@interface TestC{

}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@interface TestD{

}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface TestE{

}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_PARAMETER)
@interface TestF{

}
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
@interface TestG{

}


@TestE
class A <T> {
    private String name;
    private Class aClass;
    @TestA
    public A( String name) {
        this.name = name;
    }

    public void deom( String string){
        @TestC int a = 0;
        System.out.println(string);
    }

    public void demo01(@TestD String xxx){

    }
}
