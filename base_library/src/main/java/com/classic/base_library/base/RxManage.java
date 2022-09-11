package com.classic.base_library.base;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 用于管理RxBus的事件和Rxjava相关代码的生命周期处理
 * Created by zcq
 */
public class RxManage {

    public RxBus mRxBus = RxBus.$();
    private Map<String, Observable<?>> mObservables = new HashMap<>();// 管理观察者
    private CompositeDisposable mCompositeSubscription = new CompositeDisposable();// 管理订阅者者


    public void on(String eventName, Consumer<Object> action1) {
        Observable<?> mObservable = mRxBus.register(eventName);
        mObservables.put(eventName, mObservable);
        mCompositeSubscription.add(mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1, (e) -> e.printStackTrace()));
    }

    public void add(Disposable m) {
        mCompositeSubscription.add(m);
    }

    public void clear() {
        mCompositeSubscription.dispose();// 取消订阅
        for (Map.Entry<String, Observable<?>> entry : mObservables.entrySet()) {
            mRxBus.unregister(entry.getKey(), entry.getValue());// 移除观察
        }
    }

    public void post(Object tag, Object content) {
        mRxBus.post(tag, content);
    }
}
