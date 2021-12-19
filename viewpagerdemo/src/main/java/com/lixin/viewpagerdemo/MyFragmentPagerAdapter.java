package com.lixin.viewpagerdemo;


import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;


/**
 * @author LiXin
 * @date 2018/12/20 18:50
 * @description MyFragmentPagerAdapter
 * @file VPFDemo
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = "MyFragmentPagerAdapter";

    List<Fragment> fragmentlist ;
    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentlist ) {
        super(fm);
        this.fragmentlist = fragmentlist;
    }
    @Override
    public int getCount() {
        return fragmentlist.size();
    }
    @Override
    public Fragment getItem(int position) {
        return fragmentlist.get(position);
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }
}
