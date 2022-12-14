package com.classic.base_library.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.classic.base_library.R;
import com.classic.base_library.utils.NetWorkUtils;
import com.classic.base_library.utils.StatusBarUtil;
import com.classic.base_library.utils.ToastUtil;
import com.classic.base_library.widget.LoadDialog;
import com.github.anzewei.parallaxbacklayout.ParallaxBack;
import com.github.anzewei.parallaxbacklayout.ParallaxHelper;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;


/**
 * Created by zcq on 2019/12/19.
 */
@ParallaxBack(edge = ParallaxBack.Edge.LEFT,layout = ParallaxBack.Layout.PARALLAX)
public abstract class NetActivity extends AppCompatActivity implements View.OnClickListener {

    public final String                             TAG       = this.getClass().getSimpleName();

    private TextView                                tvTitle, tvRightTitle, tvErrorTip,
            ivCloseMall;
    private RelativeLayout                          rlNetMistake, rlTitle, rlTitleContent;
    private FrameLayout                             flContent;
    private SmartRefreshLayout smartRefreshLayout;
    private ImageButton                             ivBack;
    private ImageView                               ivError;
    private View                                    topView;

    private boolean                                 mistake;
    private boolean                                 mistakeLoad, titleMistake;
    private View                                    content;
    private ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener;
    private Handler                                 handler   = new Handler();
    public  RxManage                                mRxManage = new RxManage();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_base_net);
        mistake = isMistakeShow();
        findViews();
        initContent();
        initClick();
        initDialog();
        initRefreshLayout();
        setNetWorkState();
        initTopView();
    }

    public ImageButton getIvBack() {
        return ivBack;
    }

    private void findViews() {
        tvTitle = findViewById(R.id.tv_title);
        tvRightTitle = findViewById(R.id.tv_right_title);
        tvErrorTip = findViewById(R.id.tv_net_mistake);
        ivError = findViewById(R.id.iv_base_error);
        rlNetMistake = findViewById(R.id.rl_net_mistake);
        rlTitleContent = findViewById(R.id.rl_title_content);
        rlTitle = findViewById(R.id.rl_title);
        ivCloseMall = findViewById(R.id.iv_close_mall);
        flContent = findViewById(R.id.fl_content);
        ivBack = findViewById(R.id.ib_mall_back);
        topView = findViewById(R.id.view);
        smartRefreshLayout = findViewById(R.id.refreshLayout_base);
    }

    private void initTopView() {
        if (isStatusBarLightMode()) {
            StatusBarUtil.StatusBarLightMode(this);
        }else {
            StatusBarUtil.transparencyBar(this);
        }
    }

    private void initContent() {
        content = getLayoutInflater().inflate(getLayout(), null);
        flContent.addView(content);
    }

    private void initRefreshLayout() {
        smartRefreshLayout.setOnRefreshListener(refreshlayout -> {
            if (NetWorkUtils.isNetworkConnected()) {
                mistakeLoad = true;
                mistakeLoadData();
            }else{
                ptrCompleteBase();
            }
        });
    }

    private boolean setNetWorkState() {
        boolean networkConnected = NetWorkUtils.isNetworkConnected();
        if (!networkConnected && mistake) {
            if (content != null) {
                netMistake();
                return false;
            }
        }
        return true;
    }

    private void netMistake() {
        titleMistake = true;
        content.setVisibility(View.INVISIBLE);
        rlNetMistake.setVisibility(View.VISIBLE);
        tvErrorTip.setText(getResources().getString(R.string.net_mistake));
        ivError.setBackgroundResource(R.drawable.net_error);
    }

    private void serverMistake() {
        titleMistake = true;
        content.setVisibility(View.INVISIBLE);
        rlNetMistake.setVisibility(View.VISIBLE);
        tvErrorTip.setText(getResources().getString(R.string.services_error));
        ivError.setBackgroundResource(R.drawable.net_error);
    }

    public void noMistake(String no) {
        titleMistake = true;
        content.setVisibility(View.INVISIBLE);
        rlNetMistake.setVisibility(View.VISIBLE);
        tvErrorTip.setText(no);
        ivError.setBackgroundResource(R.drawable.img_blank);
    }

    private void initClick() {
        ivBack.setOnClickListener(this);
        tvRightTitle.setOnClickListener(this);
        ivCloseMall.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == ivBack.getId()) {
            onIbBackClick();
        } else if (v.getId() == tvRightTitle.getId()) {
            onRightTitleClick();
        } else if (v.getId() == ivCloseMall.getId()) {
            onMallCloseClick();
        }
    }

    private LoadDialog show;
    private boolean      isNeedDialog;

    private void initDialog() {
        isNeedDialog = isNeedDialog();
        if (isNeedDialog) {
            show = new LoadDialog(this).builder();
        }
    }

    public boolean isNeedDialog() {
        return true;
    }

    public void showWaiteDialog() {
        if (titleMistake) {
            titleMistake = false;
            return;
        }
        if (show != null && !show.isShowing() && !this.isFinishing()) {
            show.show();
        }
    }

    public void closeWaiteDialog() {
        try {
            if (show != null && show.isShowing() && !this.isFinishing()) {
                show.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ???????????????
     */
    public void disableBack(){
        ParallaxHelper.getInstance().disableParallaxBack(this);
    }

    /**
     * ?????????????????????404??????,????????????false
     *
     * @return
     */
    public boolean isMistakeShow() {
        return true;
    }


    /**
     * ??????
     *
     * @return
     */
    public boolean isStatusBarLightMode() {
        return true;
    }

    /**
     * ?????????????????????404????????????,????????????false
     *
     * @return
     */
    public void isSmartRefreshLayout() {
        smartRefreshLayout.setEnableRefresh(false);
    }

    /**
     * ????????????title
     *
     * @param stringId
     */
    public void setTitleRes(int stringId) {
        tvTitle.setText(stringId);
    }

    /**
     * ??????
     */
    public void needMallClose() {
        ivCloseMall.setVisibility(View.VISIBLE);
    }

    public void setBackVisble(int visble) {
        ivBack.setVisibility(visble);
    }

    /**
     * ????????????title
     *
     * @param stringId
     */
    public void setTitleString(String stringId) {
        tvTitle.setText(stringId);
    }

    /**
     * ???????????????????????????
     */
    public void noTitle() {
        rlTitle.setVisibility(View.GONE);
    }

    /**
     * ?????????????????????????????????
     */
    public void noState() {
        rlTitleContent.setVisibility(View.GONE);
    }

    /**
     * ???????????????????????????
     *
     * @param stringId
     */
    public void setRightTitle(int stringId) {
        tvRightTitle.setText(stringId);
        if (!titleMistake) {
            tvRightTitle.setVisibility(View.VISIBLE);
        }
    }

    /**
     * ???????????????????????????
     *
     * @param string
     */
    public void setRightTitleString(String string) {
        tvRightTitle.setText(string);
        if (!titleMistake) {
            tvRightTitle.setVisibility(View.VISIBLE);
        }
    }

    //???????????????????????????????????????????????????
    public void setTitleBackgroundResource(int resId) {
        rlTitle.setBackgroundResource(resId);
        tvTitle.setTextColor(getResources().getColor(R.color.white));
    }

    public void setTvRightVisibility(int visibility) {
        tvRightTitle.setVisibility(visibility);
    }

    public void onRightTitleClick() {
        //?????????,????????????????????????????????????
    }

    /**
     * ?????????????????????
     */
    public void onIbBackClick() {
        finish();
    }

    /**
     * ??????????????????
     */
    public void onMallCloseClick() {

    }

    /**
     * ?????????????????????
     *
     * @param error
     */
    public void showError(String error) {
//        DialogUtils.dialogTip(error, this);
        ToastUtil.showToast(error);
    }


    /**
     * ??????404??????
     */
    public void showNetError() {
        if (content != null && isMistakeShow()) {
            netMistake();
        } else {
            ToastUtil.showToast(getResources().getString(R.string.net_mistake));
        }
    }

    public void showServicesError() {
        if (content != null && isMistakeShow()) {
            serverMistake();
        } else {
            ToastUtil.showToast(getResources().getString(R.string.services_error));
        }
    }

    /**
     * ??????????????????
     */
    public void showNetPage() {
        if (mistakeLoad && content != null) {
            mistakeLoad = false;
            content.setVisibility(View.VISIBLE);
            rlNetMistake.setVisibility(View.INVISIBLE);
            tvRightTitle.setVisibility(View.VISIBLE);
            tvTitle.setVisibility(View.VISIBLE);
            titleMistake = false;
        }
    }

    @Override
    protected void onDestroy() {
        if (globalLayoutListener != null) {
            getWindow().getDecorView().getViewTreeObserver()
                    .removeGlobalOnLayoutListener(globalLayoutListener);
            globalLayoutListener = null;
        }
        mRxManage.clear();
        closeWaiteDialog();
        show = null;
        super.onDestroy();
    }

    /**
     * 404?????????????????????????????????????????????
     */
    public void mistakeLoadData() {
        if (!setNetWorkState()) {
            return;
        }
        closeWaiteDialog();
        showWaiteDialog();
    }

    @Override
    public void finish() {
        closeWaiteDialog();
        super.finish();
    }

    /**
     * @return ????????????????????????layout???id
     */
    public abstract int getLayout();


    public void showError(int code, String errmsg) {
        mistakeLoadData();
    }

    public void ptrCompleteBase() {
        if (smartRefreshLayout != null)
            smartRefreshLayout.finishRefresh();
    }
}
