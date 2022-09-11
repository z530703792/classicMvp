package com.classic.android.me.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.classic.android.R;
import com.classic.android.base.BaseActivity;
import com.classic.android.me.presenter.UpdateUserInfoPresenter;
import com.classic.android.me.presenter.contract.UpdateUserInfoContract;
import com.classic.android.widget.DatePickerFragment;
import com.classic.android.widget.NickNameDialog;
import com.classic.base_library.utils.PrefUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class UpdateUserInfoActivity extends BaseActivity<UpdateUserInfoPresenter> implements UpdateUserInfoContract.View {

    @BindView(R.id.tv_nickname)
    TextView tvNickName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_birth)
    public TextView tvBirth;
    @BindView(R.id.tv_signature)
    TextView tvSignature;

    private NickNameDialog nickNameDialog;
    private AlertDialog sexDialog;
    private NickNameDialog signatureDialog;

    @Override
    public void initEventAndData() {
    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_update_user_info;
    }

    @OnClick({R.id.bt_save, R.id.rl_update_nickname, R.id.rl_update_sex, R.id.rl_update_birth, R.id.rl_signature})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_update_nickname:
                Toast.makeText(this, "修改昵称", Toast.LENGTH_SHORT).show();
                updateNickNameDialog();
                break;
            case R.id.rl_update_sex:
                Toast.makeText(this, "修改性别", Toast.LENGTH_SHORT).show();
                updateSexDialog();
                break;
            case R.id.rl_update_birth:
                Toast.makeText(this, "修改生日", Toast.LENGTH_SHORT).show();
                showTimePickerDialog();
                break;
            case R.id.rl_signature:
                Toast.makeText(this, "签名", Toast.LENGTH_SHORT).show();
                updateSignatureDialog();
                break;
            case R.id.bt_save:
                int sex = -1;
                if ("保密".equals(tvSex.getText().toString()))  sex = 0;
                else if ("男".equals(tvSex.getText().toString())) sex = 1;
                else if ("女".equals(tvSex.getText().toString())) sex = 2;
                else showError("onclick 出错了");
                mPresenter.updateUserInfo(PrefUtils.getUser().getMobile(), tvNickName.getText().toString(), sex, tvSignature.getText().toString(), tvBirth.getText().toString());
                break;
        }
    }

    private void updateNickNameDialog() {
        nickNameDialog = new NickNameDialog(UpdateUserInfoActivity.this).builder()
                .setEditText("请输入昵称")
                .setCancelable(true)
                .setEditTextContent(PrefUtils.getUser().getNickName())
                .setPositiveButton("", nickNameClickListener);
        nickNameDialog.show();
    }

    //昵称dialog的监听器
    private View.OnClickListener nickNameClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            tvNickName.setText(nickNameDialog.getResult());
            nickNameDialog.dismiss();
        }
    };

    private void updateSexDialog() {
        final String[] sexItems = {"保密","男", "女"};
        sexDialog = new AlertDialog.Builder(UpdateUserInfoActivity.this)
                .setItems(sexItems, sexClickListener).create();
        sexDialog.show();
    }

    //性别dialog的监听器
    private DialogInterface.OnClickListener sexClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == 0)  tvSex.setText("保密");
            else if (which == 1) tvSex.setText("男");
            else if (which == 2) tvSex.setText("女");
            else showError("出错了");
        }
    };

    //生日
    public void showTimePickerDialog() {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    //签名
    private void updateSignatureDialog() {
        signatureDialog = new NickNameDialog(UpdateUserInfoActivity.this).builder()
                .setEditText("请输入签名")
                .setCancelable(true)
                .setEditTextContent(TextUtils.isEmpty(PrefUtils.getUser().getSignature()) ? "这个人很懒，没有签名" : PrefUtils.getUser().getSignature())
                .setPositiveButton("", signatureClickListener);

        signatureDialog.show();
    }

    //签名dialog的监听器
    private View.OnClickListener signatureClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            tvSignature.setText(signatureDialog.getResult());
            signatureDialog.dismiss();
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (nickNameDialog != null) nickNameDialog.dismiss();
        if (sexDialog != null) sexDialog.dismiss();
    }

    @Override
    public void updateUserInfo() {
        Toast.makeText(this, "完善个人用户信息成功", Toast.LENGTH_SHORT).show();
        finish();
    }
}
