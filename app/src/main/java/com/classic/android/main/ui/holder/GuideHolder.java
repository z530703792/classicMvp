package com.classic.android.main.ui.holder;


import android.view.View;

import com.classic.android.base.BaseHolder;

import java.util.List;

/**
 * Created by zcq on 2016/9/20.
 * 引导页holder
 */
public class GuideHolder extends BaseHolder<View> {
    View view;
    List<View> list;
    int postion;
    public GuideHolder(List<View> list, int postion) {
        this.list = list;
        this.postion= postion;
        contentView = initView();

    }

    @Override
    public View initView() {
        view = list.get(postion);
        return view;
    }

    @Override
    public void bandDate(Object obj) {

    }

    @Override
    public void bandDate(View obj, int position) {
    }
}

