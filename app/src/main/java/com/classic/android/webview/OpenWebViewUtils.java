package com.classic.android.webview;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by zcq on 2019/12/29.
 */

public class OpenWebViewUtils {

    private OpenWebViewUtils() {
    }

    /**
     * 不需要缩放的调整webview
     */
    public static void toWebView(String title, String url, Activity mActivity) {
        kernel2webView(title, url, false, mActivity);
    }

    public static void kernel2webView(String title, String url, boolean zoom, Activity mActivity) {
        if (mActivity != null) {
            Intent intent = new Intent(mActivity, WebViewActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("url", url);
            intent.putExtra("zoom", zoom);
            mActivity.startActivity(intent);
        }
    }

}
