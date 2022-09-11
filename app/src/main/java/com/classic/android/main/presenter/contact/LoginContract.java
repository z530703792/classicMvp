package com.classic.android.main.presenter.contact;

import com.classic.base_library.base.BasePresenter;
import com.classic.base_library.base.BaseView;

/**
 * Created by zcq
 */

public interface LoginContract {

    interface View extends BaseView {

        void accountLogin();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void accountLogin(String mobile, String accountPwd);
    }
}
