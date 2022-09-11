package com.classic.android.me.presenter;

import com.classic.android.main.presenter.contact.GetTestCodeContract;
import com.classic.base_library.model.bean.base.BaseBean;
import com.classic.base_library.model.http.BaseDataSubscriber;
import com.classic.base_library.model.http.RetrofitHelper;
import com.classic.base_library.model.http.RxUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class ReplacePhonePresenter extends GetTestCodeContract.Presenter {

    @Inject
    public ReplacePhonePresenter(RetrofitHelper helper) {
        mRetrofitHelper = helper;
    }

    /**
     * 获取验证码
     *
     * @param mobile
     */
    @Override
    public void getPhoneCode(String mobile) {
        Map<String, Object> map = new HashMap<>();
        map.put("telphone", mobile);
        mView.showWaiteDialog();

        mRxManage.add(mRetrofitHelper.getApis().getUpdateAccountPhoneCode(map).compose(RxUtil.rxSchedulerHelper())
                .subscribeWith(new BaseDataSubscriber<BaseBean>(this) {
                    @Override
                    public void onAnalysisNext(BaseBean data) {
                        mView.getPhoneCode();
                    }
                }));
    }

    /**
     * 获取验证码后确定
     *
     * @param params
     */
    @Override
    public void nextDo(String... params) {
        Map<String, Object> map = new HashMap<>();
        map.put("accountId", Integer.valueOf(params[0]));
        map.put("mobile", params[1]);
        map.put("code", params[2]);
        mView.showWaiteDialog();

        mRxManage
                .add(mRetrofitHelper.getApis().updatePhone(map).compose(RxUtil.rxSchedulerHelper())
                        .subscribeWith(new BaseDataSubscriber<BaseBean>(this) {
                            @Override
                            public void onAnalysisNext(BaseBean data) {
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
