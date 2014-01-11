package com.test.core.commons.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class StrUtils extends StringUtils{

	public static final String _SPLIT = "_";
	
	public static String encode(String str){
		if(!StringUtils.isBlank(str)){
			try {
				return URLEncoder.encode(str, "utf-8");
			} catch (UnsupportedEncodingException e) {
				
			 
			}
		}
		return null;
	}
	
	public static String decode(String str){
		if(!StringUtils.isBlank(str)){
			try {
				return URLDecoder.decode(str, "utf-8");
			} catch (UnsupportedEncodingException e) {
				
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param url
	 * @return
	 */
    public static Map<String, String> getQuerys(String url) {
        Map<String, String> params = new HashMap<String, String>();
        if(StrUtils.isNotBlank(url)){
            String query = StrUtils.substringAfterLast(url, "?");
            if(StrUtils.isNotBlank(query)){
                for(String q: StrUtils.splitByWholeSeparator(query, "&")){
                    if(StrUtils.isNotBlank(q)){
                        String[] qs = StrUtils.splitByWholeSeparator(q, "=");
                        if(2 == qs.length){
                            String key = StrUtils.isNotBlank(qs[0]) ? qs[0] : "";
                            String value = StrUtils.isNotBlank(qs[1]) ? qs[1] : "";
                            if(StrUtils.isNotBlank(key)){
                                params.put(key, value);
                            }
                        }
                    }
                }
            }
        }
        return params;
    }

	/**
	 * 
	 * @param columnName
	 * @return
	 */
	public static String getAttrName(String columnName,String split) {
		if(isEmpty(columnName)){
			return null;
		}
		
		String[] strs = StrUtils.splitByWholeSeparator(columnName, split);
		StringBuffer sbf = new StringBuffer();
		for (int i = 0; i < strs.length; i++) {
			if (i == 0) {
				sbf.append(StrUtils.lowerCase(strs[i]));
			} else {
				sbf.append(StrUtils.capitalize(StrUtils.lowerCase(strs[i])));
			}
		}
		return sbf.toString();
	}
	
	/**
	 * 
	 * @param columnName
	 * @return
	 */
	public static <T> String getCompartValues(T[] values,String aroundSymbolBegin,String aroundSymbolEnd,String compartSymbol,String quotoSymbol){
		String ret=new String(ArrayUtils.toString(values, ""));
		quotoSymbol=quotoSymbol==null?"":quotoSymbol;
		aroundSymbolBegin=aroundSymbolBegin==null?"":aroundSymbolBegin;
		aroundSymbolEnd=aroundSymbolEnd==null?"":aroundSymbolEnd;
		compartSymbol=compartSymbol==null||"".equals(compartSymbol)?",":compartSymbol;
		return 
		ret.replace("{",aroundSymbolBegin+quotoSymbol)
		.replace("}",quotoSymbol+aroundSymbolEnd)
		.replace(",",quotoSymbol+compartSymbol+quotoSymbol); 
	}
	/**
	 * 
	 * @param str 要分割的字符串
	 * @param split 分隔符，默认‘,’
	 * @return
	 */
	public static Integer[] splitAsInegerArray(String str,String split){
		if(isNotEmpty(str)){
			String[] tempS=null;
			Integer[] tempI=null;
			if(isNotEmpty(split)){
				tempS=str.split(split);
			}else{
				tempS=str.split(",");
			}
		   if(tempS!=null){
			   tempI=new Integer[tempS.length];
				for(int i=0;i<tempS.length;i++){
					tempI[i]=Integer.parseInt(tempS[i]);
				} 
				return tempI;
			}
		}
		return null;
	}
	/**
	 * @function 位数不够，数前补零0
	 * @author JsonZou
	 * @return String
	 */
	public static String formatNum(int num,int len){
		String numStr=num+"";
		int _len=numStr.length();
		 while(_len<len){
			 numStr="0"+numStr;
			 _len++;
		 }
		 return numStr;
	}
	public static void main(String[] args) {
		int a=12;
		System.out.println(formatNum(a,4));
	}
}
