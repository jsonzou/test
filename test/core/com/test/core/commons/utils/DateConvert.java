package com.test.core.commons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.beanutils.Converter;

public class DateConvert implements Converter {  
    
    public Object convert(Class arg0, Object arg1) {  
    	if(!(arg1 instanceof java.util.Date)){
        String p = (String)arg1;  
        if(p== null || p.trim().length()==0){  
            return null;  
        }     
        try{  
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
            return df.parse(p.trim());  
        }  
        catch(Exception e){  
        	System.out.println(e);
            try {  
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
                return df.parse(p.trim());  
            } catch (ParseException ex) {  
            	System.out.println(ex);
                return null;  
            }  
        }  
          
    }  
    	else{
    		
    		return arg1;
    	}
    }
  
}  
