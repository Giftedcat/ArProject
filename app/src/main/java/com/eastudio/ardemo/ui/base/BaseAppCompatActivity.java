package com.eastudio.ardemo.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

//import com.umeng.analytics.MobclickAgent;

public class BaseAppCompatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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
}
