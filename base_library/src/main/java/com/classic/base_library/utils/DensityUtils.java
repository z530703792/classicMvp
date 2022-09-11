package com.classic.base_library.utils;


import android.content.Context;

import com.classic.base_library.App.App;


public class DensityUtils {

    /**
     * dp转px
     *
     * @param dp
     * @return
     */
    public static int dp2px(Context ctx, float dp) {
        float density = ctx.getResources().getDisplayMetrics().density;
        //Logger.e("density" , density+"");
        int px = (int) (dp * density + 0.5f);
        return px;
    }

    /**
     * px转dp
     *
     * @param px
     * @return
     */
    public static float px2dp(int px) {
        float density = App.getInstance().getResources().getDisplayMetrics().density;
        float dp = px / density;
        return dp;
    }

    public static int dp2px( float dp) {
        float density = App.getInstance().getResources().getDisplayMetrics().density;
        //Logger.e("density" , density+"");
        int px = (int) (dp * density + 0.5f);
        return px;
    }

}
