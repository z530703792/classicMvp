package com.classic.base_library.utils;

import android.text.TextUtils;
import android.util.Log;

import com.classic.base_library.BuildConfig;

/**
 * @author: zcq 2019-07-02 10:48
 */
public class LogUtils {
    public static String tagPrefix = "";

    /**
     * 得到tag（所在类.方法（L:行））
     * 
     * @return
     */
    private static String generateTag() {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
        String callerClazzName = stackTraceElement.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        String tag = "%s.%s(L:%d)";
        tag = String.format(tag, new Object[] { callerClazzName, stackTraceElement.getMethodName(),
                Integer.valueOf(stackTraceElement.getLineNumber()) });
        //给tag设置前缀
        tag = TextUtils.isEmpty(tagPrefix) ? tag : tagPrefix + ":" + tag;
        return tag;
    }

    public static void v(String msg) {
        if (BuildConfig.LOG_DEBUG) {
            String tag = generateTag();
            Log.v(tag, msg);
        }
    }

    public static void v(String msg, Throwable tr) {
        if (BuildConfig.LOG_DEBUG) {
            String tag = generateTag();
            Log.v(tag, msg, tr);
        }
    }

    public static void d(String msg) {
        if (BuildConfig.LOG_DEBUG) {
            String tag = generateTag();
            Log.d(tag, msg);
        }
    }

    public static void d(String msg, Throwable tr) {
        if (BuildConfig.LOG_DEBUG) {
            String tag = generateTag();
            Log.d(tag, msg, tr);
        }
    }

    public static void i(String msg) {
        if (BuildConfig.LOG_DEBUG) {
            String tag = generateTag();
            Log.i(tag, msg);
        }
    }

    public static void i(String msg, Throwable tr) {
        if (BuildConfig.LOG_DEBUG) {
            String tag = generateTag();
            Log.i(tag, msg, tr);
        }
    }

    public static void w(String msg) {
        if (BuildConfig.LOG_DEBUG) {
            String tag = generateTag();
            Log.w(tag, msg);
        }
    }

    public static void w(String msg, Throwable tr) {
        if (BuildConfig.LOG_DEBUG) {
            String tag = generateTag();
            Log.w(tag, msg, tr);
        }
    }

    public static void e(String msg) {
        if (BuildConfig.LOG_DEBUG) {
            String tag = generateTag();
            Log.e(tag, msg);
        }
    }

    public static void e(String msg, Throwable tr) {
        if (BuildConfig.LOG_DEBUG) {
            String tag = generateTag();
            Log.e(tag, msg, tr);
        }
    }

    public static void wtf(String msg) {
        if (BuildConfig.LOG_DEBUG) {
            String tag = generateTag();
            Log.wtf(tag, msg);
        }
    }

    public static void wtf(String msg, Throwable tr) {
        if (BuildConfig.LOG_DEBUG) {
            String tag = generateTag();
            Log.wtf(tag, msg, tr);
        }
    }
}
