//================================================================================================================================
//
//  Copyright (c) 2015-2018 VisionStar Information Technology (Shanghai) Co., Ltd. All Rights Reserved.
//  EasyAR is the registered trademark or trademark of VisionStar Information Technology (Shanghai) Co., Ltd in China
//  and other countries for the augmented reality technology developed by VisionStar Information Technology (Shanghai) Co., Ltd.
//
//================================================================================================================================

package com.eastudio.ardemo.ui.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eastudio.ardemo.listener.ScanningListener;
import com.eastudio.ardemo.ui.activity.presenter.ScanningPresenter;
import com.eastudio.ardemo.view.helloarvideo.GLView;
import com.jude.beam.bijection.RequiresPresenter;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.easyar.Engine;
import com.eastudio.ardemo.R;


/**
 * AR扫描界面
 * */
@Route(path = "/com/ardemo/scanning")
@RequiresPresenter(ScanningPresenter.class)
public class ScanningActivity extends ParentActivity<ScanningPresenter> {
    /*
    * Steps to create the key for this sample:
    *  1. login www.easyar.com
    *  2. create app with
    *      Name: HelloARVideo
    *      Package Name: cn.easyar.samples.helloarvideo
    *  3. find the created item in the list and show key
    *  4. set key string bellow
    */
    private static String key = "t2Od3jQRJ8TR451VNhWOElBTkNbvgA0pvN16hUXr5ihIctUAkIklSzhcZjZjOC8XAuHfwqAWGA0AgWoreQOHj4RNz5r1TvZevHaOeTUxYRnn2epEpleOwboWY24OGuYVFtpCJxIY4gDzCkRFQJHDqckSeZfAXIGl2q8SoJbhiX7zXceDgaV2LcGFKwrxrh5pzcaGbtMG";
    private GLView glView;
    PopupWindow mAnswerPop;
    @BindView(R.id.out_rl)
    RelativeLayout out_rl;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        ButterKnife.bind(this);
        if (!Engine.initialize(this, key)) {
            Log.e("HelloAR", "Initialization Failed.");
        }
        initPopuoWindow();

        glView = new GLView(this, new ScanningListener() {
            @Override
            public void onSuccess() {
                //签到
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAnswerPop.showAtLocation(out_rl, Gravity.CENTER, 0, 0);
                    }
                });
            }
        });

        requestCameraPermission(new PermissionCallback() {
            @Override
            public void onSuccess() {
                ((ViewGroup) findViewById(R.id.preview)).addView(glView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }

            @Override
            public void onFailure() {
            }
        });
    }

    private interface PermissionCallback
    {
        void onSuccess();
        void onFailure();
    }
    private HashMap<Integer, PermissionCallback> permissionCallbacks = new HashMap<Integer, PermissionCallback>();
    private int permissionRequestCodeSerial = 0;
    @TargetApi(23)
    private void requestCameraPermission(PermissionCallback callback)
    {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                int requestCode = permissionRequestCodeSerial;
                permissionRequestCodeSerial += 1;
                permissionCallbacks.put(requestCode, callback);
                requestPermissions(new String[]{Manifest.permission.CAMERA}, requestCode);
            } else {
                callback.onSuccess();
            }
        } else {
            callback.onSuccess();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if (permissionCallbacks.containsKey(requestCode)) {
            PermissionCallback callback = permissionCallbacks.get(requestCode);
            permissionCallbacks.remove(requestCode);
            boolean executed = false;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    executed = true;
                    callback.onFailure();
                }
            }
            if (!executed) {
                callback.onSuccess();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 初始化弹窗
     */
    private void initPopuoWindow() {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.pop_answer, null);
        mAnswerPop = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mAnswerPop.setContentView(contentView);

        initPopView(contentView);

        //设置点击空白处消失
        mAnswerPop.setOutsideTouchable(true);
        mAnswerPop.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
    }

    /**
     * 初始化弹窗里的子控件
     */
    private void initPopView(final View view) {
        LinearLayout out_ll = view.findViewById(R.id.out_ll);
        Button btnAnswer1 = view.findViewById(R.id.btnAnswer1);
        Button btnAnswer2 = view.findViewById(R.id.btnAnswer2);
        btnAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAnswer(view, true);
            }
        });
        btnAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAnswer(view, false);
            }
        });
        out_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnswerPop.dismiss();
            }
        });
    }

    private void showAnswer(View view, boolean flag){
        final LinearLayout content_ll = view.findViewById(R.id.content_ll);
        final LinearLayout answer_ll = view.findViewById(R.id.answer_ll);
        ImageView imgAnswer = view.findViewById(R.id.imgAnswer);
        TextView txtAnswer = view.findViewById(R.id.txtAnswer);
        content_ll.setVisibility(View.GONE);
        answer_ll.setVisibility(View.VISIBLE);
        imgAnswer.setImageResource(flag? R.drawable.true_icon:R.drawable.false_icon);
        txtAnswer.setText(flag? "正确":"错误");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAnswerPop.dismiss();
                        content_ll.setVisibility(View.VISIBLE);
                        answer_ll.setVisibility(View.GONE);
                        ScanningActivity.this.finish();
                    }
                });
            }
        }).start();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if (glView != null) { glView.onResume(); }
    }

    @Override
    protected void onPause()
    {
        if (glView != null) { glView.onPause(); }
        super.onPause();
    }

    @OnClick({R.id.imgBack})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                //返回
                this.finish();
                break;
            default:
                break;
        }
    }

}
