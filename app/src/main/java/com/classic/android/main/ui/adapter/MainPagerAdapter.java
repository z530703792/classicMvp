package com.classic.android.main.ui.adapter;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.classic.android.main.ui.fragment.BusinessCircleFragment;
import com.classic.android.main.ui.fragment.FindFragment;
import com.classic.android.main.ui.fragment.MeFragment;
import com.classic.android.main.ui.fragment.RedPacketFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zcq
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mlist;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
        initFragment();
    }

    @Override
    public Fragment getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    private void initFragment() {
        mlist = new ArrayList<>();
        mlist.add(new RedPacketFragment());
        mlist.add(new BusinessCircleFragment());
        mlist.add(new FindFragment());
        mlist.add(new MeFragment());

    }

}
