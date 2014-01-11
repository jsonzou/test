

package com.test.core.commons.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 定义一些常用的字符串操作函数
 * 
 * 
 * 
 */
public class UtilString {
	public static void main(String[] args) throws Exception {
		String s = encodeHex("你好94", "gbk");
		System.out.println(s);
		System.out.println(decodeHex(s, "gbk"));
	}

	/**
	 * 分隔字符串 k=v&k1=v1&k2=v2
	 * 
	 * @param s
	 * @param c1
	 *            &
	 * @param c2
	 *            =
	 * @return
	 */
	public static Map<String, String> splitToMap(String s, char c1, char c2) {
		Map<String, String> result = new LinkedHashMap<String, String>();
		String[] ss1 = StringUtils.split(s, c1);
		for (String line : ss1) {
			int offset = line.indexOf(c2);
			if (offset < 0) {
				continue;
			}
			String beforeDelimiter = escapeSql(line.substring(0, offset));
			String afterDelimiter = escapeSql(line.substring(offset + 1));
			result.put(StringUtils.strip(beforeDelimiter), StringUtils.strip(afterDelimiter));
		}
		return result;
	}

	public static List<String> splitToList(String s, char c) {
		List<String> list = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		int len = s.length();
		for (int i = 0; i < len; ++i) {
			char tc = s.charAt(i);
			if (Character.isSpaceChar(tc)) {
				continue;
			}
			if (tc == c) {
				list.add(sb.toString());
				sb.setLength(0);
			} else {
				sb.append(tc);
			}
		}
		if (sb.length() > 0) {
			list.add(sb.toString());
		}
		// return list.toArray(new String[list.size()]);
		return list;
	}

	public static String escapeSql(String str) {
		return StringUtils.replace(str, "'", "''");
	}

	public static String unescapeSql(String str) {
		return StringUtils.replace(str, "''", "'");
	}

	public static String mapToString(Map<String, String> map, char c1, char c2) {
		if (MapUtils.isEmpty(map)) {
			return StringUtils.EMPTY;
		}
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> ent : map.entrySet()) {
			sb.append(ent.getKey()).append(c2).append(ent.getValue()).append(c1);
		}
		sb.setLength(sb.length() - 1);
		return sb.toString();
	}

	public static final String encodeHex(String s, String enc) {
		try {
			return new String(Hex.encodeHex(s.getBytes(enc), true));
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(enc + ": " + e);
		}
	}

	public static final String decodeHex(String s, String enc) {
		try {
			return org.apache.commons.codec.binary.StringUtils.newString(Hex.decodeHex(s.toCharArray()), enc);
		} catch (DecoderException e) {
			throw new IllegalStateException(enc + ": " + e);
		}
	}

	public static final String ereaseZeros(String str) {
		str = str.trim();
		int len = str.length();
		int count = str.length();
		while (str.charAt(len - 1) == '0') {
			len--;
		}
		while (str.charAt(len - 1) == '.') {
			len--;
		}
		return len < count ? str.substring(0, len) : str;
	}

	static public void convertUnderlineToUppercase(String str, StringBuilder sb) {
		try {
			sb.setLength(0);
			for (int i = 0; i < str.length(); ++i) {
				char c = str.charAt(i);
				if (c == '_') {
					c = str.charAt(++i);
					sb.append(Character.toUpperCase(c));
				} else {
					sb.append(Character.toLowerCase(c));
				}
			}
		} catch (StringIndexOutOfBoundsException e) {
		}
	}

	/**
	 * 将对象转为字符串，如果对象为空则返回空字符串，其余返回String.valueOf()
	 * 
	 * @param obj
	 *            转为String的对象
	 * @return ，如果对象为空则返回空字符串，其余返回String.valueOf()
	 */
	public static String toString(Object obj) {
		return obj == null ? "" : String.valueOf(obj);
	}

	public static String str(String... elements) {
		StringBuilder sb = new StringBuilder();
		for (String s : elements) {
			sb.append(s);
		}
		return sb.toString();
	}

	public static String str(char slipstr, String... elements) {
		StringBuilder sb = new StringBuilder();
		for (String s : elements) {
			sb.append(s).append(slipstr);
		}
		sb.setLength(sb.length() - 1);
		return sb.toString();
	}

	public static boolean isMobile(String mobile) {
		Pattern p = Pattern.compile("1\\d{10}");
		Matcher m = p.matcher(mobile);
		return m.matches();
	}
}
