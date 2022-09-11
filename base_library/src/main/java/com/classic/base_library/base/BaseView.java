package com.classic.base_library.base;

/**
 * Created by zcq on 2019/6/5.
 * view的基类
 */

public interface BaseView {

    void showError(String msg);

    void toLogin();

    void showWaiteDialog();

    void closeWaiteDialog();

    void showNetPage();

    void showServicesError();

    void showNetError();

    void mistakeLoadData();

    void ptrComplete();
}
