package com.classic.base_library.model.bean;

import com.classic.base_library.model.bean.base.BaseEntity;
import com.classic.base_library.model.http.RetrofitHelper;
import com.classic.base_library.model.http.RxUtil;

import io.reactivex.Flowable;

/**
 * @author: zcq  2019-12-24 18:10
 */
public class BillBean extends BaseEntity.ListBean {
    @Override
    public Flowable getPageAt(String type) {
        return new RetrofitHelper().getApis().getAllInviteRecord(param).compose(RxUtil.rxSchedulerHelper());
    }
}
