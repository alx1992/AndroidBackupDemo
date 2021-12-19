package com.lixin.viewpagerdemo;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.lixin.viewpager.R;

import java.lang.ref.WeakReference;

/**
 * @author LiXin
 * @date 2018/12/20 18:20
 * @description Fragment2
 * @file VPFDemo
 */
public class Fragment2 extends BasePagerFragment {

    private static final String TAG = "MyFragment2";
    TextView mTextView;
    Button mButton;
    Button mButton1;
    Button mButton2;
    Button mButton3;
    private View mRootView;
    private MyHandler mMyHandler = new MyHandler(this);


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //LogUtils.e("onAttach: ");
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
            mRootView = inflater.inflate(R.layout.fragment_2, container, false);
        }
        mButton= mRootView.findViewById(R.id.button);
        mButton1= mRootView.findViewById(R.id.button1);
        mButton2= mRootView.findViewById(R.id.button2);
        mButton3= mRootView.findViewById(R.id.button3);
        mTextView = mRootView.findViewById(R.id.textView);
        mButton.setOnClickListener(v->{
                mMyHandler.sendEmptyMessageDelayed(1,3000);
        });
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
        //LogUtils.e("onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //LogUtils.e("onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //LogUtils.e( "onDetach: ");
    }

    public static Fragment2 newInstance() {
        Bundle args = new Bundle();
        Fragment2 fragment = new Fragment2();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 静态内部类
     */
    public static class MyHandler extends Handler {

        //持有弱引用HandlerActivity,GC回收时会被回收掉.
        private final WeakReference<Fragment2> fragment123;

        public MyHandler(Fragment2 fragment2) {
            fragment123 = new WeakReference<>(fragment2);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Fragment2 mFragment2 = fragment123.get();
            super.handleMessage(msg);
            if (mFragment2 != null) {
                //执行业务逻辑
                mFragment2.mButton.setText("aaaaaaaaa");
                mFragment2.mButton1.setText("bbbbbbbbbb");
                mFragment2.mButton2.setText("cccccccccc");
                mFragment2.mButton3.setText("dddddddddd");
                mFragment2.mTextView.setText("改了改");

            }
        }
    }


}
