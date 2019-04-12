package com.eastudio.ardemo.util;

import com.alibaba.android.arouter.launcher.ARouter;

public class ArouterUtils {

    /**
     * 阿里跳转
     * */
    public static void navigation(String url){
        ARouter.getInstance().build(url).navigation();
    }

}
