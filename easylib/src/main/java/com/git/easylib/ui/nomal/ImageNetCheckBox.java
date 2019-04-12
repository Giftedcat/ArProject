package com.git.easylib.ui.nomal;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.git.easylib.R;
import com.git.easylib.util.NetUtil;
import com.git.easylib.util.ToastUtils;


/**
 * Created by Administrator on 2016/6/23.
 */
public class ImageNetCheckBox extends ImageView{
    private boolean isChecked=false;
    public ImageNetCheckBox(Context context) {
        super(context);
    }

    public ImageNetCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageNetCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setChecked(boolean checked){
        isChecked=checked;
        if(checked){

            setImageResource(R.drawable.btn_on);
        }else {
            setImageResource(R.drawable.btn_off);
        }
    }

    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public boolean performClick() {
        if(!NetUtil.isNetworkAvailable(getContext())){
            ToastUtils.showShort("网络异常，请检查");
            return false;
        }else{
            return super.performClick();
        }

    }
}
