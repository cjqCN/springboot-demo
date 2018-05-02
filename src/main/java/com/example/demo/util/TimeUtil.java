package com.example.demo.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;



/**
 * 为处理高并发场景下System.currentTimeMillis()的性能问题的优化<p></>
 * 每次调用new Date() 或者 System.currentTimeMillis()都会调用底层系统接口，导致速度慢
 * 本工具开一个线程与系统交互，后台定时更新时间，优化性能。
 *
 * @author chenjinquan
 * @date 2018-4-12
 */
public class TimeUtil {

    public static final String YYYY_MM_DD = "yyyy/MM/dd";
    static String CN_TIME_ZONE = "Asia/Shanghai";

    static String FULL_FORMAT = "yyyy-MM-dd HH:mm:ss";

    static String FULL_FORMAT_ = "yyyy/MM/dd HH:mm:ss";

    public static String getCurrentTimeStr() {
        return getCurrentTimeStr(FULL_FORMAT);
    }

    public static String getCurrentTimeStr(String format) {
        Date currentTime = date();
        DateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(TimeZone.getTimeZone(CN_TIME_ZONE));
        return dateFormat.format(currentTime);
    }

    public static String getDateStr(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FULL_FORMAT_);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(CN_TIME_ZONE));
        return simpleDateFormat.format(date);
    }

    public static String getDateStr(Date date, String fullFormat) {
        if (date == null) return null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(fullFormat);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(CN_TIME_ZONE));
        return simpleDateFormat.format(date);
    }

    public static String getYMDDateStr(Date date) {
        if (date == null) return null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYYY_MM_DD);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(CN_TIME_ZONE));
        return simpleDateFormat.format(date);
    }

    public static Date StringToTime(String format, String stringTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = simpleDateFormat.parse(stringTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date date() {
        Date date = new Date(SystemClock.now());
        return date;
    }

}

class SystemClock {
    static Instant now = Instant.now();
    public static long now() {
        return now.toEpochMilli();
    }
}




