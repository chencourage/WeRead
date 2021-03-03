package com.weread.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author lisheng
 * @date 2018年12月15日
 */
public class CopyUtils {

	/**
	 * 属性复制，复制名称相同，类型相同的属性，跳过空字段
	 * @param source	源对象
	 * @param target	目标对象
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static void copyProperties(Object source, Object target) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if(null != source && null != target) {
			Class<?> sourceClass = source.getClass();//得到对象的Class
			Class<?> targetClass = target.getClass();//得到对象的Class
			
			Map<Field, Class<?>> sourceFieldClassMap = getFieldClassMap(sourceClass);
			Set<Field> sourceFields = sourceFieldClassMap.keySet();
			Map<String, Field> targetNameFieldMap = getNameFieldMap(targetClass);
			
			for(Field sourceField : sourceFields) {
				String name = sourceField.getName();//属性名
				boolean sourceAccessFlag = false;
				if(!sourceField.isAccessible()) {
					sourceField.setAccessible(true); // 设置可访问
					sourceAccessFlag = true;
				}
				Object value = sourceField.get(source);
				if(null != value && 0 != value.toString().trim().length()) {
					Field targetField = targetNameFieldMap.get(name);
					if(null == targetField || sourceField.getType() != targetField.getType()) {
						continue;
					}
					boolean targetAccessFlag = false;
					if(!targetField.isAccessible()) {
						targetField.setAccessible(true); // 设置可访问
						targetAccessFlag = true;
					}
					targetField.set(target, value); // 从源对象get值set到目标对象
					if(targetAccessFlag) {
						targetField.setAccessible(false);
					}
				}
				if(sourceAccessFlag) {
					sourceField.setAccessible(false);
				}
				
			}
		}
	}
	
	private static Map<Field, Class<?>> getFieldClassMap(Class<?> clazz) {
		Map<Field, Class<?>> map = new HashMap<Field, Class<?>>();
		while(Object.class != clazz) {
			Field[] fields = clazz.getDeclaredFields();//得到Class对象的所有属性
			if(null != fields) {
				for(Field field : fields) {
					if(Modifier.isFinal(field.getModifiers())) {
						continue;
					}
					map.put(field, clazz);
				}
			}
			clazz = clazz.getSuperclass();
		}
		return map;
	}
	
	private static Map<String, Field> getNameFieldMap(Class<?> clazz) {
		Map<String, Field> map = new HashMap<String, Field>();
		while(Object.class != clazz) {
			Field[] fields = clazz.getDeclaredFields();//得到Class对象的所有属性
			if(null != fields) {
				for(Field field : fields) {
					if(Modifier.isFinal(field.getModifiers())) {
						continue;
					}
					map.put(field.getName(), field);
				}
			}
			clazz = clazz.getSuperclass();
		}
		return map;
	}
}
