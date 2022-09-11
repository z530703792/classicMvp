package com.classic.base_library.di.component;

import com.classic.base_library.di.module.AppModule;
import com.classic.base_library.model.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by zcq
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    RetrofitHelper retrofitHelper();  //提供http的帮助类

}
