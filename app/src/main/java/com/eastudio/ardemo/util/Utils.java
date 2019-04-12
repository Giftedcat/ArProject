package com.eastudio.ardemo.util;

/**
 * Created by wenxy on 2015/11/20.
 */

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

import com.git.easylib.L;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class Utils {

    public static final int VOLUME_CURRENT = -5;
    public static final int PLAY_MUSIC_STATE = -1;

    public static boolean isInstallApk(Context context, String packagename) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(
                    packagename, 0);
        } catch (NameNotFoundException e) {
            packageInfo = null;
        }
        if (packageInfo == null) {
            return false;
        } else {
            return true;
        }
    }

    private static String encodeAddress(String url) {
        if (url == null || url.length() == 0) {
            return url;
        }
        char[] cs = url.toCharArray();
        char[] keys = new char[cs.length];
        for (int i = 0; i < cs.length; i++) {
            keys[i] = randomChar(cs[i] - i);
            int tmp = cs[i] + keys[i];
            while (tmp > Byte.MAX_VALUE || keys[i] == '-' || tmp == '-') {
                keys[i] = randomChar(cs[i]);
                tmp = cs[i] + keys[i];
            }
            cs[i] = (char) tmp;
        }
        url = new String(cs);
        url += "-";
        url += new String(keys);
        return url;
    }

    public static StateListDrawable addStateDrawable(Drawable normal,
                                                     Drawable pressed) {
        StateListDrawable sd = new StateListDrawable();

        sd.addState(new int[] { android.R.attr.state_enabled,
                android.R.attr.state_focused }, pressed);

        sd.addState(new int[] { android.R.attr.state_pressed,
                android.R.attr.state_enabled }, pressed);
        sd.addState(new int[] { android.R.attr.state_focused }, pressed);
        sd.addState(new int[] { android.R.attr.state_pressed }, pressed);
        sd.addState(new int[] { android.R.attr.state_enabled }, normal);
        return sd;
    }

    private static char randomChar(int seed) {
        Random random = new Random(System.currentTimeMillis());
        int parentSeed = random.nextInt();
        random.setSeed(parentSeed + seed);
        return (char) random.nextInt(Byte.MAX_VALUE);
    }



    public static Bitmap bytes2Bitmap(byte[] b) {
        if (b != null && b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap
                .createBitmap(
                        drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(),
                        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        // canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public synchronized static byte[] Bitmap2Bytes(Bitmap bm) {
        if (bm != null && !bm.isRecycled()) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
            return baos.toByteArray();
        } else {
            return null;
        }
    }



    public static int getBatteryLevel(int level) {
        if (level >= 0 && level <= 4) {
            level = 0;
        } else if (level >= 5 && level <= 15) {
            level = 15;
        } else if (level >= 16 && level <= 35) {
            level = 28;
        } else if (level >= 36 && level <= 49) {
            level = 43;
        } else if (level >= 50 && level <= 60) {
            level = 57;
        } else if (level >= 61 && level <= 75) {
            level = 71;
        } else if (level >= 76 && level <= 90) {
            level = 85;
        } else if (level >= 91 && level <= 100) {
            level = 100;
        }
        return level;
    }

    public static int getMoonIndex(int moonLevel) {
        if (moonLevel == 30 || moonLevel == 1) {
            return 0;
        } else if (moonLevel >= 2 && moonLevel <= 6) {
            return 1;
        } else if (moonLevel >= 7 && moonLevel <= 10) {
            return 2;
        } else if (moonLevel >= 11 && moonLevel <= 14) {
            return 3;
        } else if (moonLevel >= 15 && moonLevel <= 16) {
            return 4;
        } else if (moonLevel >= 17 && moonLevel <= 21) {
            return 5;
        } else if (moonLevel >= 22 && moonLevel <= 24) {
            return 6;
        } else if (moonLevel >= 25 && moonLevel <= 26) {
            return 7;
        } else if (moonLevel >= 27 && moonLevel <= 29) {
            return 8;
        } else {
            return 0;
        }
    }

    @SuppressWarnings("deprecation")
    public static Drawable zoomDrawable(Drawable drawable, int w, int h) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap oldbmp = drawableToBitmap(drawable);
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) w / width);
        float scaleHeight = ((float) h / height);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height,
                matrix, true);
        return new BitmapDrawable(newbmp);
    }

    public static String formatTime(String millionSecond) {

        try {

            if (millionSecond.substring(millionSecond.length() - 5,
                    millionSecond.length()).equals("apple")) {
                String timeTemp = millionSecond.substring(0, 4) + "-"
                        + millionSecond.substring(4, 6) + "-"
                        + millionSecond.substring(6, 8) + "-"
                        + millionSecond.substring(9, 11) + ":" +

                        millionSecond.substring(11, 13) + ":"
                        + millionSecond.substring(13, 15);
                return timeTemp;
            } else {

                long currentTime = Long.parseLong(millionSecond);

                SimpleDateFormat formatter = new SimpleDateFormat(
                        "yyyy-MM-dd-HH:mm:ss");

                Date date = new Date(currentTime);
                String strDate = formatter.format(date);
                return strDate;

            }
        } catch (Exception e) {
            return "";
        }

    }



    public static Drawable zoomDrawable(Context context, Bitmap bitmap, int w,
                                        int h) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int newWidth = w;
        int newHeight = h;
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                matrix, true);
        return new BitmapDrawable(context.getResources(), resizedBitmap);

    }
//
//    ///add by elink wenxy@20151230 start
//    public static Bitmap createQRImage(String url) {
//        try {
//            if (url == null || "".equals(url) || url.length() < 1) {
//                return null;
//            }
//            //url = encodeAddress(url);
//            Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
//            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
//            BitMatrix bitMatrix = new QRCodeWriter().encode(url,
//                    BarcodeFormat.QR_CODE, 160,160, hints);
//            int[] pixels = new int[160* 160];
//            for (int y = 0; y < 160; y++) {
//                for (int x = 0; x < 160; x++) {
//                    if (bitMatrix.get(x, y)) {
//                        pixels[y * 160 + x] = 0xff000000;
//                    } else {
//                        pixels[y * 160 + x] = 0xffffffff;
//                    }
//                }
//            }
//            Bitmap bitmap = Bitmap.createBitmap(160,160, Bitmap.Config.ARGB_8888);
//            bitmap.setPixels(pixels, 0, 160, 0, 0,160, 160);
//            return bitmap;
//        } catch (WriterException e) {
//            // e.printStackTrace();
//        }
//        return null;
//    }
//    ///add by elink wenxy@20151230 end
//    public static Bitmap Create2DCode(String str) throws WriterException {
//        // 生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
//        BitMatrix matrix = new MultiFormatWriter().encode(str,
//                BarcodeFormat.QR_CODE, 180, 180);
//        int width = matrix.getWidth();
//        int height = matrix.getHeight();
//        // 二维矩阵转为一维像素数组,也就是一直横着排了
//        int[] pixels = new int[width * height];
//        for (int y = 0; y < height; y++) {
//            for (int x = 0; x < width; x++) {
//                if (matrix.get(x, y)) {
//                    pixels[y * width + x] = 0xff000000;
//                }
//
//            }
//        }
//
//        Bitmap bitmap = Bitmap.createBitmap(width, height,
//                Bitmap.Config.ARGB_8888);
//        // 通过像素数组生成bitmap,具体参考api
//        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
//        return bitmap;
//    }
    /**
     *判断当前activity是否处于前台
     */
    public static boolean isTopActivity(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            L.i("---topActivity.getClassName():" + topActivity.getClassName());
            L.i("---context.getClass().getName():" + context.getClass().getName());
            if (topActivity.getClassName().equals(context.getClass().getName())) {
                return true;
            }
        }
        return false;

    }

    /**
     * 根据class判断这个class是不是在栈顶
     * @param context
     * @param clazz
     * @return
     */
    public static boolean isTopActivityByClass(Context context,Class clazz) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (topActivity.getClassName().equals(clazz.getName())) {
                return true;
            }
        }
        return false;

    }
}
