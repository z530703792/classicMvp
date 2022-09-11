package com.classic.base_library.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.classic.base_library.R;

/**
 * Created by zcq on 2019/8/2.
 */
public class GlideUtils {

    public static void load(Context context, String url, ImageView iv) {
        if (isValidContextForGlide(context)) {
            Glide.with(context).load(url).into(iv);
        }
    }

    public static void load(Context context, Object url, ImageView iv) {
        if (isValidContextForGlide(context)) {
            Glide.with(context).load(url).into(iv);
        }
    }

    public static void loadErr(Context context, String url, ImageView iv) {
        if (isValidContextForGlide(context)) {
            Glide.with(context).load(url).apply(new RequestOptions().error(R.drawable.my_img_head))
                    .into(iv);
        }
    }

    public static void loadErrImg(Context context, String url, ImageView iv, int rImg) {
        if (isValidContextForGlide(context)) {
            Glide.with(context).load(url).apply(new RequestOptions().error(rImg)).into(iv);
        }
    }

    public static void loadErrImg(Context context, Object url, ImageView iv, int rImg) {
        if (isValidContextForGlide(context)) {
            Glide.with(context).load(url).apply(new RequestOptions().error(rImg)).into(iv);
        }
    }

    public static void loadGifErrImg(Context context, Object url, ImageView iv, int rImg) {
        if (isValidContextForGlide(context)) {
            Glide.with(context).load(url).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE).error(rImg)).into(iv);
        }
    }


    public static boolean isValidContextForGlide(final Context context) {
        if (context == null) {
            return false;
        }
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (activity.isDestroyed() || activity.isFinishing()) {
                    return false;
                }
            } else {

                if (activity.isFinishing()) {
                    return false;
                }
            }
        }
        return true;
    }
}
