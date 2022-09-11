package com.classic.android.utils;


import android.view.View;

import androidx.viewpager.widget.ViewPager;

/**
 * Created by zcq on 2019/5/5.
 */

public class Transformer implements ViewPager.PageTransformer {

    private int translationY;
    public static final float MAX_SCALE = 1f;
    public static final float MIN_SCALE = 0.8f;

    public Transformer() {

//        this.translationY = translationY;



    }

    /**
     * position取值特点：
     * 假设页面从0～1，则：
     * 第一个页面position变化为[0,-1]
     * 第二个页面position变化为[1,0]
     *
     * @param page
     * @param position
     */
    @Override
    public void transformPage(View page, float position) {
        /*if (position < 0 ) {
            page.setTranslationY(translationY * -position * 100 / 100);
        } else if (position > 0 ) {
            page.setTranslationY(translationY * position * 100 / 100);
        }*/

        if (position < -1) {
            position = -1;
        } else if (position > 1) {
            position = 1;
        }

        float tempScale = position < 0 ? 1 + position : 1 - position;

        float slope = (MAX_SCALE - MIN_SCALE) / 1;
        //一个公式
        float scaleValue = MIN_SCALE + tempScale * slope;
        page.setScaleX(scaleValue);
        page.setScaleY(scaleValue);

    }
}
