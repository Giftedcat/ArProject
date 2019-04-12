package com.eastudio.ardemo.ui.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import com.eastudio.ardemo.R;

/**
 * 文 件 名: NewUICommonUtil
 * 创 建 人: banbury
 * 创建日期: 2017/1/14 0014 11:00
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */

public class NewUICommonUtil {

    private static Toast toast;

    public static void openNewActivity(Context context, Class clazz) {
        context.startActivity(new Intent(context, clazz));
    }

    public static void openNewActivity(final Activity activity) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showSingleToast(activity, "开发中，敬请期待");
            }
        });
    }

    public static void openNewActivityWithIntent(Context context, Intent intent) {
        context.startActivity(intent);
    }

    public static void logout(Context context){
//        Intent intent = new Intent(context, LoginAndRegisterActivityNew.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        context.startActivity(intent);
    }



    /**
     * 动态设置listview高度的方法，主要针对scrollView套listview的只显示一行的bug
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i += 1) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    /**
     * 显示单一吐司的方法
     *
     * @param c
     * @param s
     */
    public static void showSingleToast(Context c, String s) {
        if (null == s) {
            s = c.getString(R.string.openning);
        }
        if (null == toast) {
//            toast = Toast.makeText(BaseApplication.getAppContext(), s, Toast.LENGTH_SHORT);
            View custom_toast_view = View.inflate(c, R.layout.custom_toast_view, null);
            tv_toast = (TextView) custom_toast_view.findViewById(R.id.tv_toast);
            toast = new Toast(c.getApplicationContext());
            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 250);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(custom_toast_view);
        }
//            toast.setText(s);
        tv_toast.setText(s);
        toast.show();
    }


    static TextView tv_toast;

    /**
     * 显示单一吐司的方法
     *
     * @param c
     * @param s
     */
    public static void showSingleLongToast(Context c, String s) {
        if (null == s) {
            s = c.getString(R.string.openning);
        }
        if (null == toast) {
//            toast = Toast.makeText(BaseApplication.getAppContext(), s, Toast.LENGTH_LONG);
            View custom_toast_view = View.inflate(c, R.layout.custom_toast_view, null);
            tv_toast = (TextView) custom_toast_view.findViewById(R.id.tv_toast);
            toast = new Toast(c.getApplicationContext());
            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 250);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(custom_toast_view);
        }
//            toast.setText(s);
        tv_toast.setText(s);
        toast.show();
    }


    public static boolean isThisApp(Context c, String appId) {
        if (!TextUtils.isEmpty(appId)) {
            return appId.equals(c.getPackageName());
        }
        return false;
    }


    /**
     * 判断是否有安装对应的包名的app
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isInstalled(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String pkName = packageInfos.get(i).packageName;
                if (pkName.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 高德转百度（火星坐标gcj02ll–>百度坐标bd09ll）
     * @param gd_lat
     * @param gd_lon
     * @return
     */
    public static double[] gaoDeToBaidu(double gd_lat, double gd_lon) {
        double[] bd_lat_lon = new double[2];
        double PI = 3.14159265358979324 * 3000.0 / 180.0;
        double x = gd_lon, y = gd_lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * PI);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * PI);
        bd_lat_lon[0] = z * Math.cos(theta) + 0.0065;
        bd_lat_lon[1] = z * Math.sin(theta) + 0.006;
        return bd_lat_lon;
    }


    /**
     * 百度转高德（百度坐标bd09ll–>火星坐标gcj02ll）
     * @param bd_lat
     * @param bd_lon
     * @return
     */
    public static double[] bdToGaoDe(double bd_lat, double bd_lon) {
        double[] gd_lat_lon = new double[2];
        double PI = 3.14159265358979324 * 3000.0 / 180.0;
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * PI);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * PI);
        gd_lat_lon[0] = z * Math.cos(theta);
        gd_lat_lon[1] = z * Math.sin(theta);
        return gd_lat_lon;
    }



    public static double pi = 3.1415926535897932384626;
    public static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
    public static double a = 6378245.0;
    public static double ee = 0.00669342162296594323;

    /**
     * * 火星坐标系 (GCJ-02) to 84 * * @param lon * @param lat * @return
     * */
    public static double[] gcj02_To_Gps84(double lat, double lon) {
        double[] gps = transform(lat, lon);
        double lontitude = lon * 2 - gps[1];
        double latitude = lat * 2 - gps[0];
        return new double[]{latitude, lontitude};
    }

    public static double[] transform(double lat, double lon) {
        double dLat = transformLat(lon - 105.0, lat - 35.0);
        double dLon = transformLon(lon - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * pi;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
        double mgLat = lat + dLat;
        double mgLon = lon + dLon;
        return new double[]{mgLat,mgLon};
    }

    public static double transformLat(double x, double y) {
        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y
                + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    public static double transformLon(double x, double y) {
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1
                * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0
                * pi)) * 2.0 / 3.0;
        return ret;
    }

}
