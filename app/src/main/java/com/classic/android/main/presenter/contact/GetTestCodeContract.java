package com.classic.android.main.presenter.contact;

import com.classic.base_library.base.BasePresenter;
import com.classic.base_library.base.BaseView;

/**
 * 账号注册/手机验证码登陆/修改信息
 */
public interface GetTestCodeContract {
    interface View extends BaseView {
        void getPhoneCode();//获取注册手机验证码

        void countDown(long time); //点击发送验证码按钮后按钮状态变化,由小增大

        void nextDo();//获取验证码后操作

    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getPhoneCode(String mobile);

        public abstract void nextDo(String ...params);

        public abstract void startCountDown();
    }

}
