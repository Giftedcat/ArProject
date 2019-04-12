package com.git.easylib.util;

import android.content.ComponentName;
import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;

/**
 * Created by Administrator on 2017/11/8/008.
 */

public class Util {


    public static boolean isEnabled(Context context) {
        String pkgName = context.getPackageName();
        final String flat = Settings.Secure.getString(context.getContentResolver(), "enabled_notification_listeners");
        if (!TextUtils.isEmpty(flat)) {
            final String[] names = flat.split(":");
            for (int i = 0; i < names.length; i++) {
                final ComponentName cn = ComponentName.unflattenFromString(names[i]);
                if (cn != null) {
                    if (TextUtils.equals(pkgName, cn.getPackageName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
