package com.classic.base_library.model.http;

import com.classic.base_library.base.BasePresenter;
import com.classic.base_library.model.bean.base.BaseBean;
import com.classic.base_library.model.bean.base.ListBean;

import java.util.List;

/**
 * Created by zcq on 2019/6/23.
 */

public abstract class ListSubscriber<T extends ListBean<E>, E> extends BaseSubscriber<BaseBean<T>> {

    public ListSubscriber(BasePresenter mPresenter) {
        super(mPresenter);
    }

    @Override
    public void onAnalysis(BaseBean<T> baseBean) {
        onAnalysisNext(baseBean.getData().getDate());
    }

    public abstract void onAnalysisNext(List<E> data);

    @Override
    public void getError(int code, String error_msg) {
        mPresenter.mView.showError(error_msg);
    }

}
