package com.lixin.viewpagerdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.lixin.viewpager.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class VeiwPagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    ViewPager vp;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    RadioGroup radioGroup;

    private List<Fragment> mFragmentList;
    private FragmentManager fm;//fragment管理器 对象
    private TestHandler mHandler = new TestHandler(this);
    private Handler mHandler1 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(VeiwPagerActivity.this, "test...", Toast.LENGTH_SHORT).show();
            rb3.setText("gggggg");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        initView();
        initData();
    }

    private void initData() {

        //获得fragment管理器 对象
        fm = getSupportFragmentManager();

        mFragmentList = new ArrayList();
        newFragment();

//        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(fm, mFragmentList);
        MyFragmentStatePagerAdapter adapter = new MyFragmentStatePagerAdapter(fm, mFragmentList);
        vp.setAdapter(adapter);
        //设置显示为第一个
        vp.setCurrentItem(1);
//        vp.setOffscreenPageLimit(1);



    }

    private void newFragment() {

        mFragmentList.add(Fragment1.newInstance());
        mFragmentList.add(Fragment2.newInstance());
        mFragmentList.add(Fragment3.newInstance());

    }

    private void initView() {
        vp = findViewById(R.id.vp);
        rb1 = findViewById(R.id.rb1);
        rb1.setOnClickListener(this::onClick);
        rb2 = findViewById(R.id.rb2);
        rb2.setOnClickListener(this::onClick);
        rb3 = findViewById(R.id.rb3);
        rb3.setOnClickListener(this::onClick);
        radioGroup = findViewById(R.id.radioGroup);
        vp.addOnPageChangeListener(this);

    }

    /**
     * vp的切换事件
     *
     * @param i
     * @param v
     * @param i1
     */
    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    /**
     * vp的切换事件
     *
     * @param i
     */
    @Override
    public void onPageSelected(int i) {

    }

    /**
     * vp的切换事件
     *
     * @param i
     */
    @Override
    public void onPageScrollStateChanged(int i) {

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb1:
                vp.setCurrentItem(0);
                break;
            case R.id.rb2:
                mHandler.sendEmptyMessageDelayed(1,3000);
                mHandler1.sendEmptyMessageDelayed(1,3000);
                vp.setCurrentItem(1);
                break;
            case R.id.rb3:
                vp.setCurrentItem(2);
                break;
        }
    }

    public static class TestHandler extends Handler {

        //持有弱引用HandlerActivity,GC回收时会被回收掉.
        private final WeakReference<VeiwPagerActivity> activitys;

        public TestHandler(VeiwPagerActivity activity) {
            activitys = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            VeiwPagerActivity mActivity = activitys.get();
            super.handleMessage(msg);
            if (mActivity != null) {
                //执行业务逻辑
                Toast.makeText(mActivity, "测试...", Toast.LENGTH_LONG).show();            }
            }

    }


}
