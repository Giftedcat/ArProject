package com.git.easylib.ui.dialog;

import android.app.Activity;
import android.app.ProgressDialog;

/**
 * Created by Gulliver(feilong) on 15/12/14.
 */
public class WaitDlg {
    private ProgressDialog m_pDialog;
    private Activity mActivity;

    public WaitDlg(Activity activity){
        mActivity = activity;
        m_pDialog = new ProgressDialog(activity);
        m_pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        m_pDialog.setIndeterminate(false);
        m_pDialog.setCanceledOnTouchOutside(false);
        m_pDialog.setCancelable(true);
    }

    public void show(String msg){
        // 如果Dialog依附的Activity正常运行才能show，否则Activity已销毁或还没渲染完成Dialog.show就会崩溃
        // 通常不要在Activity.onCreate()里调用Dialog.show
        try {
            if (mActivity != null && m_pDialog != null && !mActivity.isFinishing()) {
                m_pDialog.setMessage(msg);
                m_pDialog.show();
            }
        } catch (Exception e) {
        }
    }

    public void hide(){
        m_pDialog.hide();
        m_pDialog.dismiss();
    }

    public boolean isShow(){
        return m_pDialog.isShowing();
    }
}
