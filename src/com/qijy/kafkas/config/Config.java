package com.qijy.kafkas.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Config {
	static final String PASSWORD = "PASSWORD";
	//配置节点
	String sec() default "";
	//配置项
	String name() default "";
	//字段类型
	String type() default "";
	//是否自动加载
	boolean reload() default false;
	
	/**
	 * 针对list类型数据，拆分直接转换为ArrayList
	 * 针对Set类型数据，拆分直接转换为HashSet
	 * @return
	 */
	String split() default "\\|";
}
