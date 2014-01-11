package com.test.core.spring;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;

public abstract class Help {

	private static ApplicationContext APP = ApplicationConfig.getInstance()
	.getApplicationContext();
	
	
	private static final Log logger = LogFactory.getLog(Help.class);
	public static void setApplicationContext(ApplicationContext app) {
		APP = app;
	}

	@SuppressWarnings("unchecked")
    public static <T> T getBean(String beanId){
	    return (T) findBean(beanId);
	}
	
	
    public static <T> T getBeanByType(Class<T> type){
	    return APP.getBean(type);
	}
    
    public static <T> Map<String,T>  getBeansOfType(Class<T> type){
	    return APP.getBeansOfType(type);
	}
	
	public static Object findBean(String beanId) {
		Object service = null;
		service = APP.getBean(beanId);
		return service;
	}

	@SuppressWarnings(value = "all")
	public static Object findBeanOfType(Class clz) {
		if (logger.isDebugEnabled()) {
			logger.debug("findBeanOfType="+ (clz == null ? "" : clz.getName()));
		}
		if (clz == null) {
			return null;
		}
		Object service = null;
		Map<String, Object> serviceMap = APP.getBeansOfType(clz);
		Iterator<String> beanNames = serviceMap.keySet().iterator();
		while (beanNames.hasNext()) {
			Object instance = serviceMap.get(beanNames.next());
			if (instance.getClass().equals(clz)) {
				service = instance;
			} else if (AopUtils.isAopProxy(instance)) {
				service = instance;
				break;
			}
		}
		return service;
	}

	public static String getMessage(String key, Object[] params, Locale locale) {
		return APP.getMessage(key, params, locale);
	}
	
	public static String getMessage(String key, Object[] params) {
		return APP.getMessage(key, params, Locale.CHINA);
	}
	
	public static String getMessage(String key){
		return APP.getMessage(key, null, Locale.CHINA);
	}
	
}
