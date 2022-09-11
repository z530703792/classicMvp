package com.classic.base_library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * Created by sj on 2016/9/19.
 */
public class ProhibitViewPager extends ViewPager {
    public ProhibitViewPager(Context context) {
        super(context);
    }

    public ProhibitViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 禁止滑动事件
     */

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }
}
