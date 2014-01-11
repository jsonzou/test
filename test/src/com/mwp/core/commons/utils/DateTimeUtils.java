
package com.mwp.core.commons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 遍历两个日期之间天数的算法
 */
public class DateTimeUtils extends DateUtils{
    
    public static void main(String[] args) {
        try{
            Date d = min(null, null,null);
            System.out.println(DateFormatUtils.format(d, "yyyy-MM-dd"));
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public static Date min(Date... dates) {
        List<Date> dls = Arrays.asList(dates);
        sortDate(dls, "");
        if(CollectionUtils.isNotEmpty(dls)){
            return dls.iterator().next();
        }
        return null;
    }
    
    public static Date max(Date... dates) {
        List<Date> dls = Arrays.asList(dates);
        sortDate(dls, "desc");
        if(CollectionUtils.isNotEmpty(dls)){
            return dls.iterator().next();
        }
        return null;
    }
    
    public static List<Date> sortDate(List<Date> dates, final String descOrAsc) {
        Collections.sort(dates, new Comparator<Date>(){
            int t1 = 1;
            
            int t0 = 0;
            
            @Override
            public int compare(Date o1, Date o2) {
                if(StringUtils.isNotBlank(descOrAsc) && StringUtils.equalsIgnoreCase(descOrAsc, "desc")){
                    t1 = 0;
                    t0 = 1;
                }
                if(null != o1 && null != o2){
                    if(o1.after(o2)){
                        return t1;
                    }
                }else if(null == o1){
                    return 1;
                }else{
                    return 0;
                }
                return t0;
            }
        });
        return dates;
    }
    
    /**
     * 两个日期之间相差的天数
     * @param startDate
     * @param endDate
     * @return
     */
    public static int startToEndCount(Date startDate, Date endDate) {
        Calendar startTime = Calendar.getInstance();
        startTime.clear();
        startTime.setTime(startDate);
        
        int startYear = startTime.get(Calendar.YEAR);
        int startMonth = startTime.get(Calendar.MONTH);
        int startDay = startTime.get(Calendar.DAY_OF_MONTH);
        
        Calendar endTime = Calendar.getInstance();
        endTime.clear();
        endTime.setTime(endDate);
        int endYear = endTime.get(Calendar.YEAR);
        int endMonth = endTime.get(Calendar.MONTH);
        int endDay = endTime.get(Calendar.DAY_OF_MONTH);
        // System.out.println("注意西方的月份从0到11，中国的月份从1到12");
        // System.out.println("下面输入的是中国的日期.注意其中的转换问题");
        // System.out.println("start date : " + start);
        // System.out.println("end date : " + end);
        
        int count = 0;
        for(int x = startYear; x <= endYear; x++){
            // 罗马历法产生年份公元1582年
            int gregorianCutoverYear = 1582;
            boolean isLeapYear = x >= gregorianCutoverYear ? ((x % 4 == 0) && ((x % 100 != 0) || (x % 400 == 0))) : (x % 4 == 0);
            // 判断是否是闰年
            // java方法
            // boolean isLeapYear = (new GregorianCalendar()).isLeapYear(x);
            
            @SuppressWarnings("unused")
            String isBigYear = "是平年";
            if(isLeapYear){
                isBigYear = "是闰年";
            }
            // System.out.println(x + "年" + isBigYear);
            
            // 获取开始月的最大天数
            // java方法
            // SimpleDateFormat aFormat=new SimpleDateFormat("yyyy-MM-dd");
            // Date date = aFormat.parse(start);
            // Calendar time = Calendar.getInstance();
            // time.clear();
            // time.setTime(date);
            // int max=time.getActualMaximum(Calendar.DAY_OF_MONTH);//本月份的天数
            // System.out.println(max);
            
            // 获取开始月的最大天数；大月是1，3，5，7，8，10，12；小月是4，6，9，11；特殊月是2
            int max = 0;
            if(startMonth == 1){
                if(isLeapYear){
                    max = 29;
                }
                if(!isLeapYear){
                    max = 28;
                }
            }
            if(startMonth == 3 || startMonth == 5 || startMonth == 8 || startMonth == 10){
                max = 30;
            }
            if(startMonth == 0 || startMonth == 2 || startMonth == 4 || startMonth == 6 || startMonth == 7 || startMonth == 9
                    || startMonth == 11){
                max = 31;
            }
            
            // 循环每个月
            // 如果在日期范围内月份循环时自增到了一年的最后一个月就将月份初始化到一月份
            int y = 0;
            // 如果是开始日期的第一个年的月数就从开始月数循环
            if(x == startYear){
                y = startMonth;
            }
            for(; y < 12; y++){
                
                // 获取当月的最大天数；大月是1，3，5，7，8，10，12；小月是4，6，9，11；特殊月是2
                max = 0;
                if(y == 1){
                    if(isLeapYear){
                        max = 29;
                    }
                    if(!isLeapYear){
                        max = 28;
                    }
                }
                if(y == 3 || y == 5 || y == 8 || y == 10){
                    max = 30;
                }
                if(y == 0 || y == 2 || y == 4 || y == 6 || y == 7 || y == 9 || y == 11){
                    max = 31;
                }
                
                @SuppressWarnings("unused")
                int ty = y + 1;
                // System.out.println(x + "年" + ty + "月");
                
                // 循环每一天
                int z = 1;
                // 如果是开始日期的第一个月的天数就从开始天数循环
                if(x == startYear && y == startMonth){
                    z = startDay;
                }
                for(; z <= max; z++){
                    count++;
                    
                    // System.out.println(x + "年" + ty + "月" + z + "日");
                    
                    if(x == endYear && y == endMonth && z == endDay){
                        break;
                    }
                }
                
                // 如果已经遍历过了截至日期的最后月份就中止月份的循环
                if(x == endYear && y == endMonth){
                    break;
                }
                
            }
        }
        return count;
    }
    
    public static void msain(String[] args) throws ParseException {
        String start = "2007-01-27";
        String end = "2008-03-04";
        // 字符串转换成日期
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = format.parse(start);
        Date endDate = format.parse(end);
        
        int count = startToEndCount(startDate, endDate);
        
        System.out.println(start + " 到 " + end + " 的天数差：" + count);
        
    }
    
}
