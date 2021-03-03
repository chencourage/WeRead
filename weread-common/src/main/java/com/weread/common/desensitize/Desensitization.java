package com.weread.common.desensitize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *	需要脱敏的对象
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Desensitization {
	
	/** 
	 * 脱敏规则类型
	 */ 
	DesensitionType type(); 
	
	/**
	 * 附加值, 自定义正则表达式等
	 */ 
	String[] attach() default "";
}	
