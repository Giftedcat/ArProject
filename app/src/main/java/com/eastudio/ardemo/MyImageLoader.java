package com.eastudio.ardemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.eastudio.ardemo.application.BaseApplication;

import java.util.concurrent.ExecutionException;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;

/**
 * Created by Ricardo on 2018/2/2.
 */

public class MyImageLoader {

    /**
     * 设置图片以centerCrop的方式显示
     *
     * @param url
     */
    public static Bitmap loadCenterImage(Context mContext, String url) {

        try {
            Bitmap bitmap = Glide.with(mContext)
                    .load(url)
                    .asBitmap() //必须
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(500, 500)
                    .get();
            return bitmap;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 圆形图像加载方法
     *
     * @param mContext       如果为null，则默认用Application的context,建议为null，避开glide因为Activity销毁而出现的bug
     * @param url            地址
     * @param myImageView    目标imageview
     * @param defineImageSrc 默认图形的资源文件
     * @param flag           0代表不需要变灰色
     */
    public static void loadCircleImage(Context mContext, String url, ImageView myImageView, int defineImageSrc, int flag) {


//        Glide.with(mContext)
//                .load(url)
//                //.placeholder(R.drawable.loading_spinner)
//                //.crossFade()
//                .into(myImageView);

        if (null == mContext) {
            mContext = BaseApplication.getAppContext();
        }
        //flag为0代表不需要变灰色
        if (flag == 0) {
            Glide.with(mContext).load(url)
                    .centerCrop()
                    .placeholder(defineImageSrc)
                    .bitmapTransform(new CropCircleTransformation(mContext))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(myImageView);

        } else {
            Glide.with(mContext).load(url)
                    .centerCrop()
                    .placeholder(defineImageSrc)
                    //第一个参数是设置为灰色，第二个是设置为圆形，还可以追加，详情看
                    //https://github.com/wasabeef/glide-transformations
                    .bitmapTransform(new GrayscaleTransformation(mContext), new CropCircleTransformation(mContext))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(myImageView);
        }

    }

}
