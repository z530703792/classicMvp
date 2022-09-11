package com.classic.android.me.presenter;

import com.classic.android.me.presenter.contract.CardBagContract;
import com.classic.base_library.model.http.RetrofitHelper;

import javax.inject.Inject;

public class CardBagPresenter extends CardBagContract.Presenter {

    @Inject
    public CardBagPresenter(RetrofitHelper helper) {
        mRetrofitHelper = helper;
    }

}
