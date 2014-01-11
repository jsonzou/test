package com.biz.test;

import java.io.File;
import java.text.SimpleDateFormat;

public class Test {
	
public static void main(String[] args) {
	/*String content="${uname}先生，你好！您${birthday}的生日快到了，给您送一个礼物---${piao}!祝您生日快乐！";
	Map<String ,String> data =new HashMap<String ,String>();
	data.put("uname","刘大千");
	data.put("birthday","10-10");
	data.put("piao","回家的火车票");
	
	Pattern ptn=null;
	 Matcher m;
	for (Entry<String, String> entry :data.entrySet()) {
		      ptn=Pattern.compile("\\$\\{"+entry.getKey()+"\\}");
		      m=ptn.matcher(content);
		      while(m.find()){
		    	  content= m.replaceAll(entry.getValue());
		      }
	}
	System.out.println(content);
	*/
	
	
	System.out.println(new File("d:/pom.xml").lastModified());
	
	 String ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
     .format((new File("d:/pom.xml").lastModified()));
	 System.out.println(ss);
}
}
