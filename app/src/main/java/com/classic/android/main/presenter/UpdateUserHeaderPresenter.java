package com.classic.android.main.presenter;

import com.classic.android.main.presenter.contact.UpdateUserHeaderContract;
import com.classic.base_library.model.bean.base.BaseBean;
import com.classic.base_library.model.http.BaseDataSubscriber;
import com.classic.base_library.model.http.RetrofitHelper;
import com.classic.base_library.model.http.RxUtil;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class UpdateUserHeaderPresenter extends UpdateUserHeaderContract.Presenter {

    @Inject
    public UpdateUserHeaderPresenter(RetrofitHelper helper) {
        mRetrofitHelper = helper;
    }

    @Override
    public void updateUserHeader(String mobile, String headerPath) {
        Map<String, Object> map = new HashMap<>();
        map.put("mobile", mobile);
        map.put("headImageUrl", headerPath);
        mView.showWaiteDialog();
        mRxManage
                .add(mRetrofitHelper.getApis().updateUserHeader(map).compose(RxUtil.rxSchedulerHelper())
                        .subscribeWith(new BaseDataSubscriber<BaseBean>(this) {
                            @Override
                            public void onAnalysisNext(BaseBean data) {
                                mView.updateUserHeader();
                            }
                        }));
    }

    @Override
    public void uploadImg(String headerPath) {

    }
}
