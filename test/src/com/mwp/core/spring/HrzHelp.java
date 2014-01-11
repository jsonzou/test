package com.mwp.core.spring;
import java.text.MessageFormat;
import java.util.Map;

import com.mwp.core.baseservice.SuperDaoService;
public abstract class HrzHelp {  
	public static <T> T getService(Class<T> type) {	
		return Help.getBeanByType(type);		
	}
	   
    public static <T> T getService(String id){
        return Help.getBean(id);
        
    }
	
	public static <T> Map<String,T>  getServices(Class<T> type) {	
		return Help.getBeansOfType(type);
	}
	public static <T> T getDao(Class<T> type) {
		return Help.getBeanByType(type);
	}



	 
	

	public static String getMessage(String key,Object...params ) {
		return MessageFormat.format(HrzHelp.getMessage(key), params);

	}
	
    public static SuperDaoService getService(){
        return Help.getBean("superDaoService");
    }
 
	public static void main(String[] args){
		

		
	}



}
