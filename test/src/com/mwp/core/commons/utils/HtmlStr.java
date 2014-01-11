package com.mwp.core.commons.utils;

public final class HtmlStr {
	
	public static String blankIfEmpty(Object o) {
		if (o == null) {
			return "&nbsp;";
		}
		String str = o.toString().trim();
		return str.length() == 0 ? "&nbsp;" : str;
	}

	public static String htmlEncode(String s) {
		if (s == null)
			return "";
		StringBuilder str = new StringBuilder();
		for (int j = 0; j < s.length(); j++) {
			char c = s.charAt(j);
			switch (c) {
			case '"':
				str.append("&quot;");
				break;
			case '&':
				str.append("&amp;");
				break;
			case '<':
				str.append("&lt;");
				break;
			case '>':
				str.append("&gt;");
				break;
			case '\'':
				str.append("&#039;");
				break;
			case '\t':
			case ' ':
				str.append("&nbsp;");
				break;
			default:
				str.append(c);
				break;
			}
		}
		return str.toString();
	}

	public static String htmlEncode(char c) {
		switch (c) {
		case '"':
			return "&quot;";
		case '&':
			return "&amp;";
		case '<':
			return "&lt;";
		case '>':
			return "&gt;";
		case '\'':
			return "&#039;";
		case '\t':
		case ' ':
			return "&nbsp;";
		default:
			return "" + c;
		}
	}
}
