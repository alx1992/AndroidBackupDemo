package com.lixin.viewpagerdemo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;


/**
 * @author LiXin
 * @date 2018/12/20 21:53
 * @description BasePagerFragment
 * @file VPFDemo
 */
public abstract class BasePagerFragment extends Fragment {
    /**
     * Fragment的View加载完毕的标记
     */
    protected boolean isViewInitiated;
    /**
     * Fragment对用户可见的标记
     */
    protected boolean isVisibleToUser;
    /**
     * 是否懒加载
     */
    protected boolean isDataInitiated;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData();
    }

    /**
     * 用这个方法来判断当前UI是否可见
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
    }

    public abstract void fetchData();

    public boolean prepareFetchData() {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || false)) {
            fetchData();
            isDataInitiated = true;
            return true;
        }
        return false;
    }



}
