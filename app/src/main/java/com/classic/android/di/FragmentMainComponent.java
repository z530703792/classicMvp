package com.classic.android.di;

import android.app.Activity;

import com.classic.android.main.ui.fragment.MeFragment;
import com.classic.base_library.di.FragmentScope;
import com.classic.base_library.di.component.AppComponent;

import dagger.Component;

/**
 * Created by zcq
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentMainModule.class)
public interface FragmentMainComponent {

    Activity getActivity();

    void inject(MeFragment meFragment);

}
