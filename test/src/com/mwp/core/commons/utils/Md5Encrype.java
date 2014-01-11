
package com.mwp.core.commons.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Encrype
{
    public  static String  encryptPasswords(final String loginname,final String password ){
        String pass = password;
        StringBuffer buf = new StringBuffer();
        buf.append(loginname.trim());
        buf.append(password.trim());
        buf.append("tm");
        
        pass = encrypt(buf.toString());
 //       System.out.println("loginname"+":"+loginname);
 //       System.out.println("password"+":"+password);
 //       System.out.println("md5"+":"+pass);
        return pass;
    }    
 
    
  
    
    public static String encrypt(String txt){
        String enTxt = txt;
        MessageDigest md = null;
        try {
        md = MessageDigest.getInstance("MD5");
       } catch (NoSuchAlgorithmException e) {
           //logger.error("Error:", e);
           e.printStackTrace();
       }
       if(null != md){
            byte[] md5hash = new byte[32];
            try {
               md.update(txt.getBytes("UTF-8"), 0, txt.length());
           } catch (UnsupportedEncodingException e) {
               e.printStackTrace();
           }
            md5hash = md.digest();
            StringBuffer md5StrBuff = new StringBuffer();  
            for (int i = 0; i < md5hash.length; i++) {              
                if (Integer.toHexString(0xFF & md5hash[i]).length() == 1) {
                    md5StrBuff.append("0").append(Integer.toHexString(0xFF & md5hash[i]));   
                } else {
                    md5StrBuff.append(Integer.toHexString(0xFF & md5hash[i]));
                }  
            }  
            enTxt = md5StrBuff.toString();
       }
       return enTxt;
   }
    public static void main(String args[]){
        System.out.println("adminsz@"+Md5Encrype.encryptPasswords("admin","123456"));
        System.out.println("super1@"+Md5Encrype.encryptPasswords("super1","123456"));
        System.out.println("114clientsz@"+Md5Encrype.encryptPasswords("114clientsz","123456"));
    }
}
