package com.qijy.collections.annotation;

import java.lang.annotation.*;

/*
 * @ Description   :  忽略字段注解
 * @ Author        :  qijy
 * @ CreateDate    :  2021/1/22 15:38
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreData {
}
