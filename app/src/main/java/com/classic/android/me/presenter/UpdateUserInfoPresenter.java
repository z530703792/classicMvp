package com.classic.android.me.presenter;

import com.classic.android.me.presenter.contract.UpdateUserInfoContract;
import com.classic.base_library.model.bean.base.BaseBean;
import com.classic.base_library.model.http.BaseDataSubscriber;
import com.classic.base_library.model.http.RetrofitHelper;
import com.classic.base_library.model.http.RxUtil;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class UpdateUserInfoPresenter extends UpdateUserInfoContract.Presenter {

    @Inject
    public UpdateUserInfoPresenter(RetrofitHelper helper) {
        mRetrofitHelper = helper;
    }

    @Override
    public void updateUserInfo(String mobile, String nickName, int sex, String signature,
                               String birth) {
        Map<String, Object> map = new HashMap<>();
        map.put("mobile", mobile);
        map.put("nickName", nickName);
        map.put("sex", sex);
        map.put("signature", signature);
        map.put("birthday", birth);

        mView.showWaiteDialog();
        mRxManage.add(mRetrofitHelper.getApis().updateUser(map).compose(RxUtil.rxSchedulerHelper())
                .subscribeWith(new BaseDataSubscriber<BaseBean>(this) {
                    @Override
                    public void onAnalysisNext(BaseBean data) {
                        mView.updateUserInfo();
                    }
                }));
    }
}
