package com.classic.base_library.base;

import com.classic.base_library.model.http.RetrofitHelper;


/**
 * Created by zcq on 2019/6/5.
 */

public abstract class BasePresenter<T extends BaseView> {
    public final String TAG = this.getClass().getSimpleName();
    public T mView;

    public RetrofitHelper mRetrofitHelper;
    public RxManage mRxManage = new RxManage();

    public void setVM(T v) {
        this.mView = v;
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public void onDestroy() {
        mRxManage.clear();
    }

    public void getMistakeData() {

    }

}
