package com.classic.android.webview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.classic.base_library.R;

import com.classic.android.base.BaseActivity;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

/**
 * Created by zcq on 2019/12/21.
 */

public class WebViewActivity extends BaseActivity {

    @BindView(R.id.progress_bar_schedule)
    ProgressBar              progressBarSchedule;
    @BindView(R.id.rl_web_view)
    RelativeLayout           rlWebView;
    public BridgeWebView     mWebView;
    @BindView(R.id.view_cut_line)
    View                     viewCutLine;

    private String           title;

    private long             TIME_OUT         = 20000;
    private final static int MSG_PAGE_TIMEOUT = 1111;
    private Timer            mTimer;
    private Handler          mHandler         = new Handler() {
                                                  @Override
                                                  public void handleMessage(Message msg) {
                                                      switch (msg.what) {
                                                          case MSG_PAGE_TIMEOUT:
                                                              //这里对已经显示出页面且加载超时的情况做处理
                                                              if (mWebView != null && mWebView
                                                                      .getProgress() < 100) {
                                                                  showServicesError();
                                                              }
                                                              break;
                                                      }
                                                  }
                                              };

    @Override
    public void initEventAndData() {
        needMallClose();
        initWebView();
        initClient();
        initBridge();
        initIntentData();
    }

    @Override
    public void initInject() {

    }

    private void initWebView() {
        mWebView = new BridgeWebView(this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mWebView.setLayoutParams(params);
        rlWebView.addView(mWebView);
        getIvBack().setOnClickListener(view -> {
            if (mWebView.canGoBack()) {
                mWebView.goBack();//返回上一页面
            } else {
                finish();
            }
        });
        isSmartRefreshLayout();
    }

    @Override
    public void onMallCloseClick() {
        super.onMallCloseClick();
        finish();
    }

    private void initIntentData() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        String url = intent.getStringExtra("url");
        boolean isZoom = intent.getBooleanExtra("zoom", false);

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setBlockNetworkImage(false);
        settings.setBuiltInZoomControls(false);
        if (isZoom) {
            settings.setSupportMultipleWindows(true);
            settings.setLoadWithOverviewMode(true);
            settings.setBuiltInZoomControls(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        if (!TextUtils.isEmpty(url)) {
            mWebView.loadUrl(url);
        }
    }

    private void initClient() {
        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onReceivedTitle(WebView view, String t) {
                super.onReceivedTitle(view, t);
                if (null != t && !view.getUrl().contains(t) && TextUtils.isEmpty(title)) {
                    setTitleString(t);
                } else {
                    setTitleString(title);
                }
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBarSchedule.setProgress(newProgress);
                if (newProgress >= 99) {
                    progressBarSchedule.setVisibility(View.GONE);
                    viewCutLine.setVisibility(View.VISIBLE);
                }
            }

        });

        mWebView.setWebViewClient(new BridgeWebViewClient(mWebView) {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                progressBarSchedule.setVisibility(View.VISIBLE);
                viewCutLine.setVisibility(View.GONE);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mTimer = new Timer();
                TimerTask tt = new TimerTask() {
                    @Override
                    public void run() {
                        Message m = new Message();
                        m.what = MSG_PAGE_TIMEOUT;
                        mHandler.sendMessage(m);
                        mTimer.cancel();
                        mTimer.purge();
                    }
                };
                mTimer.schedule(tt, TIME_OUT);
            }

            /*** onPageFinished指页面加载完成,完成后取消计时器 */
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mTimer.cancel();
                mTimer.purge();
            }
        });
    }

    private void initBridge() {
        mWebView.registerHandler("xxx", (data, function) -> {
            progressBarSchedule.setVisibility(View.GONE);
        });

    }


    @Override
    public int getLayout() {
        return R.layout.activity_webview;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.destroy();
            mWebView = null;
        }
        if (rlWebView != null)
            rlWebView.removeAllViews();
    }


    //改写物理按键——返回的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();//返回上一页面
                return true;
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
