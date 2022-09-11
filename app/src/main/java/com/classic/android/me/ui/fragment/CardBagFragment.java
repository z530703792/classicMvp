package com.classic.android.me.ui.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.classic.android.R;
import com.classic.android.base.BaseLazyFragment;
import com.classic.android.me.presenter.CardBagPresenter;
import com.classic.android.me.presenter.contract.CardBagContract;
import com.classic.base_library.adapter.recycler.CommonAdapter;
import com.classic.base_library.adapter.recycler.MultiItemTypeAdapter;
import com.classic.base_library.adapter.recycler.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 卡包
 * 
 * @author: zcq 2019-12-24 12:02
 */
public class CardBagFragment extends BaseLazyFragment<CardBagPresenter>
        implements CardBagContract.View {

    @BindView(R.id.rv_view)
    RecyclerView rvView;
    CommonAdapter<String> commonAdapter;

    private List<String>  list = new ArrayList<>();

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_card_bag, null);
    }

    @Override
    protected void initInject() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void initLazyData() {
        rvView.setLayoutManager(new LinearLayoutManager(getActivity()));
        commonAdapter = new CommonAdapter<String>(getActivity(), list, R.layout.item_card_bag) {

            @Override
            protected void convert(ViewHolder holder, String o, int position) {

            }
        };
        rvView.setAdapter(commonAdapter);
        commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder,
                                           int position) {
                return false;
            }
        });
    }

}
