package com.classic.android.main.ui.fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.classic.android.R;
import com.classic.android.base.BaseLazyFragment;
import com.classic.android.main.presenter.UpdateUserHeaderPresenter;
import com.classic.android.main.presenter.contact.UpdateUserHeaderContract;
import com.classic.android.me.ui.activity.SettingActivity;
import com.classic.android.me.ui.activity.UpdateUserInfoActivity;
import com.classic.android.me.ui.activity.WalletActivity;
import com.classic.base_library.utils.ActivityJumpUtils;
import com.classic.base_library.utils.PrefUtils;
import com.classic.base_library.widget.CircleImageView;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.photopicker.util.BGAPhotoHelper;
import cn.bingoogolapple.photopicker.util.BGAPhotoPickerUtil;
import permissions.dispatcher.NeedsPermission;

import static android.app.Activity.RESULT_OK;

/**
 * 我的 Created by zcq
 */
public class MeFragment extends BaseLazyFragment<UpdateUserHeaderPresenter>
        implements UpdateUserHeaderContract.View {
    private static final int REQUEST_CODE_CHOOSE_PHOTO = 1;
    private static final int REQUEST_CODE_CROP         = 2;
    private BGAPhotoHelper   mPhotoHelper;

    @BindView(R.id.iv_my_head)
    CircleImageView          imgUserHead;
    @BindView(R.id.tv_name)
    TextView                 tvNickName;
    @BindView(R.id.tv_user_number)
    TextView                 tvUserNumber;

    @BindView(R.id.text_friend_count)
    TextView                 tvFriendCount;
    @BindView(R.id.text_circle_count)
    TextView                 tvCircleCount;
    @BindView(R.id.text_topic_count)
    TextView                 tvTopicCount;
    @BindView(R.id.text_trend_count)
    TextView                 tvTrendCount;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void initLazyData() {
        initPtr();
        if (PrefUtils.getUser().getHeadPortraitUrl() != null)
            Glide.with(getActivity()).load(PrefUtils.getUser().getHeadPortraitUrl())
                    .into(imgUserHead);

        if (PrefUtils.getUser().getNickName() != null)
            tvNickName.setText(PrefUtils.getUser().getNickName());
        Log.e(TAG, "onViewCreated: QRCode =============== " + PrefUtils.getUser().getQrCodeUrl());
        tvUserNumber.setText("昵称:" + PrefUtils.getUser().getMobile());

        // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话就没有拍照功能
        File takePhotoDir = new File(Environment.getExternalStorageDirectory(), "zhongfen");
        mPhotoHelper = new BGAPhotoHelper(takePhotoDir);
    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (PrefUtils.getUser().getHeadPortraitUrl() != null)
            Glide.with(getActivity()).load(PrefUtils.getUser().getHeadPortraitUrl())
                    .into(imgUserHead);

        if (PrefUtils.getUser().getNickName() != null)
            tvNickName.setText(PrefUtils.getUser().getNickName());

        tvUserNumber.setText("众粉号:" + PrefUtils.getUser().getMobile());
    }

    private void initPtr() {

    }

    @Override
    public void ptrComplete() {
        super.ptrComplete();
    }

    @Override
    public boolean isMistakeShow() {
        return false;
    }

    @OnClick({ R.id.item_my_wallet, R.id.item_my_trend, R.id.item_my_comment, R.id.item_vip,
            R.id.item_settings, R.id.img_qr_code, R.id.btn_edit_user_info, R.id.iv_my_head,
            R.id.rl_circle_friend, R.id.rl_circle, R.id.rl_my_trend, R.id.rl_my_topic })
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.item_my_wallet:
                ActivityJumpUtils.ToOtherActivity(WalletActivity.class, getActivity(), null);
                break;
            case R.id.item_my_trend:

                break;
            case R.id.item_my_comment:

                break;
            case R.id.item_vip:

                break;
            case R.id.item_settings:
                ActivityJumpUtils.ToOtherActivity(SettingActivity.class, getActivity(), null);
                break;
            case R.id.img_qr_code:

                break;
            case R.id.btn_edit_user_info:
                ActivityJumpUtils.ToOtherActivity(UpdateUserInfoActivity.class, getActivity(),
                        null);
                break;
            case R.id.iv_my_head:
                updateUserHead();
                break;
            case R.id.rl_circle_friend:

                break;
            case R.id.rl_circle:

                break;
            case R.id.rl_my_trend:

                break;
            case R.id.rl_my_topic:

                break;
        }
    }

    @NeedsPermission({ Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA })
    public void updateUserHead() {
        startActivityForResult(mPhotoHelper.getChooseSystemGalleryIntent(),
                REQUEST_CODE_CHOOSE_PHOTO);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        BGAPhotoHelper.onSaveInstanceState(mPhotoHelper, outState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_CHOOSE_PHOTO) {
                try {
                    startActivityForResult(
                            mPhotoHelper.getCropIntent(
                                    mPhotoHelper.getFilePathFromUri(data.getData()), 200, 200),
                            REQUEST_CODE_CROP);
                } catch (Exception e) {
                    mPhotoHelper.deleteCropFile();
                    BGAPhotoPickerUtil.show(R.string.bga_pp_not_support_crop);
                    e.printStackTrace();
                }
            } else if (requestCode == REQUEST_CODE_CROP) {
                //                Glide.with(getActivity()).load(mPhotoHelper.getCropFilePath()).into(imgUserHead);
                //上传
                //                uploadMethod(mPhotoHelper.getCropFilePath());
                mPresenter.updateUserHeader(PrefUtils.getUser().getMobile(),
                        mPhotoHelper.getCropFilePath());
            }
        } else {
            if (requestCode == REQUEST_CODE_CROP) {
                mPhotoHelper.deleteCameraFile();
                mPhotoHelper.deleteCropFile();
            }
        }
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    @Override
    public void updateUserHeader() {
        Glide.with(getActivity()).load(mPhotoHelper.getCropFilePath()).into(imgUserHead);
        mPresenter.uploadImg(mPhotoHelper.getCropFilePath());
    }
}
