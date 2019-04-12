package com.eastudio.ardemo.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.widget.ImageView;
import android.widget.Toast;

import com.eastudio.ardemo.http.listener.OnTaskListener;
import com.eastudio.ardemo.ui.activity.presenter.SplashActivityNewPresenter;
import com.eastudio.ardemo.ui.util.NewUICommonUtil;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.eastudio.ardemo.R;
import lib_zxing.activity.CodeUtils;
import pub.devrel.easypermissions.EasyPermissions;

/**
 *
 * @author dyf
 * @date 2018/1/19/019
 */

@RequiresPresenter(SplashActivityNewPresenter.class)
public class SplashActivityNew extends BeamBaseActivity<SplashActivityNewPresenter> implements EasyPermissions.PermissionCallbacks {

    /**
     * 扫描跳转Activity RequestCode
     */
    public static final int REQUEST_CODE = 111;

    private static final int GETLOCATION = 100;
    private static final int SETTINGS_REQUESTCODE = 101;
    @BindView(R.id.iv_splash_activity_new)
    ImageView ivSplashActivityNew;
    private Handler handler = new Handler();
    private Activity mContext = this;
    private boolean first;
    private String[] perms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_new);
        ButterKnife.bind(this);
//        getPresenter().checkAppVersion();

        ivSplashActivityNew.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ivSplashActivityNew.setImageResource(R.drawable.qidongye);

        SharedPreferences sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        first = sp.getBoolean("first", true);
        perms = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA};

//        ivSplashActivityNew.setAlpha();
        ObjectAnimator alpha = ObjectAnimator.ofFloat(ivSplashActivityNew, "Alpha", 0.2f, 1).setDuration(2000);
        alpha.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                checkPermission();
//                //由于大管家的两个app，用的是同一个地址和接口，所以无法检测新版本是对应的哪一个APP
//                if (BaseApplication.thisDGJApp || BaseApplication.thisDGJHWApp) {
//                    checkPermission();
//                } else {
//                    getPresenter().checkAppVersion();
//                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        alpha.start();

    }

    public void checkPermission() {
        //判断是否有相关权限，并申请权限
        if (EasyPermissions.hasPermissions(mContext, perms)) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    GoNextActivity();
                }
            });
        } else {
//              EasyPermissions.requestPermissions(this, "app需要定位权限", GETLOCATION, perms);
            ActivityCompat.requestPermissions(mContext, perms, GETLOCATION);
        }
    }

    /**
     * 申请的结果
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions 转交给EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    /**
     * 同意了这个权限，则进入下一页
     *
     * @param requestCode
     * @param perms
     */
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        GoNextActivity();
    }

    /**
     * @param requestCode
     * @param perms
     */
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        //用户点击过不再提醒的勾勾，会走这儿
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            //EasyPermissions自带的这个dialog，不能设置禁用空白区域的点击事件，只好自己写了
//            new AppSettingsDialog.Builder(this, "为了您能正常使用app的功能，请去设置中，允许app获取位置信息").setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            NewUICommonUtil.showSingleToast(mContext, "由于没开启相关权限，您将无法很好的使用本app");
//                            GoNextActivity();
//                        }
//                    }, 500);
//                }
//            }).build().show();


            new AlertDialog.Builder(mContext)
                    .setCancelable(false)
                    .setMessage(R.string.ask_permissions1)
                    .setPositiveButton(R.string.goToAttentionSure, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Create app settings intent
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", mContext.getPackageName(), null);
                            intent.setData(uri);
                            // Start for result
                            startActivityForResult(intent, SETTINGS_REQUESTCODE);
                        }
                    })
                    .setNegativeButton(R.string.goToAttentionCancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    NewUICommonUtil.showSingleToast(mContext, getString(R.string.ask_permissions2));
                                    GoNextActivity();
                                }
                            }, 500);
                        }
                    })
                    .show();
        } else {
            NewUICommonUtil.showSingleToast(this, getString(R.string.ask_permissions2));
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    GoNextActivity();
                }
            }, 500);
        }


    }


    /**
     * 从设置回来后会走这儿
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SETTINGS_REQUESTCODE) {
            // Do something after user returned from app settings screen, like showing a Toast.
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (EasyPermissions.hasPermissions(mContext, perms)) {

                    } else {
                        NewUICommonUtil.showSingleToast(mContext, getString(R.string.ask_permissions2));
                    }
                    GoNextActivity();
                }
            }, 500);
        }
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    toMain();
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
//                    String result = bundle.getString(CodeUtils.RESULT_STRING);
//                    Device device = new Device();
//                    device.setImei(result);
//                    device.setToken(GlobalEnv.getUserInfo().getUser().getToken());
//                    getPresenter().bind(device, new OnTaskListener<String>() {
//                        @Override
//                        public void onFinish(final String s) {
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    if (s.equals("success")) {
//                                        startActivity(new Intent(mContext, MainActivityNew.class));
//                                        finish();
//                                    } else {
//                                        startActivity(new Intent(mContext, LoginAndRegisterActivityNew.class));
//                                        finish();
//                                    }
//                                }
//                            });
//                        }
//                    });
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    toMain();
                    Toast.makeText(SplashActivityNew.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            } else {
                toMain();
            }
        }
    }

    public boolean notSkip = true;

    public void GoNextActivity() {
//        if (notSkip) {
//            getPresenter().checkVersionResult();
//            return;
//        }
        //token login
        if (first){
//            startActivity(new Intent(mContext, GuideActivityNew.class));
//            finish();
        }else {
            getPresenter().tokenLogin(new OnTaskListener<String>() {

                @Override
                public void onFinish(final String obj) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            if (obj.equals("success")) {
//                                startActivity(new Intent(mContext, MainActivityNew.class));
//                                finish();
//                            } else {
//                                startActivity(new Intent(mContext, LoginAndRegisterActivityNew.class));
//                                finish();
//                            }
                        }
                    });
                }
            });
        }
    }


    /**
     * token is null
     * to next activity
     */
    public void toNextActivity() {
////        if (first) {
////            startActivity(new Intent(mContext, GuideActivityNew.class));
////        } else {
//        startActivity(new Intent(mContext, LoginAndRegisterActivityNew.class));
////        ARouter.getInstance().build("/com/login").navigation();
////        }
//        finish();
    }

    public void toMain() {
//        Intent intent = new Intent(mContext, MainActivityNew.class);
//        startActivityForResult(intent, REQUEST_CODE);
    }


}
