package com.eastudio.ardemo.application;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.eastudio.ardemo.contants.Contants;
import com.eastudio.ardemo.util.BeamUtil;
import com.git.easylib.L;
import com.git.easylib.app.MyApplication;
import com.git.easylib.util.StoreUtil;
import com.jude.beam.Beam;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.Bugly;

import cn.jpush.android.api.JPushInterface;
import com.eastudio.ardemo.BuildConfig;
import com.eastudio.ardemo.R;


/**
 *
 * @author dyf
 * @date 2017/11/10/010
 */

public class BaseApplication extends MyApplication {

    private static final String TAG = BaseApplication.class.getSimpleName();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        getAppPackage();
        initBeam();
        initUmengPush();
        initStoreUtil();
//        MobclickAgent.openActivityDurationTrack(false);
        initJPush();
//        registeObserver();
        initBugly();
        initGreenDao();
        initArouter();
//        initL();
    }

    private void initJPush(){
        Log.i("IMDebugApplication", "init");
//        JMessageClient.setDebugMode(true);
//        JMessageClient.init(getApplicationContext(), true);

        // 设置开启日志,发布时请关闭日志
        JPushInterface.setDebugMode(true);
        // 初始化 JPushMessageType
        JPushInterface.init(this);
        JPushInterface.getRegistrationID(this);
    }

    private void initL(){
        if (!BuildConfig.DEBUG) {
            L.disableDebug();
        } else {
            L.enableDebug();
            L.enableFileLog(this);
            // 初始化检测 内存泄漏
            initLeakCanary();
        }
    }


    private void initArouter(){
        if (BuildConfig.DEBUG) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }


    private void getAppPackage() {
    }

    private void initBeam() {
        //MVP框架beam初始化
        Beam.init(this);
        //将所有继承beam框架的Activity的生命周期绑定到一个类中，在这个类中有对应的生命周期，只需要在这一个类中
        //各个生命周期内写入代码（例如友盟统计），就可以了
        BeamUtil.putActivityLifeCycleToBeamLifeCycleDelegate();
    }

    private void initUmengPush(){

    }

    private void initStoreUtil() {
        StoreUtil.init(this);
//        NewUIStorage.init(this);
//        ValueStorage.init(getApplicationContext());
    }

    private void initBugly(){
        Bugly.init(getApplicationContext(), getResources().getString(R.string.BuglyKey), true);
    }

    //检测内存泄漏
    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    private void initGreenDao() {
//        //通过DaoMaster的内部类DevOpenHelper，你可以得到一个便利的SQLiteOpenHelper对象。
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "my.db", null);
//        db = helper.getWritableDatabase();
//        //注意：该数据库连接属于DaoMaster，所以多个Session指的是相同的数据库连接。
//        DaoMaster daoMaster = new DaoMaster(db);
//        //代理类
//        daoSession = daoMaster.newSession();
    }

    private String getVersionName() throws Exception {
        // 获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        String version = packInfo.versionName;
        Contants.APP_VERSIONAME = version;
        Contants.APP_VERSIONCODE = packInfo.versionCode;
        return version;
    }

}
