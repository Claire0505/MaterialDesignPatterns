package com.admin.claire.materialdesignpatterns.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.admin.claire.materialdesignpatterns.fragment.FirstFragment;
import com.admin.claire.materialdesignpatterns.fragment.SecondFragment;
import com.admin.claire.materialdesignpatterns.fragment.ThirdFragment;

/**
 * Created by claire on 2017/10/19.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private static int TAB_COUNT = 3;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return FirstFragment.newInstance();
            case 1:
                return SecondFragment.newInstance();
            case 2:
                return ThirdFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return FirstFragment.TITLE;
            case 1:
                return SecondFragment.TITLE;
            case 2:
                return ThirdFragment.TITLE;
        }
        return super.getPageTitle(position);
    }
}
