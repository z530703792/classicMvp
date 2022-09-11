package com.classic.android.base;

import android.app.Activity;
import android.view.View;


/**
 * Created by zcq on 2016/9/19.
 * baseHolder基类
 */
public abstract class BaseHolder<T> {
    protected View contentView;
    protected Activity mActivity;

    public BaseHolder() {

    }

    public BaseHolder(Activity mActivity) {
        this.mActivity = mActivity;
        this.contentView = initView();

    }

    /**
     * 得到holder的布局
     *
     * @return view
     */
    public View getContentView() {
        return contentView;
    }

    /**
     * 初始化布局
     *
     * @return
     */
    public abstract View initView();

    /**
     * 初始化数据
     */
    public abstract void bandDate(Object obj);

    /**
     * 初始化数据
     */
    public void bandDate(T obj, int position) {

    }
}
