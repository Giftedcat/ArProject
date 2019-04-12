package com.eastudio.ardemo.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eastudio.ardemo.ui.activity.presenter.ShopPresenter;
import com.eastudio.ardemo.ui.fragment.ShopFragment1;
import com.eastudio.ardemo.ui.fragment.ShopFragment2;
import com.eastudio.ardemo.ui.fragment.ShopFragment3;
import com.jude.beam.bijection.RequiresPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.eastudio.ardemo.R;

@Route(path = "/com/ardemo/shop")
@RequiresPresenter(ShopPresenter.class)
public class ShopActivity extends ParentActivity<ShopPresenter> {

    @BindView(R.id.vp_content)
    ViewPager mVpContent;
    private List<Fragment> mFragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        ButterKnife.bind(this);
        initData();
        mVpContent.setAdapter(new MyAdapter(getSupportFragmentManager()));
        mVpContent.setOffscreenPageLimit(3);
    }

    class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }

    private void initData() {
        ShopFragment1 shopFragment1 = new ShopFragment1();
        mFragmentList.add(shopFragment1);

        ShopFragment2 shopFragment2 = new ShopFragment2();
        mFragmentList.add(shopFragment2);

        ShopFragment3 shopFragment3 = new ShopFragment3();
        mFragmentList.add(shopFragment3);
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
