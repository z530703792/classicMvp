package com.classic.android.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.classic.base_library.utils.StringUtils;
import com.classic.android.R;

/**
 * Created by zcq on 2019/3/30.
 */

public class HintDialog extends Dialog {

    private TextView             tvEnter;
    private TextView             tvTitle;
    private TextView             tvMessage;
    private String               titleStr;
    private String               messageStr;
    private String               enterStr;
    private View.OnClickListener onClickListener;

    public HintDialog(Context context) {
        this(context, R.style.Dialog);

    }

    public HintDialog(Context context, int themeResId) {
        super(context, themeResId);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.dialog_hint, null);
        setContentView(view);
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        window.setAttributes(layoutParams);

        initView();
        initData();
        initEvent();
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        tvEnter = findViewById(R.id.tv_enter);
        tvTitle = findViewById(R.id.tv_title);
        tvMessage = findViewById(R.id.tv_message);
    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {
        // 如果用户自定了 title 和 message
        if (!StringUtils.isEmpty(titleStr)) {
            tvTitle.setText(titleStr);
        }
        if (!StringUtils.isEmpty(messageStr)) {
            tvMessage.setText(messageStr);
        }
        // 如果设置按钮的文字
        if (!StringUtils.isEmpty(enterStr)) {
            tvEnter.setText(enterStr);
        }
    }

    /**
     * 从外界 Activity 为 Dialog 设置标题
     *
     * @param title
     */
    public HintDialog setTitle(String title) {
        titleStr = title;
        return this;
    }

    /**
     * 从外界 Activity 为 Dialog 设置 dialog 的 message
     *
     * @param message
     */
    public HintDialog setMessage(String message) {
        messageStr = message;
        return this;
    }

    /**
     * 设置确定按钮的显示内容和监听
     *
     * @param str
     * @param onClickListener
     */
    public HintDialog setOnclickListener(String str, View.OnClickListener onClickListener) {
        this.enterStr = str;
        this.onClickListener = onClickListener;
        return this;
    }

    /**
     * 初始化界面的按钮监听器
     */
    private void initEvent() {
        // 设置确定按钮被点击后，向外界提供监听
        tvEnter.setOnClickListener(v -> {
            onClickListener.onClick(v);
            dismiss();
        });
    }
}
