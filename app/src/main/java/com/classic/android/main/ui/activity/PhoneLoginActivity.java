package com.classic.android.main.ui.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.classic.android.R;
import com.classic.android.base.BaseActivity;
import com.classic.android.main.presenter.PhoneLoginPresenter;
import com.classic.android.main.presenter.contact.GetTestCodeContract;
import com.classic.base_library.utils.ActivityJumpUtils;
import com.classic.base_library.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class PhoneLoginActivity extends BaseActivity<PhoneLoginPresenter>
        implements GetTestCodeContract.View {
    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.et_phone_test_code)
    EditText etTestCode;
    @BindView(R.id.bt_get_test_code)
    Button   btGetTestCode;
    @BindView(R.id.bt_next)
    Button   btLogin;

    @Override
    public int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initEventAndData() {
        btLogin.setText("登陆");
    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void getPhoneCode() {
        Toast.makeText(this, "获取验证码成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void countDown(long time) {
        if (time < 60) {
            btGetTestCode.setClickable(false);
            btGetTestCode.setBackgroundColor(Color.GRAY);
            btGetTestCode.setText(String.valueOf(60 - time) + "s");
        } else {
            btGetTestCode.setClickable(true);
            btGetTestCode.setBackgroundColor(Color.rgb(255, 140, 60));
            btGetTestCode.setText("获取验证码");
        }
    }

    @Override
    public void nextDo() {
        ToastUtil.showToast("登陆成功，即将跳转主页面");
        ActivityJumpUtils.ToOtherActivity(MainActivity.class, this, null);
        //        //临时的判断逻辑,和后台对接明白后更改
        //        AccountBean account = (AccountBean) bean;
        //        if (account != null) {
        ////            ToastUtil.showToast("登陆成功，跳转主页面");
        //            Toast.makeText(this, "登陆成功，即将跳转主页面", Toast.LENGTH_SHORT).show();
        //            ActivityJumpUtils.ToOtherActivity(MainActivity.class,this,null);
        //        } else {
        //            Toast.makeText(this, "登陆失败", Toast.LENGTH_SHORT).show();
        //        }
    }

    @OnClick({ R.id.bt_get_test_code, R.id.bt_next })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_get_test_code:
                mPresenter.getPhoneCode(etPhoneNumber.getText().toString());
                mPresenter.startCountDown();
                break;
            case R.id.bt_next:
                mPresenter.nextDo(etPhoneNumber.getText().toString(),
                        etTestCode.getText().toString());
                break;
        }
    }
}
