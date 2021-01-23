package com.qijy.collections.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DateTime {
    String name() default "";
    String type() default "";
}
