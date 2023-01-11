package com.a.gridlayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import com.a.gridlayout.view.PagerGridLayoutManager;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        RecyclerView rv = findViewById(R.id.rv);
        rv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.set(10, 10, 10, 10);
            }
        });

        PagerGridLayoutManager layoutManager = new PagerGridLayoutManager(
                2,
                2,
                PagerGridLayoutManager.HORIZONTAL);

        layoutManager.setHandlingSlidingConflictsEnabled(true);
        rv.setLayoutManager(layoutManager);

        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                ToastUtils.showLong("点击了：" + position);
            }
        });


    }

    TestAdapter adapter = new TestAdapter();
    private void initData() {
        List<TestBean> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            list.add(new TestBean(i, String.valueOf(i)));
        }
        adapter.setList(list);
    }
}