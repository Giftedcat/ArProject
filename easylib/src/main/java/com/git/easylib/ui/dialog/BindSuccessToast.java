package com.git.easylib.ui.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.git.easylib.R;


/**
 * Created by chenggong on 2016/3/18.
 */
public class BindSuccessToast {
    private static BindSuccessToast toastCommom;

    private Toast toast;

    private BindSuccessToast(){
    }

    public static BindSuccessToast createToastConfig(){
        if (toastCommom==null) {
            toastCommom = new BindSuccessToast();
        }
        return toastCommom;
    }

    /**
     * 显示Toast
     * @param context
     * @param root
     * @param tvString
     */
    public void ToastShow(Context context,ViewGroup root,String tvString){
        View layout = LayoutInflater.from(context).inflate(R.layout.bind_success_dialog,root);
        TextView text = (TextView) layout.findViewById(R.id.tv_bind_success);
        text.setText(tvString);
        toast = new Toast(context);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}
