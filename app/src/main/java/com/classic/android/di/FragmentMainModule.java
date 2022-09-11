package com.classic.android.di;

import android.app.Activity;


import androidx.fragment.app.Fragment;

import com.classic.base_library.di.FragmentScope;

import dagger.Module;
import dagger.Provides;


/**
 * Created by zcq
 */

@Module
public class FragmentMainModule {

    private Fragment fragment;

    public FragmentMainModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }
}
