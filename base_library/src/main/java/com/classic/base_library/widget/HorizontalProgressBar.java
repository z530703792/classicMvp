package com.classic.base_library.widget;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ProgressBar;

/**
 * Created by hh on 2019/12/7.
 */

public class HorizontalProgressBar extends ProgressBar {

    private ValueAnimator valueAnimator;

    public HorizontalProgressBar(Context context) {
        super(context);
    }

    public HorizontalProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizontalProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }


    /**
     * 动画设置进度
     *
     * @param progress
     */
    public void setProgressWithAnimator(final int progress) {
        cancelProgressAnimator();
        if(progress == 0) {
            setProgress(0);
            return;
        }
        double v = progress / 100.00;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            valueAnimator = ValueAnimator.ofFloat(0f, 1.0f);
            valueAnimator.setDuration((long) (v * 2000));
            valueAnimator.start();
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    animation.getAnimatedFraction();
                    setProgress((int) (progress * animation.getAnimatedFraction()));
                }
            });
        }
    }

    public void cancelProgressAnimator() {
        if(valueAnimator != null) {
            valueAnimator.cancel();
        }
    }


}
