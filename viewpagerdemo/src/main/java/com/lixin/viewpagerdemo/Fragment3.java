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
 * @date 2018/12/20 18:26
 * @description Fragment3
 * @file VPFDemo
 */
public class Fragment3 extends BasePagerFragment {

    private View mRootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //LogUtils.e( "onAttach: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //LogUtils.e( "onCreate: ");
    }

    @Override
    public void fetchData() {
        LogUtils.e( "fetchData: ");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //LogUtils.e( "onCreateView: ");

        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_3, container, false);
        }
        return mRootView;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //LogUtils.e( "onActivityCreated: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        //LogUtils.e( "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        //LogUtils.e( "onResume: ");
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
        //LogUtils.e( "onDetach: ");
    }

    public static Fragment3 newInstance() {

        Bundle args = new Bundle();

        Fragment3 fragment = new Fragment3();
        fragment.setArguments(args);
        return fragment;
    }
    
}
