package com.classic.base_library.utils;

import android.widget.Toast;

import com.classic.base_library.App.App;


public class ToastUtil {
    private static Toast toast;

    /**
     * @param text
     */
    public static void showToast(String text) {
        if (App.getInstance() == null) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(App.getInstance(), text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);//如果不为空，则直接改变当前toast的文本
        }
        toast.show();
    }
}
