package com.classic.android.main.ui.activity;

import android.view.View;
import android.widget.EditText;

import com.classic.android.R;
import com.classic.android.base.BaseActivity;
import com.classic.android.main.presenter.LoginPresenter;
import com.classic.android.main.presenter.contact.LoginContract;
import com.classic.base_library.utils.ActivityJumpUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zcq
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.et_pwd)
    EditText etPwd;

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initEventAndData() {

    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public boolean isMistakeShow() {
        return false;
    }

    @Override
    public void accountLogin() {
        ActivityJumpUtils.ToOtherActivity(MainActivity.class, this, null);
        finish();
    }

    @OnClick({R.id.bt_login, R.id.bt_to_register_view, R.id.bt_to_phone_login_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                mPresenter.accountLogin(etPhoneNumber.getText().toString(), etPwd.getText().toString());
                break;
            case R.id.bt_to_register_view:
                ActivityJumpUtils.ToOtherActivity(RegisterActivity.class, this, null);
                break;
            case R.id.bt_to_phone_login_view:
                ActivityJumpUtils.ToOtherActivity(PhoneLoginActivity.class, this, null);
                break;
        }
    }
}
