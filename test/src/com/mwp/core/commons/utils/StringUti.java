package com.mwp.core.commons.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

public class StringUti extends StringUtils {

	public static String ClassToLowName(Class<?> c) {

		return uncapitalize(c.getSimpleName());

	}

	public static String append(String... args) {

		StringBuilder sb = new StringBuilder();

		for (String arg : args) {
			sb.append(arg);
		}
		return sb.toString();

	}
	public static Boolean containsUpperAndLowerLetter(String str){
		   if(StringUti.isEmpty(str)){return false;}
		   if(str.length()==1){return false;}
           Pattern pattern = Pattern.compile(".*?[a-z]+.*?[A-Z]+.*?");
		   Matcher matcher = pattern.matcher(str);
		   return matcher.find();
	}
	public static void main(String[] args) {
		System.out.println(containsUpperAndLowerLetter("Ba4b5B"));
	}
}
