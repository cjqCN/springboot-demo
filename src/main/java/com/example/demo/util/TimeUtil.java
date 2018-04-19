package com.example.demo.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;


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
    private final long period;
    private final AtomicLong now;

    private SystemClock(long period) {
        this.period = period;
        this.now = new AtomicLong(System.currentTimeMillis());
        scheduleClockUpdating();
    }

    private static class InstanceHolder {
        public static final SystemClock INSTANCE = new SystemClock(1);
    }

    private static SystemClock instance() {
        return InstanceHolder.INSTANCE;
    }

    private void scheduleClockUpdating() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, "System Clock");
                thread.setDaemon(true);
                return thread;
            }
        });
        scheduler.scheduleAtFixedRate(new Runnable() {
            public void run() {
                now.set(System.currentTimeMillis());
            }
        }, period, period, TimeUnit.MILLISECONDS);
    }

    private long currentTimeMillis() {
        return now.get();
    }

    public static long now() {
        return instance().currentTimeMillis();
    }



}



