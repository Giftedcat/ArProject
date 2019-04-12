package com.eastudio.ardemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eastudio.ardemo.contants.ArouterUrl;
import com.eastudio.ardemo.ui.activity.ParentActivity;
import com.eastudio.ardemo.ui.activity.presenter.MainPresenter;
import com.eastudio.ardemo.util.ArouterUtils;
import com.eastudio.ardemo.util.SharePreferenceUtil;
import com.jude.beam.bijection.RequiresPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author dyf
 */
@RequiresPresenter(MainPresenter.class)
public class MainActivity extends ParentActivity<MainPresenter> {

    PopupWindow mAnswerPop;
    @BindView(R.id.out_rl)
    RelativeLayout out_rl;
    @BindView(R.id.txtFraction)
    TextView txtFraction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initPopuoWindow();
        requestPermissions();
    }

    @Override
    protected void onResume() {
        super.onResume();

        int fraction = SharePreferenceUtil.getIntValue(this, "fraction", 0);
        txtFraction.setText(fraction + "");
    }

    public void requestPermissions()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},111);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode)
        {
            case 111: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {

                }
                else
                {
                    permissionsNotSelected();
                }
            }
        }
    }

    private void permissionsNotSelected()
    {
        Toast.makeText(this,"You must enable permissions in settings",Toast.LENGTH_LONG).show();

    }

    @OnClick({R.id.shop_ll, R.id.manor_ll, R.id.signin_ll, R.id.imgAR, R.id.imgARTree})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shop_ll:
                //商店
                ArouterUtils.navigation(ArouterUrl.SHOPURL);
                break;
            case R.id.manor_ll:
                //庄园
                ArouterUtils.navigation(ArouterUrl.MANORURL);
                break;
            case R.id.signin_ll:
                //签到
                mAnswerPop.showAtLocation(out_rl, Gravity.CENTER, 0, 0);
                break;
            case R.id.imgAR:
                //AR扫描
                ArouterUtils.navigation(ArouterUrl.SCANNING);
                break;
            case R.id.imgARTree:
                //AR树
                ArouterUtils.navigation(ArouterUrl.MARKERLESS);
                break;
            default:
                break;
        }
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
        imgAnswer.setImageResource(flag? R.drawable.true_icon: R.drawable.false_icon);
        txtAnswer.setText(flag? "正确":"错误");
        if (flag){
            int fraction = SharePreferenceUtil.getIntValue(this, "fraction", 0);
            fraction += 10;
            SharePreferenceUtil.saveIntValue(this, "fraction", fraction);
            final int finalFraction = fraction;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    txtFraction.setText(finalFraction + "");
                }
            });
        }
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
                    }
                });
            }
        }).start();
    }

}
