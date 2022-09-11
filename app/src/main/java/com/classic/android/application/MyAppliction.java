package com.classic.android.application;

import android.content.Context;

import androidx.multidex.MultiDex;

import com.classic.base_library.App.App;


/**
 * Created by zcq on 2019/2/6.
 */

public class MyAppliction extends App {

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = this;

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
