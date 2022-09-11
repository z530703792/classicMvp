package com.classic.android.main.ui.activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.ViewPager;

import com.classic.android.R;
import com.classic.android.main.ui.adapter.GuideAdapter;
import com.classic.base_library.base.SimpleActivity;
import com.classic.base_library.utils.PrefUtils;
import com.classic.base_library.utils.StatusBarUtil;
import com.classic.base_library.utils.SystemUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 引导页
 */

public class GuideActivity extends SimpleActivity {

    @BindView(R.id.vp_guide)
    ViewPager vpGuide;

    @Override
    public int getLayout() {
        return R.layout.activity_guide;
    }

    @Override
    public void initEventAndData() {
        disableBack();
        initTopView();

        View view = LayoutInflater.from(this).inflate(R.layout.layout_guide, null);
        Button button = view.findViewById(R.id.btn_guide_enter);
        button.setOnClickListener(view1 -> {
            PrefUtils.putBoolean("newApp", false);

            Intent intent = new Intent(this,
                    SystemUtil.isLogin() ? MainActivity.class : LoginActivity.class);
            startActivity(intent);
            finish();
        });

        final List<View> views = new ArrayList<>();
        views.add(setImg(R.mipmap.guide));
        views.add(setImg(R.mipmap.guide));
        views.add(view);
        vpGuide.setAdapter(new GuideAdapter(views));
    }

    private View setImg(int r) {
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        LinearLayout.LayoutParams imgvwDimens = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(imgvwDimens);
        imageView.setImageResource(r);
        return imageView;
    }

    private void initTopView() {
        StatusBarUtil.StatusBarLightMode(this);
    }

}
