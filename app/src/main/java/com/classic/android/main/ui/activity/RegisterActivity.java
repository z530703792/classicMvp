package com.classic.android.main.ui.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.classic.android.R;
import com.classic.android.base.BaseActivity;
import com.classic.android.main.presenter.RegisterPresenter;
import com.classic.android.main.presenter.contact.GetTestCodeContract;
import com.classic.base_library.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登陆功能待验证
 */
public class RegisterActivity extends BaseActivity<RegisterPresenter> implements GetTestCodeContract.View {

    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.et_phone_test_code)
    EditText etTestCode;
    @BindView(R.id.bt_get_test_code)
    Button btGetTestCode;
    @BindView(R.id.bt_next)
    Button btRegister;

    @Override
    public int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initEventAndData() {
        btRegister.setText("注册");
    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void getPhoneCode() {
        ToastUtil.showToast("获取验证码成功");
    }

    @Override
    public void countDown(long time) {
        if (time < 60) {
            btGetTestCode.setClickable(false);
            btGetTestCode.setBackgroundColor(Color.GRAY);
            btGetTestCode.setText(String.valueOf(60 - time) + "s");
        } else {
            btGetTestCode.setClickable(true);
            btGetTestCode.setBackgroundColor(Color.rgb(255,140,60));
            btGetTestCode.setText("获取验证码");
        }
    }

    @Override
    public void nextDo() {
        ToastUtil.showToast("注册成功");
    }

    @OnClick({R.id.bt_get_test_code, R.id.bt_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_get_test_code:
                Toast.makeText(this, "获取验证码", Toast.LENGTH_SHORT).show();
                mPresenter.getPhoneCode(etPhoneNumber.getText().toString());
                mPresenter.startCountDown();
                break;
            case R.id.bt_next:
                mPresenter.nextDo(etPhoneNumber.getText().toString(), etTestCode.getText().toString());
                break;
        }
    }
}
