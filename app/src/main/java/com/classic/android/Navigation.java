package com.classic.android;

import android.app.Activity;

import com.classic.android.main.ui.activity.LoginActivity;
import com.classic.base_library.base.RxManage;
import com.classic.base_library.utils.ActivityJumpUtils;
import com.classic.base_library.utils.PrefUtils;
import com.classic.base_library.utils.SystemUtil;
import com.classic.base_library.utils.ToastUtil;

public class Navigation {
    private static RxManage mRxManage = new RxManage();

    /**
     * 登录
     */
    public static void login(Activity context, boolean hint) {
        if (SystemUtil.isLogin()) {
            if (hint) {
                ToastUtil.showToast("登陆已过期");
            }
            PrefUtils.putUser(null);
            ActivityJumpUtils.ToOtherActivity(LoginActivity.class, context, null);
        }
    }

    /**
     * 退出
     */
    public static void logout() {
        PrefUtils.putUser(null);
        // 清理缓存&注销监听
    }

}
