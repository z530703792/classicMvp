package com.classic.base_library.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.classic.base_library.R;

public class LoadDialog {
    private Context      context;
    private Dialog       dialog;
    private LinearLayout lLayout_bg;
    private ImageView    imageView;
    private Animation    rotateAnimation;

    public LoadDialog(Context context) {
        this.context = context;
    }

    public LoadDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_load, null);

        // 获取自定义Dialog布局中的控件
        lLayout_bg = view.findViewById(R.id.lLayout_bg);
        imageView = view.findViewById(R.id.iv_load);
        rotateAnimation = AnimationUtils.loadAnimation(context, R.anim.dialog_load);
        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.loadStyle);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);

        // 调整dialog背景大小
        lLayout_bg.setLayoutParams(
                new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        return this;
    }

    public void show() {
        if (context == null) {
            return;
        }
        imageView.startAnimation(rotateAnimation);
        dialog.show();
    }

    public boolean isShowing() {
        return dialog.isShowing();
    }

    public void dismiss() {
        imageView.clearAnimation();
        dialog.dismiss();
    }
}
