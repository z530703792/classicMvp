package com.classic.android.me.presenter.contract;

import com.classic.base_library.base.BasePresenter;
import com.classic.base_library.base.BaseView;

/**
 * Created by zcq
 */

public interface UpdateUserInfoContract {

    interface View extends BaseView {
        void updateUserInfo();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void updateUserInfo(String mobile, String nickName, int sex, String signature, String birth);
    }
}
