/* 
 * Copyright (C) 2012,物联速通, 
 * All Rights Reserved 
 * 
 * @project  
 * @author  admin
 * @date    2012-2-17-上午10:29:45
 *
 * Modification History: 
 **********************************************************
 * Date		  Author		    Comments
 * 2012-2-17	     admin			     Create
 **********************************************************
 */

package com.test.core.commons.utils;

import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @author admin
 * @date 2012-2-17-上午10:29:45
 */
public class UUIDUtils{
    
    private static Lock lock = new ReentrantLock();
    
    private static int  last16;
    
    public  static String getUUID() {
        lock.lock();
        String uuid_str = null;
        try{
            UUID uuid = UUID.randomUUID();
            Long ctm = System.currentTimeMillis();
            ctm = ctm + last16;
            if(last16 > 9999){
                last16 = 0;
            }
            last16++;
            
            Long uuid_ms = uuid.getMostSignificantBits();
            Long uuid_ls = uuid.getLeastSignificantBits();
            
            uuid_str = StrUtils.join(String.valueOf(ctm), String.valueOf(uuid_ms), String.valueOf(uuid_ls));
            uuid_str = StrUtils.replace(uuid_str, "-", "");
            
        }finally{
            lock.unlock();
        }
        return uuid_str;
    }
    
    public synchronized static String getUUID(int number) {
        return StrUtils.left(getUUID(), number);
    }
    
    public static void main(String[] args){
    
    	
    	System.out.println(getUUID());
    }
    
}
