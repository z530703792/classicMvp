package com.classic.android.me.ui.activity;


import android.view.View;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;


import com.classic.android.R;
import com.classic.android.base.BaseActivity;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;


import butterknife.BindView;
import butterknife.OnClick;

/**
 * 钱包
 *
 * @author: zcq 2019-12-24 12:01
 */
public class WalletActivity extends BaseActivity {
    private final static String TITLE = "钱包";

    @BindView(R.id.tv_balance)
    TextView tvBalance;
   // @BindView(R.id.lv_billing_details)
   // TRecyclerView lvBillingDetails;
    @BindView(R.id.scroll)
    NestedScrollView scroll;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @Override
    public int getLayout() {
        return R.layout.activity_wallet;
    }

    @Override
    public void initEventAndData() {
        setTitleString(TITLE);
        initView();
        //        lvBillingDetails.setListBeanType(BillListBean.class).setView(BillVH.class).setEmpty()
//                .fetch();
    }

    @Override
    public void initInject() {

    }


    @OnClick({R.id.btn_recharge, R.id.btn_withdraw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_recharge:
                break;
            case R.id.btn_withdraw:
                break;
        }
    }

    private void initView() {
//        lvBillingDetails.noEnableRefresh(false);
//        lvBillingDetails.setEnableRefresh(false);
//        lvBillingDetails.nestedScrolling(false);

        scroll.setOnScrollChangeListener(
                (NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX,
                                                           oldScrollY) -> {

                    if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                       // lvBillingDetails.scrollLoad();
                    }
                });

        refreshLayout.setOnRefreshListener(refreshlayout -> {

           // lvBillingDetails.reFetch();
            ptrComplete();

        });

    }

    @Override
    public void ptrComplete() {
        super.ptrComplete();
        refreshLayout.finishRefresh();
    }

    @Override
    public void mistakeLoadData() {
        super.mistakeLoadData();
       // lvBillingDetails.reFetch();
    }


}
