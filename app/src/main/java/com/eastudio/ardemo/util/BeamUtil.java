package com.eastudio.ardemo.util;

import com.jude.beam.Beam;
import com.jude.beam.bijection.ActivityLifeCycleDelegate;
import com.jude.beam.bijection.ActivityLifeCycleDelegateProvider;
import com.jude.beam.bijection.BeamAppCompatActivity;

/**
 * 文 件 名: BeamUtil
 * 创 建 人: banbury
 * 创建日期: 2017/1/10 0010 16:28
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */

public class BeamUtil {

    public static void putActivityLifeCycleToBeamLifeCycleDelegate(){
        Beam.setActivityLifeCycleDelegateProvider(new ActivityLifeCycleDelegateProvider() {
            @Override
            public ActivityLifeCycleDelegate createActivityLifeCycleDelegate(BeamAppCompatActivity activity) {
                return new MineActivityLifeCycleDelegate(activity);
            }
        });
    }
}
