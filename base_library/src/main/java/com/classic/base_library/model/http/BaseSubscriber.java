package com.classic.base_library.model.http;

import com.classic.base_library.base.BaseError;
import com.classic.base_library.base.BasePresenter;
import com.classic.base_library.model.bean.base.BaseBean;
import com.classic.base_library.model.bean.base.BaseFault;
import com.classic.base_library.utils.ToastUtil;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by zcq
 */

public abstract class BaseSubscriber<T extends BaseBean> extends ResourceSubscriber<T> {

    public BasePresenter mPresenter;
    public T response;

    public BaseSubscriber(BasePresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void onNext(T t) {
        mPresenter.mView.closeWaiteDialog();
        response = t;
        if (t.isSucceed()) {
            mPresenter.mView.showNetPage();
            onAnalysis(t);
        }else{
            onError(new BaseFault(t.getSuccess(),t.getMessage()));
        }
        mPresenter.mView.ptrComplete();
    }

    public abstract void onAnalysis(T t);



    @Override
    public void onError(Throwable t) {
        baseError.onError(t);
        mPresenter.mView.closeWaiteDialog();
        mPresenter.mView.ptrComplete();
    }

    private BaseError baseError=new BaseError() {
        @Override
        protected void onErr(int code, String error_msg) {
            getErr(code, error_msg);
            mPresenter.mView.closeWaiteDialog();
        }
    };

    private void getErr(int code, String error_msg){
        if (code == BaseError.TOLOGIN){
            mPresenter.mView.toLogin();
        }else if (code == BaseError.ERR_CODE_CONNECT){
            mPresenter.mView.showNetError();
        }else if (code == BaseError.ERR_CODE_NET){
            mPresenter.mView.showServicesError();
        }else if (code == BaseError.ERR_CODE_UNKNOWN){
            ToastUtil.showToast(error_msg);
        }else{
            getError(code,error_msg);
        }
    }

    public abstract void getError(int code, String error_msg);

    @Override
    public void onComplete() {

    }
}
