package com.classic.base_library.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.classic.base_library.App.App;
import com.classic.base_library.R;
import com.classic.base_library.utils.NetWorkUtils;
import com.classic.base_library.utils.ToastUtil;
import com.classic.base_library.widget.LoadDialog;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;


/**
 * Created by zcq on 2019/12/22.
 */

public abstract class NetFragment extends Fragment {

    private FrameLayout netContent;
    private View content;
    private RelativeLayout rlNetMistake;
    private TextView tvErrorTip;
    private ImageView ivError;
    private SmartRefreshLayout smartRefreshLayout;

    private boolean mistake;
    private boolean mistakeLoad ;

    private ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener;
    public  RxManage mRxManage = new RxManage();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frgment_net_base, null);
        content = initView(inflater, container, savedInstanceState);
        findViews(view);
        if (content != null) {
            netContent.addView(content);
        }
        mistake = isMistakeShow();
        setNetWorkState();
        initDialog();
        initRefreshLayout();
        return view;
    }

    private void findViews(View view) {
        netContent = view.findViewById(R.id.fl_net_content);
        rlNetMistake = view.findViewById(R.id.rl_net_mistake);
        tvErrorTip = view.findViewById(R.id.tv_net_mistake);
        ivError = view.findViewById(R.id.iv_base_error);
        smartRefreshLayout = view.findViewById(R.id.refreshLayout_base);
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


    private void netMistake() {
        content.setVisibility(View.INVISIBLE);
        rlNetMistake.setVisibility(View.VISIBLE);
        tvErrorTip.setText(getResources().getString(R.string.net_mistake));
        ivError.setBackgroundResource(R.drawable.net_error);
    }


    private void serverMistake() {
        content.setVisibility(View.INVISIBLE);
        rlNetMistake.setVisibility(View.VISIBLE);
        tvErrorTip.setText(getResources().getString(R.string.services_error));
        ivError.setBackgroundResource(R.drawable.net_error);
    }

    private LoadDialog show;
    private boolean isNeedDialog;

    private void initDialog() {
        isNeedDialog = isNeedDialog();
        if (isNeedDialog && getActivity() != null) {
            show = new LoadDialog(getActivity()).builder();

        }
    }

    public boolean isNeedDialog() {
        return true;
    }

    public void showWaiteDialog() {
        if (show != null && !show.isShowing()) {
            show.show();
        }
    }

    public void closeWaiteDialog() {
        try {
            if (show != null && show.isShowing()) {
                show.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        }
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
     * ?????????????????????404????????????,????????????false
     *
     * @return
     */
    public void isSmartRefreshLayout() {
        smartRefreshLayout.setEnableRefresh(false);
    }


    /**
     * ?????????????????????
     *
     * @param error
     */
    public void showError(String error) {
        ToastUtil.showToast(error);
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

    /**
     * ????????????????????????????????????????????????????????????????????????????????????????????????
     * ???????????????????????????????????????????????????ViewPager?????????????????????????????????
     */
    protected abstract View initView(LayoutInflater inflater,
                                     @Nullable ViewGroup container,
                                     @Nullable Bundle savedInstanceState);

    @Override
    public void onDestroyView() {
        closeWaiteDialog();
        if (globalLayoutListener != null) {
            getActivity().getWindow().getDecorView().getViewTreeObserver().removeGlobalOnLayoutListener(globalLayoutListener);
            globalLayoutListener = null;
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRxManage.clear();
        closeWaiteDialog();
    }



    public void showError(int code, String errmsg) {
        mistakeLoadData();
    }

    public String getStrings(int resId) {
        if (getActivity()==null){
            return App.context.getResources().getString(resId);
        }else {
            return getResources().getString(resId);
        }
    }

    public void ptrCompleteBase() {
        if (smartRefreshLayout != null)
            smartRefreshLayout.finishRefresh();
    }
}
