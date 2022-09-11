package com.classic.base_library.App;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.MultiDexApplication;

import com.classic.base_library.di.component.AppComponent;
import com.classic.base_library.di.component.DaggerAppComponent;
import com.classic.base_library.di.module.AppModule;
import com.classic.base_library.utils.Utils_CrashHandler;
import com.github.anzewei.parallaxbacklayout.ParallaxHelper;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshFooterCreator;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zcq on 2019/11/9.
 */

public class App extends MultiDexApplication {
    public static Context context;
    public static App instance;
    public static Map<String, Long> map = new HashMap<>();
    public static boolean GestureVerift = true;


    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        //设置全局的Header构建器

        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout refreshLayout) {
                return new MaterialHeader(context);
            }
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout refreshLayout) {
                return new ClassicsFooter(context);
            }
        });
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;



        Utils_CrashHandler.getInstance().init(this);
        registerActivityLifecycleCallbacks(ParallaxHelper.getInstance());
    }

    public static App getInstance() {
        return instance;
    }

    public static AppComponent getAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();
    }




}