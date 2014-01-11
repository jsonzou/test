package com.test.core.commons.utils;

public class SNGenerator {

    private static final SNGenerator instance = new SNGenerator();
    private static final int MILLSEC_IN_SEC = 1000;
    private static final int TIME_SHITF_BITS = 32;

    private int seq = 0;
  
    /**
     * Constructor
     * Initializes this SNGenerator object
     */
    private SNGenerator() 
    {    
    }

    /**
     * getInstance
     * Get SNGenerator instance
     * @return the newly created SNGenerator
     */
    public static SNGenerator getInstance() 
    {
        return (instance);
    }


    /**
     * getSN
     * Get SNGenerator number
     * @param id the id of service record
     * @return the newly Serial Number
     */
    public String getSN(String id) 
    {
        long time = System.currentTimeMillis() / MILLSEC_IN_SEC;
        long number = (time << TIME_SHITF_BITS) | incSeq();
      
        String oldstr = id + number;
        
        /* MD5 Hash */
        return MD5Encrypt.MD5Encode(oldstr);
    }

  
    /**
     * incSeq
     * Incr Sequence number
     * @return the newly Sequence Number
     */
    private synchronized int incSeq() 
    {
        seq++;
        return (seq);
    }
}