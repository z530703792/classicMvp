package com.classic.base_library.model.http;

import com.classic.base_library.base.BasePresenter;
import com.classic.base_library.model.bean.base.BaseBean;
import com.classic.base_library.model.bean.base.DataBean;


/**
 * Created by zcq
 */

public abstract class DataSubscriber<T extends DataBean<E>, E> extends BaseSubscriber<BaseBean<T>> {

    public DataSubscriber(BasePresenter mPresenter) {
        super(mPresenter);
    }

    @Override
    public void onAnalysis(BaseBean<T> tDataResponse) {
        onAnalysisNext(tDataResponse.getData().getDate());
    }

    public abstract void onAnalysisNext(E data);

    @Override
    public void getError(int code, String error_msg) {
        mPresenter.mView.showError(error_msg);
    }
}
