package com.classic.android.main.ui.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.classic.android.R;
import com.classic.android.base.BaseLazyFragment;

/**
 * 红包 Created by zcq
 */

public class RedPacketFragment extends BaseLazyFragment {


    @Override
    protected void initInject() {
    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void initLazyData() {

    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_red_packet, null);
    }

    @Override
    public boolean isMistakeShow() {
        return false;
    }

}
