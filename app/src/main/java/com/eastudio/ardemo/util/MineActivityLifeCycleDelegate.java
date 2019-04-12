package com.eastudio.ardemo.util;

import android.graphics.Color;
import android.os.Bundle;

import com.git.easylib.L;
import com.githang.statusbar.StatusBarCompat;
import com.jude.beam.bijection.ActivityLifeCycleDelegate;
import com.jude.beam.bijection.BeamAppCompatActivity;


//import com.umeng.message.PushAgent;

/**
 * 文 件 名: MineActivityLifeCycleDelegate
 * 创 建 人: banbury
 * 创建日期: 2017/1/10 0010 16:23
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */

public class MineActivityLifeCycleDelegate extends ActivityLifeCycleDelegate {

//    private BeamAppCompatActivity activity;

    public MineActivityLifeCycleDelegate(BeamAppCompatActivity act) {
        super(act);
        L.d("MineActivityLifeCycleDelegate");
//        activity = act;
    }

    @Override
    protected void onStart() {
        super.onStart();
        L.d("onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        L.d("onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //友盟统计
//        MobclickAgent.onResume(getActivity());
        L.d("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //友盟统计
//        MobclickAgent.onPause(getActivity());
        L.d("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        L.d("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        L.d("onDestroy");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //推送用的
//        PushAgent.getInstance(getActivity()).onAppStart();
        L.d("onCreate"+getClass().getSimpleName());
        StatusBarCompat.setStatusBarColor(getActivity(), Color.TRANSPARENT, true);
    }
}
