package com.classic.android.di;

import android.app.Activity;

import com.classic.android.main.ui.activity.LoginActivity;
import com.classic.android.main.ui.activity.PhoneLoginActivity;
import com.classic.android.main.ui.activity.RegisterActivity;
import com.classic.android.main.ui.activity.SplashActivity;
import com.classic.android.me.ui.activity.ReplacePhoneActivity;
import com.classic.android.me.ui.activity.SetPayPwdActivity;
import com.classic.android.me.ui.activity.SettingActivity;
import com.classic.android.me.ui.activity.UpdateUserInfoActivity;
import com.classic.base_library.di.ActivityScope;
import com.classic.base_library.di.component.AppComponent;

import dagger.Component;

/**
 * Created by zcq
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityMainModule.class)
public interface ActivityMainComponent {

    Activity getActivity();

    void inject(SplashActivity splashActivity);

    void inject(LoginActivity loginActivity);

    void inject(PhoneLoginActivity phoneLoginActivity);

    void inject(RegisterActivity registerActivity);

    void inject(SetPayPwdActivity setPayPwdActivity);

    void inject(ReplacePhoneActivity replacePhoneActivity);

    void inject(UpdateUserInfoActivity updateUserInfoActivity);

    void inject(SettingActivity settingActivity);


}
