package com.git.easylib.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/4/8.
 */
public class SingleToast {
    private static Toast sToast = null;
    private static Object lock = new Object();
    private static final Handler mMainThreadHandler = new Handler(Looper.getMainLooper());

    private SingleToast() {
    }

    public static void show(final Context context, final String msg) {
        mMainThreadHandler.post(new Runnable() {
            public void run() {
                synchronized(SingleToast.lock) {
                    if(SingleToast.sToast == null) {
                        SingleToast.sToast = Toast.makeText(context.getApplicationContext(), msg, 1);
                    }
                }

                SingleToast.sToast.setText(msg);
                SingleToast.sToast.show();
            }
        });
    }

//    public static void show(final String msg) {
//        mMainThreadHandler.post(new Runnable() {
//            public void run() {
//                synchronized(SingleToast.lock) {
//                    if(SingleToast.sToast == null) {
//                        SingleToast.sToast = Toast.makeText(, msg, 1);
//                    }
//                }
//
//                SingleToast.sToast.setText(msg);
//                SingleToast.sToast.show();
//            }
//        });
//    }
}
