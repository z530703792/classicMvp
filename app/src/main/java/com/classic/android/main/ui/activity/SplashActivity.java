package com.classic.android.main.ui.activity;

import android.Manifest;
import android.content.pm.ActivityInfo;

import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.classic.android.R;
import com.classic.android.base.BaseActivity;
import com.classic.android.main.presenter.SplashPresenter;
import com.classic.android.main.presenter.contact.SplashContract;
import com.classic.base_library.utils.ActivityJumpUtils;
import com.classic.base_library.utils.PrefUtils;
import com.classic.base_library.utils.ToastUtil;
import com.classic.base_library.widget.AlertDialog;

import butterknife.BindView;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * 起始页
 */
@RuntimePermissions
public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View {

    @BindView(R.id.iv_action_pic)
    ImageView ivActionPic;
    @BindView(R.id.tv_count_down)
    TextView tvCountDown;
    private boolean hasExteralPermission = false;

    @Override
    public void initEventAndData() {
        noState();
        disableBack();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        SplashActivityPermissionsDispatcher.eternalStorageNeedWithPermissionCheck(this);
        mPresenter.startCountDown();
    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void mistakeLoadData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public boolean isMistakeShow() {
        return false;
    }

    @Override
    public void countDown(long time) {
        tvCountDown.setText("跳过" + String.valueOf(3 - time));
    }

    @Override
    public void toMain() {
//        ActivityJumpUtils.ToOtherActivity(
//                PrefUtils.getBoolean("newApp", true) ? GuideActivity.class
//                        : SystemUtil.isLogin() ? MainActivity.class : LoginActivity.class,
//                this, null);
        ActivityJumpUtils.ToOtherActivity(
                PrefUtils.getBoolean("newApp", true) ? GuideActivity.class : MainActivity.class,
                this, null);
        finish();
    }

    @OnClick(R.id.tv_count_down)
    public void onViewClicked() {
        hasExteralPermission = true;
        if (hasExteralPermission) {
            toMain();
        }
    }

    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void eternalStorageNeed() {
        hasExteralPermission = true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        SplashActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode,
                grantResults);
    }

    @OnShowRationale({Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void eternalStorageRationale(final PermissionRequest request) {
        request.proceed();
    }

    @OnPermissionDenied({Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void eternalStorageDenied() {
        //        ToastUtil.showToast("您拒绝了权限，可能会导致该应用内部发生错误,请尽快授权");
        initPermissionsDialog();
    }

    @OnNeverAskAgain({Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void eternalStorageAgain() {
        ToastUtil.showToast("请到设置界面检查权限");
    }

    private void initPermissionsDialog() {
        new AlertDialog(this).builder().setTitle("权限提示：").setMsg("您拒绝了权限，会导致该应用内部发生错误,请尽快授权！")
                .setPositiveButton("重新授权",
                        arg -> SplashActivityPermissionsDispatcher
                                .eternalStorageNeedWithPermissionCheck(this))
                .setNegativeButton("退出程序", arg -> finish()).setCancelable(false).show();
    }

}
