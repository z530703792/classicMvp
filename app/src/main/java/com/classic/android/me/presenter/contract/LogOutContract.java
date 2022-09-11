package com.classic.android.me.presenter.contract;

import com.classic.base_library.base.BasePresenter;
import com.classic.base_library.base.BaseView;

/**
 * Created by zcq
 */

public interface LogOutContract {

    interface View extends BaseView {
        void logOut();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void logOut();
    }
}
