package com.eastudio.ardemo.ui.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.githang.statusbar.StatusBarCompat;

import org.greenrobot.eventbus.EventBus;

//import com.umeng.analytics.MobclickAgent;


/**
 * 自定义Activity基类
 * Created by luzhuwen on 2017/5/18.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected boolean isRegisteredEventBus;
    protected RelativeLayout titleLeftImg;
    protected TextView tvTitle;
    protected Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        StatusBarCompat.setStatusBarColor(this, Color.TRANSPARENT, true);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(setLayout());

//        tvTitle = (TextView) findViewById(R.id.tvTitle);
//        titleLeftImg = (RelativeLayout) findViewById(R.id.titleLeftImg);
        if (titleLeftImg != null) {
            titleLeftImg.setOnClickListener(onClickBackListener);
        }

        initViews();
        initData();
    }

    /** 设置布局 */
    protected abstract int setLayout();
    /** 初始化控件 */
    protected abstract void initViews();
    /** 初始化数据 */
    protected abstract void initData();

    /** 返回按钮点击监听 */
    protected View.OnClickListener onClickBackListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onPageStart("BaseScreen"); //统计页面()
//        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPageEnd("BaseScreen");
//        MobclickAgent.onPause(this);
    }

    /** 设置标题 */
    protected void setTitle(String titleText) {
        if (tvTitle != null) {
            tvTitle.setText(titleText);
        }
    }

    /** 显示Toast，时长默认为SHORT */
    public void showToast(String strTips) {
        Toast.makeText(mActivity, strTips, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示Toast
     * @param strTips
     * @param duration 等于Toast.LENGTH_LONG(1)时显示时长为LONG，否则为SHORT
     */
    public void showToast(String strTips, int duration) {
        if (duration == Toast.LENGTH_LONG) {
            Toast.makeText(mActivity, strTips, Toast.LENGTH_LONG).show();
        } else {
            showToast(strTips);
        }
    }

    /** 子类调用注册EventBus，不调用则默认没有注册 */
    protected void registerEventBus() {
        EventBus.getDefault().register(this);
        isRegisteredEventBus = true;
    }

//    @Subscribe
//    public void onEventMainThread(EventMsg msg) {
//        onReceiveEventBusMsg(msg);
//    }
//
//    /**
//     * 接收EventBus消息的方法，必须注册EventBus后才能正常接收
//     * @param msg 消息对象
//     */
//    protected void onReceiveEventBusMsg(EventMsg msg) {
//        //子类重写处理相关逻辑
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 注销EventBus
        if(isRegisteredEventBus) {
            EventBus.getDefault().unregister(this);
        }
    }

}
