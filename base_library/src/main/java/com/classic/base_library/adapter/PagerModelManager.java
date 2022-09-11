package com.classic.base_library.adapter;



import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zcq
 */
public class PagerModelManager {
    public static final String DATA         = "data";
    private List<CharSequence> titleList    = new ArrayList();
    private List<Fragment> fragmentList = new ArrayList();

    public PagerModelManager() {
    }

    public Fragment getItem(int position) {
        return this.fragmentList.get(position);
    }

    public int getFragmentCount() {
        return this.fragmentList.size();
    }

    public boolean hasTitles() {
        return this.titleList.size() != 0;
    }

    public CharSequence getTitle(int position) {
        return this.titleList.get(position);
    }

    public PagerModelManager addFragment(Fragment fragment, CharSequence title) {
        this.titleList.add(title);
        this.addFragment(fragment);
        return this;
    }

    public PagerModelManager addFragment(Fragment fragment) {
        this.fragmentList.add(fragment);
        return this;
    }



    public PagerModelManager addCommonFragment(List<? extends Fragment> list) {
        this.fragmentList.addAll(list);
        return this;
    }

    public PagerModelManager addCommonFragment(List<? extends Fragment> list,
                                               List<String> titleList) {
        this.titleList.addAll(titleList);
        this.addCommonFragment(list);
        return this;
    }




}
