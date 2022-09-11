package com.classic.base_library.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import com.classic.base_library.App.Constants;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by zcq on 2019/12/25.
 */

public class ActivityJumpUtils {

    private ActivityJumpUtils() {
    }

    /**
     * 跳转到其他的页面
     * <p>
     * map可以允许为空
     */
    public static void ToOtherActivity(Class clazz, Activity mActivity, Map<String, Object> map) {
        if (mActivity != null && !mActivity.isFinishing()) {
            Intent intent = new Intent(mActivity, clazz);
            if (map != null) {
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    if (entry.getValue() instanceof String) {
                        intent.putExtra(entry.getKey(), (String) entry.getValue());
                    } else if (entry.getValue() instanceof Integer) {
                        intent.putExtra(entry.getKey(), (Integer) entry.getValue());
                    } else if (entry.getValue() instanceof Boolean) {
                        intent.putExtra(entry.getKey(), (Boolean) entry.getValue());
                    }  else if (entry.getValue() instanceof Long) {
                        intent.putExtra(entry.getKey(), (Long) entry.getValue());
                    } else {

                    }
                }
            }
            mActivity.startActivity(intent);
        }
    }

    public static void ToActivityWithString(Class clazz, Activity mActivity, String... strings) {
        if (mActivity != null && !mActivity.isFinishing()) {
            Intent intent = new Intent(mActivity, clazz);
            if (strings != null) {
                for (int i = 0; i < strings.length; i += 2) {
                    intent.putExtra(strings[i], strings[i + 1]);
                }
            }
            mActivity.startActivity(intent);
        }
    }

    public static <T> void goPage(Context context, Class<T> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    public static <T> void goPage4Result(Activity activity, Class<T> cls, int requestCode) {
        Intent intent = new Intent(activity, cls);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 跳转
     *
     * @param context
     * @param o
     * @param cls
     * @param <T>
     */
    public static <T> void goPageObj(Context context, Object o, Class<T> cls) {
        Intent intent = new Intent(context, cls);
        intent.putExtra(Constants.MODEL, (Parcelable) o);
        context.startActivity(intent);
    }

    /**
     * 跳转
     *
     * @param context
     * @param o
     * @param cls
     * @param <T>
     */
    public static <T> void goPageObj(Context context, Object o, String title_type, Class<T> cls) {
        Intent intent = new Intent(context, cls);
        intent.putExtra(Constants.TITLE_TYPE, title_type);
        intent.putExtra(Constants.MODEL, (Serializable) o);
        context.startActivity(intent);
    }

    /**
     * 跳转
     *
     * @param context
     * @param title_type
     * @param cls
     * @param <T>
     */
    public static <T> void goPageType(Context context, String title_type, Class<T> cls) {
        Intent intent = new Intent(context, cls);
        intent.putExtra(Constants.TITLE_TYPE, title_type);
        context.startActivity(intent);
    }

}
