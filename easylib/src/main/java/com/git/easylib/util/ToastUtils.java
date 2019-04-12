package com.git.easylib.util;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.git.easylib.app.MyApplication;

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

    public static void show(@StringRes int stringId) {
        showBase(stringId, Toast.LENGTH_LONG);
    }

    private static void showBase(final @StringRes int stringId, final int length) {
        MyApplication.getMainThreadHandler().post(new Runnable() {
            @Override
            public void run() {
                if (sToast == null) {
                    sToast = Toast.makeText(MyApplication.getAppContext(), stringId, length);
                }
                sToast.setText(stringId);
                sToast.show();
            }
        });
    }

    private static void showBase(final String text, final int length) {
        MyApplication.getMainThreadHandler().post(new Runnable() {
            @Override
            public void run() {
                if (sToast == null) {
                    sToast = Toast.makeText(MyApplication.getAppContext(), text, length);
                }
                sToast.setText(text);
                sToast.show();
            }
        });
    }

}
