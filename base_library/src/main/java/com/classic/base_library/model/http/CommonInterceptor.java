package com.classic.base_library.model.http;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zcq on 2019/6/6.
 */

public class CommonInterceptor implements Interceptor {

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request oldRequest = chain.request();
        HttpUrl url = oldRequest.url();
        long l = System.currentTimeMillis();
        // 添加新的参数
        HttpUrl.Builder authorizedUrlBuilder = oldRequest.url().newBuilder()
                .scheme(oldRequest.url().scheme()).host(oldRequest.url().host());
        //                .addQueryParameter("userId", "14");

        // 新的请求
        Request newRequest = oldRequest.newBuilder().method(oldRequest.method(), oldRequest.body())
                .url(authorizedUrlBuilder.build()).build();

        return chain.proceed(newRequest);
    }
}
