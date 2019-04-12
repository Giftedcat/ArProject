package com.eastudio.ardemo.ui.activity;

import android.content.Context;
import android.os.Bundle;

import com.eastudio.ardemo.event.BanlanceMsg;
import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.beam.expansion.BeamBasePresenter;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author Ricardo
 * @date 2018/4/27
 */

public class ParentActivity<T extends BeamBasePresenter> extends BeamBaseActivity<T> {

    public Context mContext;

    @Override
    protected void onResume() {
        super.onResume();

        MobclickAgent.onResume(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    /**
     * 收到推送-截屏
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void helloEventBus(BanlanceMsg message) {
    }

}
