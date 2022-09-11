package com.classic.android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by zcq on 2019/7/31.
 */

public class AttestationScrollView extends ScrollView {
    public AttestationScrollView(Context context) {
        super(context);
    }

    public AttestationScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AttestationScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.onInterceptTouchEvent(ev);
    }
}
