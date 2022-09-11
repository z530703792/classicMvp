package com.classic.android.me.ui.activity;

import android.graphics.Color;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.classic.android.R;
import com.classic.android.base.BaseActivity;
import com.classic.base_library.utils.StringUtils;
import com.classic.base_library.widget.ObservableScrollView;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;


import butterknife.BindView;

/**
 * Created by zcq on 2019/12/25.
 */

public class MeActivity extends BaseActivity implements ObservableScrollView.OnObservableScrollViewListener {

    @BindView(R.id.rl_main_topContent)
    FrameLayout                     rlMainTopContent;
    @BindView(R.id.sv_main_content)
    ObservableScrollView            svMainContent;
    @BindView(R.id.tv_title)
    TextView                        tvTitle;
    @BindView(R.id.ll_header_content)
    RelativeLayout                  llHeaderContent;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private int                     mHeight;
    private boolean                 isTitle            = true;
    private String                  title;

    @Override
    public void initEventAndData() {
        //获取标题栏高度
        ViewTreeObserver viewTreeObserver = rlMainTopContent.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rlMainTopContent.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mHeight = rlMainTopContent.getHeight() - llHeaderContent.getHeight();
                svMainContent.setOnObservableScrollViewListener(MeActivity.this);
            }
        });
        initView();
    }

    @Override
    public void initInject() {

    }

    private void initView() {
        refreshLayout.setNestedScrollingEnabled(false);
        refreshLayout.setOnRefreshListener(refreshlayout -> {

        });
        title = "zcq";
        if (isTitle)
            tvTitle.setText("xxx");

    }


    @Override
    public boolean isMistakeShow() {
        return false;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_me_circle;
    }

    @Override
    public void ptrComplete() {
        super.ptrComplete();
        if (refreshLayout != null)
            refreshLayout.finishRefresh();
    }


    /**
     * 获取ObservableScrollView的滑动数据
     *
     * @param l
     * @param t
     * @param oldl
     * @param oldt
     */
    @Override
    public void onObservableScrollViewListener(int l, int t, int oldl, int oldt) {
        if (t <= 0) {
            //顶部图处于最顶部，标题栏透明
            llHeaderContent.setBackgroundColor(Color.argb(0, 60, 125, 208));
            setTvTitle(true, 255);
        } else if (t > 0 && t < mHeight) {
            //滑动过程中，渐变
            float scale = (float) t / mHeight;//算出滑动距离比例
            float alpha = (255 * scale);//得到透明度
            llHeaderContent.setBackgroundColor(Color.argb((int) alpha, 60, 125, 208));
            if (t > 0 && t < mHeight / 2) {
                alpha = 255 * (1 - scale);
            }
            setTvTitle(t > 0 && t < mHeight / 2, alpha);
        } else {
            //过顶部图区域，标题栏定色
            setTvTitle(false, 255);
            llHeaderContent.setBackgroundColor(Color.argb(255, 60, 125, 208));
        }
    }

    private void setTvTitle(boolean is, float alpha) {
        isTitle = is;
        tvTitle.setText(is && !StringUtils.isEmpty(title) ? title : getString(R.string.app_name));
        tvTitle.setTextColor(Color.argb((int) alpha, 255, 255, 255));

    }

}
