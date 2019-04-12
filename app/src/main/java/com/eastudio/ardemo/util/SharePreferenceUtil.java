package com.eastudio.ardemo.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharePreferenceUtil
{
	private static String SP_FILE_NAME = "myjob";
	
	public static void saveStringValue(Context context,String key,String value)
	{
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				SP_FILE_NAME, Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}
	
	public static void saveIntValue(Context context,String key,int value)
	{
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				SP_FILE_NAME, Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putInt(key, value);
		editor.commit();
	}
	
	public static void saveBooleanValue(Context context,String key,boolean value)
	{
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				SP_FILE_NAME, Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
	
	public static void saveLongValue(Context context,String key,long value)
	{
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				SP_FILE_NAME, Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putLong(key, value);
		editor.commit();
	}
	
	public static String getStringValue(Context context,String key,String defaultValue)
	{
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				SP_FILE_NAME, Context.MODE_PRIVATE);
		return sharedPreferences.getString(key, defaultValue);
	}
	
	public static int getIntValue(Context context,String key,int defaultValue)
	{
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				SP_FILE_NAME, Context.MODE_PRIVATE);
		return sharedPreferences.getInt(key, defaultValue);
	}
	
	public static boolean getBooleanValue(Context context,String key,boolean defaultValue)
	{
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				SP_FILE_NAME, Context.MODE_PRIVATE);
		return sharedPreferences.getBoolean(key, defaultValue);
	}
	
	public static long getLongValue(Context context,String key,long defaultValue)
	{
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				SP_FILE_NAME, Context.MODE_PRIVATE);
		return sharedPreferences.getLong(key, defaultValue);
	}
	
}
