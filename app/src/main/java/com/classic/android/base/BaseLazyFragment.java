package com.classic.android.base;

import android.app.Activity;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.classic.android.di.DaggerFragmentMainComponent;
import com.classic.android.di.FragmentMainModule;
import com.classic.base_library.App.App;
import com.classic.base_library.base.BasePresenter;
import com.classic.base_library.base.BaseView;
import com.classic.base_library.base.NetFragment;
import com.classic.android.Navigation;

import com.classic.android.di.FragmentMainComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zcq on 2019/6/13.
 */

public abstract class BaseLazyFragment<T extends BasePresenter> extends NetFragment
        implements BaseView {
    public final String TAG     = this.getClass().getSimpleName();

    protected View      mRootView;
    protected Activity  mActivity;
    protected boolean   isVisible;
    private boolean     isFirst = true;
    private Unbinder    bind;

    @Inject
    public T            mPresenter;

    protected FragmentMainComponent getFragmentComponent() {
        return DaggerFragmentMainComponent.builder().appComponent(App.getAppComponent())
                .fragmentMainModule(getFragmentModule()).build();
    }

    protected FragmentMainModule getFragmentModule() {
        return new FragmentMainModule(this);
    }

    //--------------------system method callback------------------------//
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = super.onCreateView(inflater, container, savedInstanceState);
        }
        initInject();
        mRxManage.on(getActivity().getPackageName(), s -> toLogin());
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mPresenter != null) {
            mPresenter.setVM(this);
        }
        bind = ButterKnife.bind(this, mRootView);
        lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            lazyLoad();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 懒加载
     */
    protected void lazyLoad() {
        if (isVisible && mRootView != null && isFirst) {
            initLazyData();
            isFirst = false;
        }
    }

    @Override
    public synchronized void toLogin() {
        Navigation.login(getActivity(), true);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            setUserVisibleHint(true);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (bind != null) {
            bind.unbind();
        }
        if (mPresenter != null) {
            new Thread(() -> mPresenter.onDestroy()).start();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mPresenter != null) {
            mPresenter.onStart();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mPresenter != null) {
            mPresenter.onStop();
        }
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

    protected abstract void initInject();

    /**
     * fragment被设置为不可见时调用
     */
    protected abstract void onInvisible();

    /**
     * 这里获取数据，刷新界面
     */
    protected abstract void initLazyData();

}
