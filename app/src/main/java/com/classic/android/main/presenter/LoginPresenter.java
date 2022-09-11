package com.classic.android.main.presenter;

import com.classic.android.main.presenter.contact.LoginContract;
import com.classic.base_library.model.bean.AccountBean;
import com.classic.base_library.model.bean.UserBean;
import com.classic.base_library.model.http.DataSubscriber;
import com.classic.base_library.model.http.RetrofitHelper;
import com.classic.base_library.model.http.RxUtil;
import com.classic.base_library.utils.PrefUtils;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by zcq
 */

public class LoginPresenter extends LoginContract.Presenter {

    @Inject
    public LoginPresenter(RetrofitHelper helper) {
        mRetrofitHelper = helper;
    }

    @Override
    public void accountLogin(String mobile, String accountPwd) {
        Map<String, Object> map = new HashMap<>();
        map.put("mobile", mobile);
        map.put("accountPwd", accountPwd);
        mView.showWaiteDialog();
        mRxManage
                .add(mRetrofitHelper.getApis().accountLogin(map).compose(RxUtil.rxSchedulerHelper())
                        .subscribeWith(new DataSubscriber<AccountBean, UserBean>(this) {
                            @Override
                            public void onAnalysisNext(UserBean data) {
                                PrefUtils.putUser(data);
                                mView.accountLogin();
                            }

                        }));
    }



}
