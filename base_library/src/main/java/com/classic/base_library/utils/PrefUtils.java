package com.classic.base_library.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.classic.base_library.App.App;
import com.google.gson.Gson;

import com.classic.base_library.model.bean.UserBean;

/**
 * 专门访问和设置SharePreference的工具类, 保存和配置一些设置信息
 *
 * @author Kevin
 */
public class PrefUtils {

    private static final String SHARE_PREFS_NAME = "sp";

    public static void putBoolean(String key, boolean value) {
        SharedPreferences pref = App.getInstance().getSharedPreferences(SHARE_PREFS_NAME,
                Context.MODE_PRIVATE);

        pref.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        SharedPreferences pref = App.getInstance().getSharedPreferences(SHARE_PREFS_NAME,
                Context.MODE_PRIVATE);

        return pref.getBoolean(key, defaultValue);
    }

    public static void putString(String key, String value) {
        SharedPreferences pref = App.getInstance().getSharedPreferences(SHARE_PREFS_NAME,
                Context.MODE_PRIVATE);

        pref.edit().putString(key, value).commit();
    }

    public static String getString(String key, String defaultValue) {
        SharedPreferences pref = App.getInstance().getSharedPreferences(SHARE_PREFS_NAME,
                Context.MODE_PRIVATE);

        return pref.getString(key, defaultValue);
    }

    public static void putInt(String key, int value) {
        SharedPreferences pref = App.getInstance().getSharedPreferences(SHARE_PREFS_NAME,
                Context.MODE_PRIVATE);

        pref.edit().putInt(key, value).commit();
    }

    public static int getInt(String key, int defaultValue) {
        SharedPreferences pref = App.getInstance().getSharedPreferences(SHARE_PREFS_NAME,
                Context.MODE_PRIVATE);

        return pref.getInt(key, defaultValue);
    }

    public static void putUser(UserBean value) {
        SharedPreferences pref = App.getInstance().getSharedPreferences(SHARE_PREFS_NAME,
                Context.MODE_PRIVATE);
        pref.edit().putString("user", value == null ? "" : new Gson().toJson(value)).commit();
    }

    public static UserBean getUser() {
        SharedPreferences pref = App.getInstance().getSharedPreferences(SHARE_PREFS_NAME,
                Context.MODE_PRIVATE);
        String user = pref.getString("user", "");
        return StringUtils.isEmpty(user) ? new UserBean()
                : new Gson().fromJson(user, UserBean.class);
    }
}
