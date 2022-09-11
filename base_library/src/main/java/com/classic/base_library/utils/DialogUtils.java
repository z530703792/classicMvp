package com.classic.base_library.utils;

import android.app.Activity;
import android.view.View;

import com.classic.base_library.widget.AlertDialog;

/**
 * Created by zcq on 2019/12/20.
 */

public class DialogUtils {

    private DialogUtils() {
    }

    public static void dialogTip(String tip, Activity mActivity) {
        dialogBuilder(tip, mActivity, false, null, null, "提示", "取消", "确定", false);
    }

    public static void dialogLeftTip(String tip, Activity mActivity) {
        dialogBuilder(tip, mActivity, false, null, null, "提示", "取消", "确定", true);
    }

    public static void dialogTipPositive(String tip, Activity mActivity,
                                         View.OnClickListener listener) {
        dialogBuilder(tip, mActivity, false, listener, null, "提示", "取消", "确定", false);
    }

    public static void dialogTipCancel(String tip, Activity mActivity, View.OnClickListener enter) {
        dialogBuilder(tip, mActivity, true, enter, null, "提示", "取消", "确定", false);
    }

    public static void dialogTipPositive(String tip, String enterText, Activity mActivity,
                                         View.OnClickListener enter) {
        dialogBuilder(tip, mActivity, true, enter, null, "提示", "取消", enterText, false);
    }

    public static void dialogTipCancel(String tip, String cancelText, Activity mActivity,
                                       View.OnClickListener enter) {
        dialogBuilder(tip, mActivity, true, enter, null, "提示", cancelText, "确定", false);
    }

    public static void dialogTip(String tip, String cancelText, Activity mActivity,
                                 View.OnClickListener positiveClick,
                                 View.OnClickListener cancelClick) {
        dialogBuilder(tip, mActivity, true, positiveClick, cancelClick, "提示", cancelText, "确定",
                false);
    }

    /**
     * @param msg 提示问题
     * @param mActivity activity
     * @param cancel 是否需要取消按钮
     * @param positiveClick 确定按钮的回调可传入空
     * @param cancelClick 取消按钮的回调可传入空
     * @param tip 提示标题
     * @param cancelText 取消的文字
     * @param enterText 确定的文字
     * @param isLeft 是否左对齐
     */
    public static void dialogBuilder(String msg, Activity mActivity, boolean cancel,
                                     View.OnClickListener positiveClick,
                                     View.OnClickListener cancelClick, String tip,
                                     String cancelText, String enterText, boolean isLeft) {
        if (mActivity != null && !mActivity.isFinishing()) {
            AlertDialog dialog = new AlertDialog(mActivity).builder().setTitle(tip).setMsg(msg)
                    .setCancelable(false);
            if (cancel) {
                if (cancelClick == null) {
                    dialog.setNegativeButton(cancelText, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    });
                } else {
                    dialog.setNegativeButton(cancelText, cancelClick);
                }
            }
            if (positiveClick == null) {
                dialog.setPositiveButton(enterText, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
            } else {
                dialog.setPositiveButton(enterText, positiveClick);
            }
            if (isLeft) {
                dialog.needLeftTip(true);
            }
            dialog.show();
        }
    }
}
