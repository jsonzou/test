

package com.mwp.core.commons.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import com.alibaba.fastjson.JSONArray;


/**
 * 提供调用对象方法的简便方式
 * 
 * 
 * 
 */
public abstract class UtilMethod {
	/**
	 * 通过一个字符串调用对象的方法
	 * 
	 * @param methodStr
	 *            com.package.ClassName.methodName(p1, p2)
	 * @return
	 * @throws NoSuchMethodException
	 */
	static public Object invoke(String methodStr) throws NoSuchMethodException {
		int pos = methodStr.lastIndexOf('(');
		String clsNameAndMth = methodStr.substring(0, pos);
		String parametes = methodStr.substring(pos);
		pos = clsNameAndMth.lastIndexOf('.');
		String className = clsNameAndMth.substring(0, pos);
		String mothdName = clsNameAndMth.substring(pos + 1);
		parametes = parametes.replace('(', '[');
		parametes = parametes.replace(')', ']');
		JSONArray array = JSONArray.parseArray(parametes);
		Class<?> cls;
		try {
			cls = ClassUtils.getClass(className);
			Object[] args = array.toArray();
			Class<?>[] parameterTypes = extractClasses(args);
			Method method = MethodUtils.getAccessibleMethod(cls, mothdName, parameterTypes);
			if (method == null) {
				parameterTypes = tryArgsClass(parameterTypes);
				method = MethodUtils.getAccessibleMethod(cls, mothdName, parameterTypes);
			}
			if (method == null) {
				throw new NoSuchMethodException(String.format("UtilMethod do not find %s mothod", methodStr));
			}
			if (Modifier.isStatic(method.getModifiers())) {
				return method.invoke(null, args);
			} else {
				Object obj = null;
				if (ClassUtils.isAssignable(cls, Object.class)) {
					//obj = Tx.createSrv(cls);
					
				} else {
					obj = ConstructorUtils.invokeConstructor(cls);
				}
				return method.invoke(obj, args);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}

		return null;
	}

	static private Class<?>[] tryArgsClass(Class<?>[] classes) {
		Class<?>[] result = new Class<?>[classes.length];
		int i = 0;
		for (Class<?> cls : classes) {
			if (org.springframework.util.ClassUtils.isPrimitiveWrapper(cls)) {
				result[i++] = org.apache.commons.lang3.ClassUtils.wrapperToPrimitive(cls);
			} else {
				result[i++] = cls;
			}
		}
		return result;
	}

	static private Class<?>[] extractClasses(Object... args) {
		if (args == null || args.length == 0) {
			return new Class<?>[0];
		}
		Class<?>[] result = new Class<?>[args.length];
		int i = 0;
		for (Object object : args) {
			result[i++] = object.getClass();
		}
		return result;
	}
}
