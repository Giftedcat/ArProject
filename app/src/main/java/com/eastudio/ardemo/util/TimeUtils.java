package com.eastudio.ardemo.util;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.eastudio.ardemo.R;

/**
 * Created by jinyi on 15/11/16.
 */
public class TimeUtils {
    private static final String TAG = TimeUtils.class.getSimpleName();

    /**
     * long类型时间格式化
     */
    @SuppressLint("SimpleDateFormat")
    public static String convertToTime(long time) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.SIMPLIFIED_CHINESE);
        Date date = new Date(time);
        return df.format(date);
    }

    public static String convertToTime2(long time) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Date date = new Date(time);
        return df.format(date);
    }
    public static String convertToDateTime(long time) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date(time);
        return df.format(date);
    }

    public static String convertToDateTimeSimple(long time) {
        SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date(time);
        return df.format(date);
    }

    public static long valueOfLong(String millisTime) {
        if (millisTime != null) {
            try {
                long value = Long.valueOf(millisTime);
                return value;
            } catch (NumberFormatException e) {
                Log.w(TAG, "NumberFormatException, str:" + millisTime, e);
            }
        }
        return 0;
    }

    public static int valueOfInt(String intNum) {
        if (intNum != null) {
            try {
                int value = Integer.valueOf(intNum);
                return value;
            } catch (NumberFormatException e) {
                // if(LogUtils.enabled) LogUtils.w(TAG, "NumberFormatException, str:" + intNum, e);
            }
        }
        return 0;
    }

    public static long millsTime(String time) {
        long millsTime = 0;
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
        try {
            millsTime = dateFormat.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return millsTime;
    }

    public static String formatToToday(long millsTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        Date date = new Date(millsTime);
        String time = format.format(date);
        if (time == null || "".equals(time)) {
            return "";
        }

        Calendar current = Calendar.getInstance();

        Calendar today = Calendar.getInstance();// 今天

        today.set(Calendar.YEAR, current.get(Calendar.YEAR));
        today.set(Calendar.MONTH, current.get(Calendar.MONTH));
        today.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));
        // Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);

        Calendar yesterday = Calendar.getInstance();// 昨天

        yesterday.set(Calendar.YEAR, current.get(Calendar.YEAR));
        yesterday.set(Calendar.MONTH, current.get(Calendar.MONTH));
        yesterday.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH) - 1);
        yesterday.set(Calendar.HOUR_OF_DAY, 0);
        yesterday.set(Calendar.MINUTE, 0);
        yesterday.set(Calendar.SECOND, 0);

        current.setTime(date);

        if (current.after(today)) {
            return "今天" + time.split(" ")[1];
        } else if (current.before(today) && current.after(yesterday)) {

            return "昨天" + time.split(" ")[1];
        } else {
            int index = time.indexOf("-") + 1;
            return time.substring(index, time.length());
        }
    }

    public static void adjustTime(ContentValues values, String key, long expTime) {
        if (values.containsKey(key)) {
            Long src = values.getAsLong(key);
            long m = 0;
            if (src != null) {
                m = src;
            }
            values.put(key, m + expTime);
        }
    }

    public static String toDay() {
        String today = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        today = dateFormat.format(System.currentTimeMillis());
        return today;
    }

    public static String yesterday() {
        Calendar yesterday = Calendar.getInstance();// 昨天
        Calendar current = Calendar.getInstance();

        yesterday.set(Calendar.YEAR, current.get(Calendar.YEAR));
        yesterday.set(Calendar.MONTH, current.get(Calendar.MONTH));
        yesterday.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH) - 1);
        yesterday.set(Calendar.HOUR_OF_DAY, 0);
        yesterday.set(Calendar.MINUTE, 0);
        yesterday.set(Calendar.SECOND, 0);

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        return dateFormat.format(yesterday.getTime());
    }

    public static String Tomorrow() {
        Calendar yesterday = Calendar.getInstance();// 昨天
        Calendar current = Calendar.getInstance();

        yesterday.set(Calendar.YEAR, current.get(Calendar.YEAR));
        yesterday.set(Calendar.MONTH, current.get(Calendar.MONTH));
        yesterday.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH) + 1);
        yesterday.set(Calendar.HOUR_OF_DAY, 0);
        yesterday.set(Calendar.MINUTE, 0);
        yesterday.set(Calendar.SECOND, 0);

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        return dateFormat.format(yesterday.getTime());
    }

    public static long dayMills(long millsTime) {
        final long dayMillis = 1000 * 3600 * 24;
        long day = millsTime / dayMillis;
        day = day * dayMillis;
        return day;
    }

    public static boolean isToday(long millsTime) {
        final long dayMillis = 1000 * 3600 * 24;
        long day = millsTime / dayMillis;
        day = day * dayMillis;
        return day == TimeUtils.dayMills(System.currentTimeMillis());
    }

    public static String formatToTime(long millsTime) {
        final long dayMillis = 1000 * 3600 * 24;
        long time = millsTime % dayMillis;
        SimpleDateFormat sdFormatter = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String formatedate = sdFormatter.format(new Date(time));
        return formatedate;
    }

    public static String getDistanceTime(Context context, long time) {
        Resources res = context.getResources();
        long delta = (Math.abs(time - Calendar.getInstance().getTimeInMillis())) / (1000 * 60);
        if (delta / 60 >= 24)
            return (String.valueOf(delta / (60 * 24)) + res.getString(R.string.day));
        else {
            if (delta / 60 <= 0)
                return String.valueOf(delta % 60) + res.getString(R.string.minute);
            return (String.valueOf(delta / 60) + res.getString(R.string.hour) + String.valueOf(delta % 60) + res.getString(R.string.minute));
        }

    }
}
