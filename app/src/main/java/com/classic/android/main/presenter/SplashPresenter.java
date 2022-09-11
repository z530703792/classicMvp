package com.classic.android.main.presenter;

import com.classic.base_library.model.http.RetrofitHelper;
import com.classic.base_library.model.http.RxUtil;
import com.classic.android.main.presenter.contact.SplashContract;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by zcq
 */

public class SplashPresenter extends SplashContract.Presenter {

    @Inject
    public SplashPresenter(RetrofitHelper helper) {
        mRetrofitHelper = helper;
    }

    @Override
    public void startCountDown() {
        mRxManage.add(Flowable.interval(1, TimeUnit.SECONDS)
                .compose(RxUtil.rxSchedulerHelper())
                .subscribe(aLong -> {
                    mView.countDown(aLong);
                    if (aLong == 3) {
                        mView.toMain();
                    }
                    //Log.e(TAG, "accept: ");
                }, throwable -> mView.toMain(), () -> mView.toMain())
        );
    }
}
