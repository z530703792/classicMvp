package com.classic.base_library.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.classic.base_library.R;


/**
 * Created by sj on 2016/10/13.
 */
public class BottomFullScreenDialog extends Dialog implements View.OnClickListener {


    public BottomFullScreenDialog(Context context, View view) {
        super(context , R.style.bottomDialog);
        init(view);
    }

    /**
     * 初始化dialog
     * @param view
     */
    private void init(View view) {
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeAllViews();
        }
        Window window = this.getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(view);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        lp.windowAnimations = R.style.MyDialogAnimation;
        window.setAttributes(lp);
        super.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

}

