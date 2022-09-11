package com.classic.android.me.ui.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.classic.android.R;
import com.classic.android.base.BaseActivity;
import com.classic.android.main.presenter.contact.GetTestCodeContract;
import com.classic.android.me.presenter.SetPayPwdPresenter;
import com.classic.base_library.utils.PrefUtils;
import com.classic.base_library.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class SetPayPwdActivity extends BaseActivity<SetPayPwdPresenter> implements GetTestCodeContract.View {

    @BindView(R.id.rl_phone_number)
    RelativeLayout rlPhoneNumber;
    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;

    @BindView(R.id.et_phone_test_code)
    EditText etTestCode;
    @BindView(R.id.bt_get_test_code)
    Button btGetTestCode;

    @BindView(R.id.rl_set_pay_pwd)
    RelativeLayout rlSetPayPwd;
    @BindView(R.id.et_pay_pwd)
    EditText etPayPwd;

    @BindView(R.id.bt_next)
    Button btNext;

    @Override
    public void initEventAndData() {
        rlPhoneNumber.setVisibility(View.GONE);
        rlSetPayPwd.setVisibility(View.VISIBLE);
        btNext.setText("确定");
    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_register;
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
            btGetTestCode.setBackgroundColor(Color.rgb(255, 140, 60));
            btGetTestCode.setText("获取验证码");
        }
    }

    @Override
    public void nextDo() {
        ToastUtil.showToast("更改成功");
        finish();
    }

    @OnClick({R.id.bt_get_test_code, R.id.bt_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_get_test_code:
                Toast.makeText(this, "获取验证码" + (mPresenter != null), Toast.LENGTH_SHORT).show();
                if (mPresenter != null) {
                    mPresenter.getPhoneCode(PrefUtils.getUser().getMobile());
                    mPresenter.startCountDown();
                }
                break;
            case R.id.bt_next:
                Toast.makeText(this, "确定", Toast.LENGTH_SHORT).show();
                if (PrefUtils.getUser() != null) {
                    String mobile = String.valueOf(PrefUtils.getUser().getMobile());
                    mPresenter.nextDo(mobile, etTestCode.getText().toString(), etPayPwd.getText().toString());
                }

                break;
        }
    }
}
