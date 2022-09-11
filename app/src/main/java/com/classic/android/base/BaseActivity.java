package com.classic.android.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.classic.android.Navigation;

import com.classic.android.di.ActivityMainComponent;
import com.classic.android.di.ActivityMainModule;
import com.classic.android.di.DaggerActivityMainComponent;
import com.classic.base_library.App.App;
import com.classic.base_library.base.BasePresenter;
import com.classic.base_library.base.BaseView;
import com.classic.base_library.base.NetActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zcq on 2019/6/5.
 */

public abstract class BaseActivity<T extends BasePresenter> extends NetActivity
        implements BaseView {

    @Inject
    public T         mPresenter;

    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUnBinder = ButterKnife.bind(this);
        initInject();
        if (mPresenter != null)
            mPresenter.setVM(this);
        if (this instanceof BaseView && mPresenter != null)
            mPresenter.setVM(this);
        initEventAndData();
        mRxManage.on(this.getClass().getSimpleName(), s -> toLogin());
    }

    public abstract void initEventAndData();

    public abstract void initInject();

    protected ActivityMainComponent getActivityComponent() {
        return DaggerActivityMainComponent.builder().appComponent(App.getAppComponent())
                .activityMainModule(getActivityModule()).build();
    }

    protected ActivityMainModule getActivityModule() {
        return new ActivityMainModule(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        if (mPresenter != null) {
            new Thread(() -> mPresenter.onDestroy()).start();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mPresenter != null) {
            mPresenter.onStart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPresenter != null) {
            mPresenter.onStop();
        }
    }

    @Override
    public void toLogin() {
        Navigation.login(this, false);
    }

    @Override
    public void mistakeLoadData() {
        super.mistakeLoadData();
        if (mPresenter != null)
            mPresenter.getMistakeData();
    }

    @Override
    public void ptrComplete() {
        ptrCompleteBase();
    }

}
