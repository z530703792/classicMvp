package com.classic.base_library.adapter;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * zcq
 * viewpager的fragmentAdpter
 */
public class BaseFragmentAdpter extends FragmentPagerAdapter {
    private String[] title ;
    private List<Fragment> fragments;

    public BaseFragmentAdpter(FragmentManager fm, List<Fragment> fragments, String[] title) {
        super(fm);
        this.title = title;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
       return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
