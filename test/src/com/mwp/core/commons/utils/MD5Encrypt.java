package com.mwp.core.commons.utils;
//--------------------------------- IMPORTS ------------------------------------
import java.security.MessageDigest;

public class MD5Encrypt {
    //--------------------------- PUBLIC STATIC FIELDS -----------------------------
    //-------------------------- PROTECTED STATIC FIELDS ---------------------------
    //------------------------ PACKAGE-ONLY STATIC FIELDS --------------------------
    //-------------------------- PRIVATE STATIC   FIELDS -----------------------------÷
    private final static String[] hexDigits = {
        "0", "1", "2", "3", "4", "5", "6", "7",
        "8", "9", "a", "b", "c", "d", "e", "f"};
  
    /**
     * The private constructer.
     */
    private MD5Encrypt()
    {
    }
    
    /**
     * 转换字节数组为16进制字串
     * 
     * @param b 字节数组
     * @return 16进制字串
     */
    public static String byteArrayToString(byte[] b) 
    {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToNumString(b[i]));// 使用本函数则返回加密结果的10进制数字字串，即全数字形式
        }
        return resultSb.toString();
    }

  
    /**
     * 转换字节数组为16进制字串
     * 
     * @param b 字节数组
     * @return 16进制字串
     */
    public static String byteArrayToHexString(byte[] b) 
    {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            // Return Hex String
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }


    /**
     * @param origin plain text as source
     * @return return encoded test
     */
    public static String MD5Encode(String origin) 
    {
        String resultString = null;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (origin != null) {
                resultString = byteArrayToHexString(md.digest(origin.getBytes()));    
            }            
        }
        catch (Exception ex) {
            resultString = null;
        }
        
        return resultString;
    }
   
    /**
     * @param b byte
     * @return string as char string
     */
    private final static String byteToNumString(byte b) 
    {
        int _b = b;
        
        if (_b < 0) {
            _b = 256 + _b;
        }

        return String.valueOf(_b);
    }

  
    /**
     * @param b byte
     * @return string as Hex string
     */
    private static String byteToHexString(byte b) 
    {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
}