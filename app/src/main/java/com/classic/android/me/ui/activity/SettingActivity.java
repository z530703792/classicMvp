package com.classic.android.me.ui.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.classic.android.main.ui.activity.LoginActivity;
import com.classic.android.me.presenter.LogOutPresenter;
import com.classic.android.me.presenter.contract.LogOutContract;
import com.classic.base_library.model.bean.LatestVersionBean;
import com.classic.base_library.utils.ActivityJumpUtils;
import com.classic.base_library.utils.CommUtils;
import com.classic.base_library.utils.DataCleanManager;
import com.classic.base_library.utils.DialogUtils;
import com.classic.base_library.utils.ToastUtil;
import com.classic.base_library.utils.VersionCodeUtils;
import com.classic.android.BuildConfig;
import com.classic.android.R;
import com.classic.android.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: zcq 2019-08-13 15:59
 */
public class SettingActivity extends BaseActivity<LogOutPresenter> implements LogOutContract.View {
    private final static String TITLE = "设置";
    @BindView(R.id.tv_dalvik_cache)
    TextView tvDalvikCache;
    @BindView(R.id.tv_current_version)
    TextView tvCurrentVersion;
    @BindView(R.id.tv_app_version_detail)
    TextView tvAppVersionDetail;
    private LatestVersionBean bean;
    private int num = 0;
    private String cache;
    @Override
    public int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void initEventAndData() {
        setTitleString(TITLE);

        initVersion();
//        mPresenter.getLatestVersion(VersionCodeUtils.getChannelKey(this));
        setCache();

    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @OnClick({R.id.rl_dalvik_cache, R.id.rl_current_version,R.id.rl_safe_setting,R.id.rl_update_user_info,R.id.bt_login_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_dalvik_cache:
                DialogUtils.dialogTipCancel("确定清除缓存？", this, v -> {
                    DataCleanManager.clearAllCache(this);
                    setCache();
                });
                break;
            case R.id.rl_current_version:

                if (num > 10) {
                    tvAppVersionDetail.setText(VersionCodeUtils.getVersionCode(this)
                            + VersionCodeUtils.getChannel(this) + "_" + BuildConfig.BUILD_TYPE);
                }
                num++;
                break;
            case R.id.rl_safe_setting:
                ActivityJumpUtils.ToOtherActivity(SafeSettingActivity.class, this, null);
                break;
            case R.id.rl_update_user_info:
                ActivityJumpUtils.ToOtherActivity(UpdateUserInfoActivity.class, this, null);
                break;
            case R.id.bt_login_out:
                Toast.makeText(this, "退出登陆", Toast.LENGTH_SHORT).show();
                mPresenter.logOut();
                break;
        }
    }

    private void setCache() {
        cache = "";
        try {
            cache = DataCleanManager.getTotalCacheSize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvDalvikCache.setText(cache);
    }

    private void initVersion() {
        PackageManager packageManager = getPackageManager();
        PackageInfo info = null;
        try {
            info = packageManager.getPackageInfo("com.zhongfen.android",
                    PackageManager.GET_ACTIVITIES);
            String versionName = info.versionName;
            tvCurrentVersion.setText("v" + versionName);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void getLatestVersion(LatestVersionBean bean) {
        this.bean = bean;
    }



    @Override
    public boolean isMistakeShow() {
        return false;
    }

    @Override
    public void logOut() {
        ActivityJumpUtils.ToOtherActivity(LoginActivity.class,this,null);
        finish();
    }
}
