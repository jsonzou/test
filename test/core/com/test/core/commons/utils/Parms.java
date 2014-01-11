package com.test.core.commons.utils;

import java.util.Map;

public class Parms {
	public static Map<String, Object> m(String name1, Object value1) {
		return UtilMisc.toMap(name1, value1);
	}

	public static Map<String, Object> m(String name1, Object value1, String name2, Object value2) {
		return UtilMisc.toMap(name1, value1, name2, value2);
	}

	public static Map<String, Object> m(String name1, Object value1, String name2, Object value2, String name3, Object value3) {
		return UtilMisc.toMap(name1, value1, name2, value2, name3, value3);
	}

	public static Map<String, Object> m(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4) {
		return UtilMisc.toMap(name1, value1, name2, value2, name3, value3, name4, value4);
	}
}
