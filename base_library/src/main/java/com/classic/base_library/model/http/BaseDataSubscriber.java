package com.classic.base_library.model.http;

import com.classic.base_library.base.BasePresenter;
import com.classic.base_library.model.bean.base.BaseBean;


/**
 * Created by zcq on 2019/7/16.
 */

public abstract class BaseDataSubscriber<T extends BaseBean> extends BaseSubscriber<T> {

    public BaseDataSubscriber(BasePresenter mPresenter) {
        super(mPresenter);
    }

    @Override
    public void onAnalysis(T tDateResponse) {
        onAnalysisNext(tDateResponse);
    }

    public abstract void onAnalysisNext(T data);

    @Override
    public void getError(int code, String error_msg) {
        mPresenter.mView.showError(error_msg);
    }
}
