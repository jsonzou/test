package com.test.core.commons.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

public class MethodParse {
	private Class<?> declaringClass;
	private String   methodName;
	
	public MethodParse(String methodStr) throws ClassNotFoundException {
		int pos = methodStr.lastIndexOf('.');
		String className = methodStr.substring(0, pos);
		methodName = methodStr.substring(pos + 1);
		declaringClass = ClassUtils.getClass(className);
	}

	public Class<?> getDeclaringClass() {
		return declaringClass;
	}

	public String getMethodName() {
		return methodName;
	}
	
	public Method getMethod(Class<?>... parameterTypes) throws NoSuchMethodException {
		Method method = MethodUtils.getAccessibleMethod(declaringClass, methodName, parameterTypes);
		if (method == null) {
			throw new NoSuchMethodException(String.format("MethodParse do not find %s.%s mothod",declaringClass.toString(), methodName));
		}
		return method;
	}
	
	public Object invoke(Object obj, Object...args) throws NoSuchMethodException {
		try {
			return getMethod(extractClasses(args)).invoke(obj, args);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (InvocationTargetException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	private Class<?>[] extractClasses(Object... args) {
		if (args == null || args.length == 0) {
			return new Class<?>[0];
		}
		Class<?>[] result = new Class<?>[args.length];
		int i = 0;
		for (Object object : args) {
			Class<?> cls = object.getClass();
			if (org.springframework.util.ClassUtils.isPrimitiveWrapper(cls)) {
				result[i++] = org.apache.commons.lang3.ClassUtils.wrapperToPrimitive(cls);
			} else {
				result[i++] = cls;
			}
		}
		return result;
	}
	
}
