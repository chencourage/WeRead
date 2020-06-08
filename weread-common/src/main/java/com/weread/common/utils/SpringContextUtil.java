package com.weread.common.utils;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil implements ApplicationContextAware{
	
	private static ApplicationContext appContext;

	public static <T>T getBean(Class<T> type){
		try {
			return appContext.getBean(type);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static <T>Collection<T> getBeans(Class<T> type) {
		Map<String,T> map = appContext.getBeansOfType(type);
		return map.values();
	}
	
	public static <T>T getBean(String beanName,Class<T> clazz){
		return appContext.getBean(beanName, clazz);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		appContext = applicationContext;
		
	}

}
