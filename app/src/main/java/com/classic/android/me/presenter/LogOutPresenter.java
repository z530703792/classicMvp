package com.classic.android.me.presenter;

import com.classic.android.me.presenter.contract.LogOutContract;
import com.classic.base_library.model.http.RetrofitHelper;
import com.classic.base_library.utils.PrefUtils;

import javax.inject.Inject;

public class LogOutPresenter extends LogOutContract.Presenter {

    @Inject
    public LogOutPresenter(RetrofitHelper helper) {
        mRetrofitHelper = helper;
    }

    @Override
    public void logOut() {
        PrefUtils.putUser(null);
        mView.logOut();
    }


}
