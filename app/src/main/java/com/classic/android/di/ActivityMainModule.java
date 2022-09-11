package com.classic.android.di;

import android.app.Activity;

import com.classic.base_library.di.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zcq
 */

@Module
public class ActivityMainModule {
    private Activity mActivity;

    public ActivityMainModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }
}
