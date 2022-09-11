package com.classic.android.utils;

import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.classic.android.R;

import java.util.List;

/**
 * @author: zcq  2019-08-14 21:09
 */
public class ShowFragments {
    public static void showFragment(Class<? extends Fragment> fragmentClass, String tag,
                                    Bundle argument, FragmentActivity activity) {
        try {
            FragmentManager fm = activity.getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            List<Fragment> fragments = fm.getFragments();
            if (fragments != null) {
                for (Fragment item : fragments) {
                    ft.hide(item);
                }
            }
            Fragment pf = fm.findFragmentByTag(tag);
            if (pf != null) {
                ft.show(pf);
            } else {
                Fragment fragment = fragmentClass.newInstance();
                if (argument != null) {
                    fragment.setArguments(argument);
                }
                ft.add(R.id.fragment_container, fragment, tag);
            }
            ft.commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void showFragment(Class<? extends Fragment> fragmentClass, String tag,
                                         Bundle argument, Fragment f) {
        try {
            FragmentManager fm = f.getChildFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            List<Fragment> fragments = fm.getFragments();
            if (fragments != null) {
                for (Fragment item : fragments) {
                    if (item!=null) {
                        ft.hide(item);
                    }
                }
            }
            Fragment pf = fm.findFragmentByTag(tag);
            if (pf != null) {
                ft.show(pf);
            } else {
                Fragment fragment = fragmentClass.newInstance();
                if (argument != null) {
                    fragment.setArguments(argument);
                }
                ft.replace(R.id.fragment_container, fragment, tag);
            }
            ft.commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
