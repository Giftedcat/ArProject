package com.eastudio.ardemo.util;

import android.widget.Toast;

import com.eastudio.ardemo.application.BaseApplication;

/**
 * Created by cyp on 15/11/16.
 */
public class ToastUtils {

    private static Toast sToast;

    /**
     * long Toast
     *
     * @param text
     */
    public static void showLong(final String text) {
        showBase(text, Toast.LENGTH_LONG);
    }

    /**
     * short Toast
     *
     * @param text
     */
    public static void showShort(final String text) {
        showBase(text, Toast.LENGTH_SHORT);
    }

    private static void showBase(final String text, final int length) {
        BaseApplication.getMainThreadHandler().post(new Runnable() {
            @Override
            public void run() {
                if (sToast == null) {
                    sToast = Toast.makeText(BaseApplication.getAppContext(), text, length);
                }
                sToast.setText(text);
                sToast.show();
            }
        });
    }

}
