package com.classic.android.main.ui.activity;

import android.Manifest;

import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.classic.android.R;
import com.classic.android.base.BaseActivity;
import com.classic.android.main.ui.adapter.MainPagerAdapter;
import com.classic.android.widget.NoScrollViewPager;
import com.classic.base_library.msg.RxEvent;
import com.classic.base_library.utils.ToastUtil;
import com.classic.base_library.widget.AlertDialog;

import butterknife.BindView;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.bnve)
    BottomNavigationViewEx bnve;
    @BindView(R.id.vp)
    NoScrollViewPager      vp;

    private long           exitTime;

    private boolean        hasExteralPermission = false;

    @Override
    public void initEventAndData() {
        mRxManage.on(RxEvent.EVENT_MAIN_PAGE, arg -> {
            vp.setCurrentItem((int) arg, false);
            bnve.setSelectedItemId(bnve.getMenu().getItem((int) arg).getItemId());
        });
        noState();
        disableBack();
        bnve.enableItemShiftingMode(false);
        bnve.enableShiftingMode(false);
        bnve.enableAnimation(false);
        bnve.setItemIconTintList(null);
        vp.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        bnve.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    private int previousPosition = -1;

                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        int position = 0;
                        switch (item.getItemId()) {
                            case R.id.i_main_card_bag:
                                position = 0;
                                break;
                            case R.id.i_main_business_circle:
                                position = 1;
                                break;
                            case R.id.i_main_find:
                                position = 2;
                                break;
                            case R.id.i_main_me:
                                position = 3;
                                break;

                        }
                        if (previousPosition != position) {
                            vp.setCurrentItem(position, false);
                            previousPosition = position;
                        }

                        return true;
                    }
                });

        MainActivityPermissionsDispatcher.mainNeedPermissionsWithPermissionCheck(this);
    }

    @Override
    public boolean isMistakeShow() {
        return false;
    }

    @Override
    public void initInject() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                return super.onKeyDown(keyCode, event);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        bnve.setCurrentItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean isStatusBarLightMode() {
        return false;
    }


    @NeedsPermission({ Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE })
    void mainNeedPermissions() {
        hasExteralPermission = true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode,
                grantResults);
    }

    @OnShowRationale({ Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE })
    void mainShowPermissions(final PermissionRequest request) {
        request.proceed();
    }

    @OnPermissionDenied({ Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE })
    void mainDeniedPermissions() {
        initPermissionsDialog();
    }

    @OnNeverAskAgain({ Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE })
    void mainNeverPermissions() {
        ToastUtil.showToast("请到设置界面检查权限");
    }

    private void initPermissionsDialog() {
        new AlertDialog(this).builder().setTitle("权限提示：").setMsg("您拒绝了权限，会导致该应用内部发生错误,请尽快授权！")
                .setPositiveButton("重新授权",
                        arg -> MainActivityPermissionsDispatcher
                                .mainNeedPermissionsWithPermissionCheck(this))
                .setNegativeButton("退出程序", arg -> finish()).setCancelable(false).show();
    }

}
