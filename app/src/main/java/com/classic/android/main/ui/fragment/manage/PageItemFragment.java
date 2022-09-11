package com.classic.android.main.ui.fragment.manage;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.classic.android.R;
import com.classic.android.base.BaseLazyFragment;
import com.classic.base_library.model.bean.zcqBean;


/**
 * Created by zcq
 */

public class PageItemFragment extends BaseLazyFragment  {

//    @BindView(R.id.rv_view)
//    TRecyclerView rvView;

    @Override
    protected void initInject() {
    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void initLazyData() {
//        rvView.setFooterView(CommFooterOneVH.class).setListBeanType(ListResponse.class).setView(PageItemVH.class)
//                .setEmpty().fetch();
//        rvView.setOnItemClickListener(this);
    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page, null);
    }





    @Override
    public boolean isMistakeShow() {
        return false;
    }
}
