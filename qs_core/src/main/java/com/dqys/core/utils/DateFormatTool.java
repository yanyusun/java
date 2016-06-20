package com.dqys.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Yvan on 16/6/16.
 */
public class DateFormatTool {

    public static final String DATE_FORMAT_6 = "yyMMdd";
    public static final String DATE_FORMAT_8 = "yyyyMMdd";
    public static final String DATE_FORMAT_10_REG1 = "yyyy-MM-dd";
    public static final String DATE_FORMAT_10_REG2 = "yyyy/MM/dd";
    public static final String DATE_FORMAT_19 = "yyyy-MM-dd HH:mm:ss";

    public static String format(String regex){
        return format(Calendar.getInstance().getTime(), regex);
    }

    public static String format(Date date, String regex){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(regex);
        return simpleDateFormat.format(date);
    }

    public static Date parse(String dateStr, String regex) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(regex);
        try {
            return simpleDateFormat.parse(dateStr);
        }catch (ParseException e){
            // 这里做下日志

            e.printStackTrace();
        }
        return null;
    }
}
