package com.eastudio.ardemo.ui.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.eastudio.ardemo.ui.activity.SplashActivityNew;
import com.git.easylib.L;


/**
 * Created by Gulliver(feilong) on 15/12/10.
 */
public class ActivitiesUtils {
    private final static String TAG = ActivitiesUtils.class.getSimpleName();

    /**
     * 跳转到登陆界面
     *
     * @param context
     */
    public static void showLoginActivity(Context context) {
        L.d(TAG, "showLoginActivity");
////        Intent i = new Intent(context, SplashActivity.class);
////        Intent i = new Intent(context, MyloginActivity.class);
//        Intent i = new Intent(context, LoginAndRegisterActivityNew.class);
//        context.startActivity(i);
    }

    /**
     * 跳转到登陆界面，并且清除之前的activity
     *
     * @param context
     */
    public static void showLoginActivityOnly(Context context) {
        L.d(TAG, "showLoginActivity");
        Intent i = new Intent(context, SplashActivityNew.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    /**
     * 跳转到主界面
     *
     * @param context
     */
    public static void showMainActivity(Context context) {
        L.d(TAG, "showMainActivity");
//        Intent i = new Intent(context, MainActivity.class);
//        context.startActivity(i);
    }

    /**
     * 跳转到绑定界面
     * logout=true表示左上返回钮是登出账号并跳转到欢迎页
     * logout=false表示左上返回是关闭当前页
     *
     * @param context
     */
    public static void showBindActivity(Context context, boolean logout) {
        L.d(TAG, "showBindActivity");
//        Intent i = new Intent(context, ShowScanActivity.class);
//        i.putExtra("logout", logout);
//        context.startActivity(i);
    }

//    /**
//     * 跳转到欢迎界面
//     *
//     * @param context
//     */
//    public static void showWelcomActivity(Context context) {
//        LogUtils.TestFragment(TAG, "showWelcomActivity");
//        Intent i = new Intent(context, WelcomeActivity.class);
//        context.startActivity(i);
//    }

//    /**
//     * 跳转到添加围栏界面
//     *
//     * @param context
//     */
//    public static void showAddFenceActivity(Context context) {
//        LogUtils.TestFragment(TAG, "showAddFenceActivity");
//        Intent i = new Intent(context, FenceAddMapActivity.class);
//        context.startActivity(i);
//    }

//    /**
//     * 跳转到添加围栏界面，并且清除之前的activity
//     *
//     * @param context
//     */
//    public static void showAddFenceActivityOnly(Context context) {
//        LogUtils.TestFragment(TAG, "showAddFenceActivity");
//        Intent i = new Intent(context, FenceAddMapActivity.class);
//        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(i);
//    }

    /**
     * 跳转到设置手机号界面
     *
     * @param context
     */
    public static void showSetPhoneNumActivity(Context context) {
        L.d(TAG, "showSetPhoneNumActivity");
//        Intent i = new Intent(context, PhoneNumberActivity.class);
//        context.startActivity(i);
    }

    /**
     * 跳转到设置宝贝信息界面
     */
    public static void showSetBabyInfoActivity(Context context) {
        L.d(TAG, "showSetBabyInfoActivity");
//        Intent i = new Intent(context, BabyInfoActivity.class);
//        context.startActivity(i);
    }

    /**
     * 跳转到绑定成功界面
     *
     * @param context
     */
    public static void showBindResultActivityOnly(Context context) {
        L.d(TAG, "showBindResultActivityOnly");
//        Intent i = new Intent(context, BindResultActivity.class);
//        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(i);
    }

    /**
     * 打开网络设置
     *
     * @param context
     */
    public static void showNetWorkSettingActivity(Context context) {
        Intent intent = null;
        if (android.os.Build.VERSION.SDK_INT > 10) {
            intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
        } else {
            intent = new Intent();
            ComponentName component = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
            intent.setComponent(component);
            intent.setAction("android.intent.action.VIEW");
        }
        context.startActivity(intent);
    }

//    /**
//     * 打开baby设置界面
//     */
//    public static void showSettingActivity(Context context) {
//        Intent i = new Intent(context, SettingsActivity.class);
//        context.startActivity(i);
//
//    }
}
