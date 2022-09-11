package com.classic.base_library.utils;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

/**
 * Created by zcq on 2019/6/15.
 */

public class CommUtils {

    /**
     * @param d
     * @return 2位小数的double
     */
    public static String doubleTo2PointString(double d) {
        BigDecimal b = new BigDecimal(d);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * @param d
     * @return 1位小数的double
     */
    public static String doubleToOneString(double d) {
        BigDecimal b = new BigDecimal(d);
        return b.setScale(1, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * @param d
     * @return 无小数的double
     */
    public static String doubleToIntString(double d) {
        BigDecimal b = new BigDecimal(d);
        return b.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * double到int数
     *
     * @param d
     * @return
     */
    public static int doubleToInt(double d) {
        return (new Double(d)).intValue();
    }

    /**
     * 将时间搓转换为时间 格式为:年月日
     *
     * @param time
     * @return
     */
    public static String timestampToTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(time);
    }

    /**
     * 将时间搓转换为时间
     *
     * @param time
     * @return
     */
    public static String timestampToString(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(time);
    }

    /**
     * 将时间搓转换为时间
     *
     * @param time
     * @return
     */
    public static String timestampToStringTwo(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf.format(time);
    }

    /**
     * 将时间搓转换为时间
     *
     * @param time
     * @return
     */
    public static String timestampToTimeTwo(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(time);
    }

    /**
     * 得到字符串中的字符的位置
     *
     * @param string
     * @param c
     * @return
     */
    public static int getCharAt(String string, char c) {
        if (!string.contains(c + "")) {
            return -1;
        }
        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (c == chars[i]) {
                return i;
            }
        }
        return -1;
    }

    public static int setVarCode(String verName) {
        String[] ver = verName.split("\\.");
        StringBuilder stringBuilder = new StringBuilder();
        for (String name : ver) {
            if (name.length() < 2) {
                stringBuilder.append(0 + name);
            } else {
                stringBuilder.append(name);
            }
        }
        return Integer.parseInt(stringBuilder.toString());
    }

    /**
     * 位置*
     * 
     * @return
     */
    public static String getStringC(String s) {
        if (!TextUtils.isEmpty(s) && s.length() > 5) {
            return s.substring(0, 6) + "****" + s.substring(s.length() - 4, s.length());
        }
        return "";

    }

}
