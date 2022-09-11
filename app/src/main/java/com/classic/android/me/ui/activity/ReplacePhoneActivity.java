package com.classic.android.me.ui.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.classic.android.R;
import com.classic.android.base.BaseActivity;
import com.classic.android.main.presenter.contact.GetTestCodeContract;
import com.classic.android.me.presenter.ReplacePhonePresenter;
import com.classic.base_library.utils.PrefUtils;
import com.classic.base_library.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 修改支付密码和修改手机号以及获取验证码后台返回无数据（2019.12.15）
 */
public class ReplacePhoneActivity extends BaseActivity<ReplacePhonePresenter>
        implements GetTestCodeContract.View {

    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.et_phone_test_code)
    EditText etTestCode;
    @BindView(R.id.bt_get_test_code)
    Button   btGetTestCode;
    @BindView(R.id.bt_next)
    Button   btReplace;

    @Override
    public void initEventAndData() {
        btReplace.setText("确定");
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
    }



    @OnClick({ R.id.bt_get_test_code, R.id.bt_next })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_get_test_code:
                ToastUtil.showToast("获取修改账户信息验证码");
                mPresenter.getPhoneCode(etPhoneNumber.getText().toString());
                mPresenter.startCountDown();
                break;
            case R.id.bt_next:
                if (PrefUtils.getUser() != null) {
                    String accountId = String.valueOf(PrefUtils.getUser().getAccountId());
                    mPresenter.nextDo(accountId, etPhoneNumber.getText().toString(),
                            etTestCode.getText().toString());
                }
                break;
        }
    }
}
