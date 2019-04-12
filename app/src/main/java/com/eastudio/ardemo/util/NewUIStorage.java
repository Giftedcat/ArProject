package com.eastudio.ardemo.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


/**
 * 数据存储类型.<br/>
 * 在使用该方法之前, 需要先调用{@link #init(Context)}初始化一下. <br/>
 * <br/>
 * 如果应用程序的Application是继承自{@link_BaseApplication}, 则不需要再进行初始化了, 可以直接使用
 * 本类需在已经有设备id的情况下，才能使用，否则会出现空指针
 */
public class NewUIStorage {
    private static SharedPreferences sharedPreferences;
    private static Editor editer;

    private NewUIStorage() {

    }

    public static void init(Context c) {
        sharedPreferences = c.getSharedPreferences("newuisp",
                Context.MODE_PRIVATE);
        editer = sharedPreferences.edit();
        editer.commit();
    }

//    /**
//     * 如果不存在, 则返回0
//     *
//     * @param key
//     * @return 某个键对应的值
//     */
//    public static int getInt(String key) {
//        return sharedPreferences.getInt(GlobalEnv.getCurrentbaby().getId() + key, 0);
//    }
//
//    public static int getInt(String key, int defVuale) {
//        return sharedPreferences.getInt(GlobalEnv.getCurrentbaby().getId() + key, defVuale);
//    }
//
//    public static void put(String key, int value) {
//        editer.putInt(GlobalEnv.getCurrentbaby().getId() + key, value);
//        editer.commit();
//    }
//
//    /**
//     * 如果不存在, 则返回0
//     *
//     * @param key
//     * @return 某个键对应的值
//     */
//    public static long getLong(String key) {
//        return sharedPreferences.getLong(GlobalEnv.getCurrentbaby().getId() + key, 0);
//    }
//
//    public static long getLong(String key, long defValue) {
//        return sharedPreferences.getLong(GlobalEnv.getCurrentbaby().getId() + key, defValue);
//    }
//
//    public static void put(String key, long value) {
//        editer.putLong(GlobalEnv.getCurrentbaby().getId() + key, value);
//        editer.commit();
//    }
//
//    /**
//     * 如果不存在, 则返回 null
//     *
//     * @param key
//     * @return 某个键对应的值
//     */
//    public static String getString(String key) {
//        return sharedPreferences.getString(GlobalEnv.getCurrentbaby().getId() + key, null);
//    }
//
//    public static String getString(String key, String defValue) {
//        return sharedPreferences.getString(GlobalEnv.getCurrentbaby().getId() + key, defValue);
//    }
//
//    public static void put(String key, String value) {
//        editer.putString(GlobalEnv.getCurrentbaby().getId() + key, value);
//        editer.commit();
//    }
//
//    /**
//     * 如果不存在, 则返回false
//     *
//     * @param key
//     * @return 某个键对应的值
//     */
//    public static boolean getBoolean(String key) {
//        return sharedPreferences.getBoolean(GlobalEnv.getCurrentbaby().getId() + key, false);
//    }
//
//    public static boolean getBoolean(String key, boolean defValue) {
//        return sharedPreferences.getBoolean(GlobalEnv.getCurrentbaby().getId() + key, defValue);
//    }
//
//    public static void put(String key, boolean value) {
//        editer.putBoolean(GlobalEnv.getCurrentbaby().getId() + key, value);
//        editer.commit();
//    }
//
//    public static void remove(String key) {
//        editer.remove(GlobalEnv.getCurrentbaby().getId() + key);
//        editer.commit();
//    }

}
