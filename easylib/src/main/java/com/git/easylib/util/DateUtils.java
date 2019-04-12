package com.git.easylib.util;

import android.text.TextUtils;

import java.io.IOError;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 日期、时间转换工具类
 * Created by luzhuwen on 2017/3/29.
 */
public class DateUtils {


    public static String getThisMonth() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }

    public static String getThisDay() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }

    public static String getQiniuyunTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }

    public static String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }

    // 私有构造
    public DateUtils() {}

    /**
     * 时间戳转化为年月日时分
     * @param timeStamp
     * @return
     */
    public static String ToYMDHM(long timeStamp){
        SimpleDateFormat format =  new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        return format.format(timeStamp);
    }
    /**
     * 时间戳转化为年月日时,依靠符号分隔
     * @param timeStamp
     * @return
     */
    public static String ToYMD_bySymbolDivide(long timeStamp){
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
        return format.format(timeStamp);
    }
    /**
     * 时间戳转化为年月日时,依靠符号分隔
     * @param timeStamp
     * @return
     */
    public static String ToYMDH_bySymbolDivide(long timeStamp){
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH");
        return format.format(timeStamp);
    }
    /**
     * 时间戳转化为年月日时,依靠符号分隔
     * @param timeStamp
     * @return
     */
    public static String ToYMDHm_bySymbolDivide(long timeStamp){
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(timeStamp);
    }
    /**
     * 时间戳转化为年月日时,依靠符号分隔
     * @param timeStamp
     * @return
     */
    public static String ToYMDHMS_bySymbolDivide(long timeStamp){
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(timeStamp);
    }
    /**
     * 时间戳转化为年月日
     * 输出月份前面不想被自动补0，一个M。如果想被自动补0，那么就用MM
     * @param timeStamp
     * @return
     */
    public static String ToYMD(long timeStamp){
        //如果timeStamp==0，默认格式化是1970年1月1日，这是不合理的，需要加判断
        if(timeStamp != 0) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy年M月d日");
            return format.format(timeStamp);
        } else
            return "";
    }

    /**
     * 时间戳转化为年月
     * 输出月份前面不想被自动补0，一个M。如果想被自动补0，那么就用MM
     * @param timeStamp
     * @return
     */
    public static String ToYM(long timeStamp){
        SimpleDateFormat format =  new SimpleDateFormat("yyyy年M月");
        return format.format(timeStamp);
    }

    /**
     * 返回年月日数组形式
     * @param timeStamp
     * @return
     */
    public static int[] ToYMDint(long timeStamp){
        SimpleDateFormat format =  new SimpleDateFormat("yyyy");
        SimpleDateFormat format1 =  new SimpleDateFormat("MM");
        SimpleDateFormat format2 =  new SimpleDateFormat("dd");
        int[] ints = new int[3];
        ints[0] = Integer.parseInt(format.format(timeStamp));
        ints[1] = Integer.parseInt(format1.format(timeStamp));
        ints[2] = Integer.parseInt(format2.format(timeStamp));
        return ints;
    }

    /**
     * 时间戳转化为月日时分
     * @param timeStamp
     * @return
     */

    public static String ToMDHM(long timeStamp){
        SimpleDateFormat format =  new SimpleDateFormat("MM月dd日HH:mm");
        return format.format(timeStamp);
    }

    public static String ToD(long timeStamp){
        SimpleDateFormat format =  new SimpleDateFormat("dd日");
        return format.format(timeStamp);
    }

    /**
     * 时间戳转化为月日
     * @param timeStamp
     * @return
     */
    public static String ToMD(long timeStamp){
        SimpleDateFormat format =  new SimpleDateFormat("MM月dd日");
        return format.format(timeStamp);
    }
    /**
     * 时间戳转化为月日
     * @param timeStamp
     * @return
     */
    public static String ToMD_byPointDivide(long timeStamp){
        SimpleDateFormat format =  new SimpleDateFormat("MM.dd");
        return format.format(timeStamp);
    }
    public static String ToMD2(long timeStamp){
        SimpleDateFormat format =  new SimpleDateFormat("M月d日");
        return format.format(timeStamp);
    }

    public static String ToYMD2(long timeStamp){
        SimpleDateFormat format =  new SimpleDateFormat("yyyy.M.d");
        return format.format(timeStamp);
    }

    public static String ToHMS(long timeStamp){
        SimpleDateFormat format =  new SimpleDateFormat("HH:mm:ss");
        return format.format(timeStamp);
    }

    /**
     * 时间戳转化为时分
     * @param timeStamp
     * @return
     */
    public static String ToHM(long timeStamp){
        SimpleDateFormat format =  new SimpleDateFormat("HH:mm");
        return format.format(timeStamp);
    }

    /**
     * time 按照yyyy-MM-dd-HH-mm格式传入日期，返回时间戳
     * @param time
     * @return
     */
    public static long YMDHMToTimeStamp(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年M月d日HH:mm");
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return date.getTime();
    }


    /**
     * time 按照yyyy-mm-dd格式传入日期，返回时间戳
     * @param time
     * @return
     */
    public static long YMDToTimeStamp(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return date.getTime();
    }

    /**
     * 时间展示 主界面消息列表
     * @param mTimeStamp
     */
    public static String getSessionTime(long mTimeStamp) {
        if (mTimeStamp <= 0) {
            return null;
        }
        String[] weekDays = {
                "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"
        };
        String strDesc = null;
        SimpleDateFormat formatYear = new SimpleDateFormat("yy/MM/dd");
        SimpleDateFormat formatToday = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        /**消息时间戳*/
        long changeTime = mTimeStamp;

        long messageTimeStamp = changeTime;
        /**当前的时间戳*/
        long currentTimeStamp = System.currentTimeMillis();
        /**获取今天的 0 点时间戳*/
        long todayTimeStamp = getTimesmorning();
        /**获取 上一周 0点时间戳*/
        long rangeWeekStamp = todayTimeStamp - 86400000 * 6;

        /**今天的显示 hh:mm   (今天星期三)
         * 昨天
         * 星期一
         * 星期日 、 星期六、 星期五、星期四
         * yy-hh-mm
         * */
        do {
            long diff = currentTimeStamp - messageTimeStamp;
            long diffToday = currentTimeStamp - todayTimeStamp;
            /**今天之内的*/
            if (diff < diffToday) {
                strDesc = formatToday.format(messageTimeStamp);
                break;
            }

            long diffWeek = currentTimeStamp - rangeWeekStamp;
            /**最近一周的判断*/
            if (diff < diffWeek) {
                /**昨天零点的时间*/
                long yesterday = todayTimeStamp - 86400000;
                long diffYesterday = currentTimeStamp - yesterday;
                if (diff < diffYesterday) {
                    strDesc = "昨天";
                } else {
                    Calendar weekCal = Calendar.getInstance();
                    weekCal.setTimeInMillis(messageTimeStamp);
                    int w = weekCal.get(Calendar.DAY_OF_WEEK) - 1;
                    w = w < 0 ? 0 : w;
                    strDesc = weekDays[w];
                }
                break;
            }
            /**年月日显示*/
            strDesc = formatYear.format(messageTimeStamp);
        } while (false);
        return strDesc;
    }
    /**
     * 获取当天零点的时间戳
     * @return
     */
    public static long getTimesmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 获取当天23:59的时间戳
     * @return
     */
    public static long getTimesnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }


    public static byte[] int2bytes(int res) {
        byte[] targets = new byte[4];
        try {
            targets[0] = (byte) (res & 0xff);// 最低位
            targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
            targets[2] = (byte) ((res >> 16) & 0xff);// 次高位
            targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。
        }catch (IOError e)
        {
            e.printStackTrace();
        }
        return targets;
    }
    public static int bytes2int(byte[] b, int offset) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (4 - 1 - i) * 8;
            value += (b[i + offset] & 0x000000FF) << shift;
        }
        return value;
    }


    /**
     * 转换日期 转换为更为人性化的时间
     *
     * @param time 时间
     * @return
     */
    public static String translateDate(long time, long curTime) {
        time = time/1000;
        curTime = curTime/1000;
        long oneDay = 24 * 60 * 60;
        Calendar today = Calendar.getInstance();    //今天
        today.setTimeInMillis(curTime * 1000);
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        long todayStartTime = today.getTimeInMillis() / 1000;
        if (time >= todayStartTime) {
            long d = curTime - time;
            if (d <= 60) {
                return "刚刚";
            } else if (d <= 60 * 60) {
                long m = d / 60;
                if (m <= 0) {
                    m = 1;
                }
                return m + "分钟前";
            } else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("今天 HH:mm");
                Date date = new Date(time * 1000);
                String dateStr = dateFormat.format(date);
                if (!TextUtils.isEmpty(dateStr) && dateStr.contains(" 0")) {
                    dateStr = dateStr.replace(" 0", " ");
                }
                return dateStr;
            }
        } else {
            if (time < todayStartTime && time > todayStartTime - oneDay) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("昨天 HH:mm");
                Date date = new Date(time * 1000);
                String dateStr = dateFormat.format(date);
                if (!TextUtils.isEmpty(dateStr) && dateStr.contains(" 0")) {

                    dateStr = dateStr.replace(" 0", " ");
                }
                return dateStr;
            } else if (time < todayStartTime - oneDay && time > todayStartTime - 2 * oneDay) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("前天 HH:mm");
                Date date = new Date(time * 1000);
                String dateStr = dateFormat.format(date);
                if (!TextUtils.isEmpty(dateStr) && dateStr.contains(" 0")) {
                    dateStr = dateStr.replace(" 0", " ");
                }
                return dateStr;
            } else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM月dd日 HH:mm");
                Date date = new Date(time * 1000);
                String dateStr = dateFormat.format(date);
                if (!TextUtils.isEmpty(dateStr) && dateStr.contains(" 0")) {
                    dateStr = dateStr.replace(" 0", " ");
                }
                return dateStr;
            }
        }
    }

    /**
     * 获取今天星期几
     */
    public static int getWeek() {
        int week = 0;
        long sysTime = System.currentTimeMillis();
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
        Date d1 = new Date(sysTime);
        String st = dayFormat.format(d1);
        switch (st) {
            case "星期一":
                week = 0;
                break;
            case "星期二":
                week = 1;
                break;
            case "星期三":
                week = 2;
                break;
            case "星期四":
                week = 3;
                break;
            case "星期五":
                week = 4;
                break;
            case "星期六":
                week = 5;
                break;
            case "星期日":
                week = 6;
                break;
            default:
                break;
        }
        return week;
    }
}
