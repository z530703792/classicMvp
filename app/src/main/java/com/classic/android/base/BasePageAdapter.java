package com.classic.android.base;

import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * Created by zcq on 2016/9/20.
 * viewpager的基类
 */
public abstract class BasePageAdapter<T> extends PagerAdapter {
    protected List<T> list;

    public BasePageAdapter(List<T> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        if (null == list) {
            return 0;
        }
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BaseHolder holder = getView(position);
        View view = holder.getContentView();
        container.addView(view);//添加页卡
        holder.bandDate(list.get(position), position);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    /**
     * @param postion
     * @return view
     */
    public abstract BaseHolder<T> getView(int postion);

    public void setList(List<T> list) {
        this.list = list;
    }
}
