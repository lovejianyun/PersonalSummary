package com.qijy.annotations;

import java.lang.annotation.*;

@Target(value={ElementType.FIELD,ElementType.CONSTRUCTOR,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Qijy1 {
}
