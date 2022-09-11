package com.classic.android.main.presenter;

import com.classic.android.main.presenter.contact.GetTestCodeContract;
import com.classic.base_library.model.bean.AccountBean;
import com.classic.base_library.model.bean.UserBean;
import com.classic.base_library.model.bean.base.BaseBean;
import com.classic.base_library.model.http.BaseDataSubscriber;
import com.classic.base_library.model.http.DataSubscriber;
import com.classic.base_library.model.http.RetrofitHelper;
import com.classic.base_library.model.http.RxUtil;
import com.classic.base_library.utils.PrefUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;


public class RegisterPresenter extends GetTestCodeContract.Presenter {

    @Inject
    public RegisterPresenter(RetrofitHelper helper) {
        mRetrofitHelper = helper;
    }

    @Override
    public void getPhoneCode(String mobile) {
        Map<String, Object> map = new HashMap<>();
        map.put("telphone", mobile);
        mView.showWaiteDialog();

        mRxManage.add(mRetrofitHelper.getApis().getRegisterPhoneTestCode(map).compose(RxUtil.rxSchedulerHelper())
                .subscribeWith(new BaseDataSubscriber<BaseBean>(this) {
                    @Override
                    public void onAnalysisNext(BaseBean data) {
                        mView.getPhoneCode();
                    }
                }));
    }

    @Override
    public void nextDo(String... params) {
        Map<String, Object> map = new HashMap<>();
        map.put("mobile", params[0]);
        map.put("code", params[1]);
        mView.showWaiteDialog();

        mRxManage.add(mRetrofitHelper.getApis().accountRegister(map).compose(RxUtil.rxSchedulerHelper())
                .subscribeWith(new DataSubscriber<AccountBean, UserBean>(this) {
                    @Override
                    public void onAnalysisNext(UserBean data) {
                        PrefUtils.putUser(data);
                        mView.nextDo();
                    }
                }));
    }

    @Override
    public void startCountDown() {
        mRxManage.add(Flowable.interval(1, TimeUnit.SECONDS)
                .compose(RxUtil.rxSchedulerHelper())
                .take(61)
                .subscribe(aLong -> mView.countDown(aLong)));
    }

}
