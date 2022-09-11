package com.classic.android.main.presenter.contact;

import com.classic.base_library.base.BasePresenter;
import com.classic.base_library.base.BaseView;

/**
 * Created by zcq
 */

public interface SplashContract {

    interface View extends BaseView {

        void countDown(long time);

        void toMain();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void startCountDown();
    }
}
