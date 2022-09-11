package com.classic.base_library.widget;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by Allen Liu on 2019/2/23.
 */
public class BaseDialog extends Dialog {
    private int res;

    public BaseDialog(Context context, int theme, int res) {
        super(context, theme);
        // TODO 自动生成的构造函数存根
        setContentView(res);
        this.res = res;
        setCanceledOnTouchOutside(false);
    }

}