package com.lixin.viewpagerdemo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.lixin.viewpager.R;

/**
 * @author LiXin
 * @date 2018/12/20 17:49
 * @description Fragment1
 * @file VPFDemo
 */
public class Fragment1 extends BasePagerFragment {

    private static final String TAG = "MyFragment1";
    private View mRootView;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ////LogUtils.e("onAttach: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //LogUtils.e("onCreate: ");
    }

    @Override
    public void fetchData() {
        LogUtils.e("fetchData: ");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //LogUtils.e("onCreateView: ");

        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_1, container, false);
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //LogUtils.e("onActivityCreated: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        //LogUtils.e("onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        //LogUtils.e("onResume: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //LogUtils.e( "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //LogUtils.e( "onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //LogUtils.e("onDetach: ");
    }


    public static Fragment1 newInstance() {

        Bundle args = new Bundle();
        Fragment1 fragment = new Fragment1();
        fragment.setArguments(args);
        return fragment;
    }

}
