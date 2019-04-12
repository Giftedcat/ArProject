package com.eastudio.ardemo.ui.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eastudio.ardemo.ui.activity.presenter.ManorPresenter;
import com.eastudio.ardemo.util.SharePreferenceUtil;
import com.jude.beam.bijection.RequiresPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.eastudio.ardemo.R;

/**
 * @author dyf
 */
@Route(path = "/com/ardemo/manor")
@RequiresPresenter(ManorPresenter.class)
public class ManorActivity extends ParentActivity<ManorPresenter> {

    PopupWindow mCardPop;
    @BindView(R.id.out_rl)
    RelativeLayout out_rl;
    @BindView(R.id.o2_rl1)
    RelativeLayout o2_rl1;
    @BindView(R.id.o2_rl2)
    RelativeLayout o2_rl2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manor);

        ButterKnife.bind(this);
        initPopuoWindow();
    }

    /**
     * 初始化弹窗
     */
    private void initPopuoWindow() {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.pop_postcard, null);
        mCardPop = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mCardPop.setContentView(contentView);

        initPopView(contentView);

        //设置点击空白处消失
        mCardPop.setOutsideTouchable(true);
        mCardPop.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
    }

    /**
     * 初始化弹窗里的子控件
     */
    private void initPopView(View view) {
        LinearLayout out_ll = view.findViewById(R.id.out_ll);
        out_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCardPop.dismiss();
            }
        });
    }

    @OnClick({R.id.imgLetter, R.id.imgBack, R.id.o2_rl1, R.id.o2_rl2})
    public void onClick(View view) {
        int fraction = SharePreferenceUtil.getIntValue(this, "fraction", 0);
        switch (view.getId()) {
            case R.id.imgBack:
                //返回
                this.finish();
                break;
            case R.id.imgLetter:
                //邮箱
                mCardPop.showAtLocation(out_rl, Gravity.CENTER, 0, 0);
                break;
            case R.id.o2_rl1:
                //收集
                o2_rl1.setVisibility(View.INVISIBLE);
                fraction += 10;
                SharePreferenceUtil.saveIntValue(this, "fraction", fraction);
                break;
            case R.id.o2_rl2:
                //收集
                o2_rl2.setVisibility(View.INVISIBLE);
                fraction += 10;
                SharePreferenceUtil.saveIntValue(this, "fraction", fraction);
                break;
            default:
                break;
        }
    }

}
