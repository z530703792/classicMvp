package com.classic.android.main.presenter.contact;

import com.classic.base_library.base.BasePresenter;
import com.classic.base_library.base.BaseView;

/**
 * Created by zcq
 */
public interface UpdateUserHeaderContract {

    interface View extends BaseView {
        void updateUserHeader();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void updateUserHeader(String mobile, String headerPath);
        public abstract void uploadImg(String headerPath);
    }
}
