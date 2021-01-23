package com.qijy.collections.annotation;

import java.lang.annotation.*;

/*
 * @ Description   :  判断是否为模糊查询
 * @ Author        :  qijy
 * @ CreateDate    :  2021/1/21 13:28
 */
@Target(ElementType.FIELD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface FiledLike {

}
