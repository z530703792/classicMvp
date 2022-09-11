package com.classic.android.main.ui.adapter;

import android.view.View;

import com.classic.android.base.BaseHolder;
import com.classic.android.base.BasePageAdapter;
import com.classic.android.main.ui.holder.GuideHolder;

import java.util.List;
/**
 * Created by zcq on 2016/9/23.
 * home页面产品的adpter
 */
public class GuideAdapter extends BasePageAdapter<View> {

    public GuideAdapter(List<View> list) {
        super(list);
    }

    @Override
    public BaseHolder getView(int postion) {
        return new GuideHolder( list, postion);
    }
}