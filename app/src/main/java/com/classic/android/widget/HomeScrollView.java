package com.classic.android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.classic.base_library.utils.DensityUtils;

/**
 * Created by zcq on 2019/1/8.
 */

public class HomeScrollView extends ScrollView {

    private boolean isScrolledToTop = true;
    private boolean isScrolledToBottom = false;

    private boolean isShowTilte = false;

    private SimpleScrollListener mListener;

    public HomeScrollView(Context context) {
        super(context);
        init();
    }

    public HomeScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HomeScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if (getScrollY() == 0) {
            isScrolledToTop = true;
            isScrolledToBottom = false;
            if (mListener != null) {
                mListener.onTop();
            }
        } else if (getScrollY() + getHeight() - getPaddingTop() - getPaddingBottom() == getChildAt(0).getHeight()) {

            isScrolledToBottom = true;
            isScrolledToTop = false;
        } else {
            int scrollY = getScrollY();
            if (scrollY <= DensityUtils.dp2px(64) && scrollY > 0 && isShowTilte) {
                isShowTilte = false;
                if (mListener != null) {
                    mListener.onShowTitle(false);
                }
            } else if (scrollY > DensityUtils.dp2px(64) && !isShowTilte) {
                isShowTilte = true;
                if (mListener != null) {
                    mListener.onShowTitle(true);
                }
            }
            isScrolledToTop = false;
            isScrolledToBottom = false;
        }
    }

    private boolean isDown = false;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            if (mListener != null) {
                mListener.onUp();
                isDown = false;
            }
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            if (!isDown && mListener != null) {
                mListener.onDown();
                isDown = false;
            }
        }
        return super.onTouchEvent(ev);
    }

    public void setScrollListener(SimpleScrollListener mListener) {
        this.mListener = mListener;
    }
}
