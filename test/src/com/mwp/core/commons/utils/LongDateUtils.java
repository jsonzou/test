

package com.mwp.core.commons.utils;

import java.text.ParseException;
import java.util.Date;


/**
 * 时间转换工具
 * @author 
 * @date 2013-2-23-下午01:21:35
 */
public  class LongDateUtils{
    public static final String DATEFORMAT_1 = "yyyy-MM-dd";
    public static final String DATEFORMAT_2 = "yyyy-MM-dd HH:mm:ss";
    public static final String DATEFORMAT_3 = "yyyy-MM-dd HH:mm";
    
    public static Date LongToDate(Long lnTime){
        Date da = new Date();
        da.setTime(lnTime);
        return da;
    }
    
    public static Long StrToLongTime(String dateStr, String parsePatterns) throws ParseException {
        return DateTimeUtils.parseDate(dateStr, parsePatterns).getTime();
    }
    
    public static Integer StrToIntegerTime(String dateStr, String parsePatterns) throws ParseException{
        Long ln = StrToLongTime(dateStr,parsePatterns);
        String strLn = String.valueOf(ln);
        String strInt = StrUtils.removeEnd(strLn, "000");
        return Integer.valueOf(strInt);
    }
    
    public static Date IntegerToDate(Integer intTime){
        String strInt = String.valueOf(intTime);
        String strLn = StrUtils.rightPad(strInt, 13, "0");
        Long ln = Long.valueOf(strLn);
        return LongToDate(ln);
    }
     
}
