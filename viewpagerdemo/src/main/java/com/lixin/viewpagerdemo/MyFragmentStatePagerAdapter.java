package com.lixin.viewpagerdemo;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @author LiXin
 * @date 2018/12/20 21:20
 * @description MyFragmentStatePagerAdapter
 * @file VPFDemo
 */
public class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter {


    List<Fragment> fragmentlist ;


    public MyFragmentStatePagerAdapter(FragmentManager fm, List<Fragment> fragmentlist) {
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


 /*   @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    }*/
}
