package com.classic.android.me.ui.activity;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.classic.android.R;
import com.classic.android.base.BaseActivity;
import com.classic.base_library.utils.ActivityJumpUtils;
import com.classic.base_library.utils.PrefUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class SafeSettingActivity extends BaseActivity {

    @BindView(R.id.tv_phone_number)
    TextView phoneNumber;

    @Override
    public void initEventAndData() {
        if (PrefUtils.getUser() != null) phoneNumber.setText(PrefUtils.getUser().getMobile());
    }

    /**
     *  void inject(RegisterActivity registerActivity);
     *  加到ActivityMainComponent中会报错，找不到Dragger.....生成类
     */
    @Override
    public void initInject() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_safe_setting;
    }

    @OnClick({R.id.btn_replace_phone,R.id.btn_set_pay_pwd})
    public void onViewClicked(View v) {
        switch (v.getId()){
            case R.id.btn_replace_phone:
                Toast.makeText(this, "更换手机号码", Toast.LENGTH_SHORT).show();
                ActivityJumpUtils.ToOtherActivity(ReplacePhoneActivity.class,this,null);
                break;
            case R.id.btn_set_pay_pwd:
                Toast.makeText(this, "设置支付密码", Toast.LENGTH_SHORT).show();
                ActivityJumpUtils.ToOtherActivity(SetPayPwdActivity.class,this,null);
                break;
        }
    }

}
