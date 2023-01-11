package com.a.gridlayout;


import android.graphics.Color;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.a.gridlayout.view.PagerGridLayoutManager;
import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import java.util.Arrays;
import java.util.List;


/**
 * @author ShenBen
 * @date 2021/01/10 17:28
 * @email 714081644@qq.com
 */
public class TestAdapter extends BaseQuickAdapter<TestBean, BaseViewHolder> {
    public static final String TAG = "TestAdapter";

    public TestAdapter() {
        super(R.layout.item_video);
    }

    @Override
    protected void onItemViewHolderCreated(@NonNull BaseViewHolder viewHolder, int viewType) {
        Log.i(TAG, "onCreateViewHolder: ");
    }

    @Override
    protected void convert(@NonNull BaseViewHolder holder, TestBean item, @NonNull List<?> payloads) {
        int position = getItemPosition(item) - getHeaderLayoutCount();
        LogUtils.dTag(TAG, "onBindViewHolder-position: " + position + ",payloads: " + Arrays.toString(payloads.toArray()));
        holder.setBackgroundColor(R.id.surfaceView,Color.RED);
        holder.setText(R.id.textView,item.getName());
    }

    @Override
    protected void convert(@NonNull BaseViewHolder holder, TestBean testBean) {
        int position = getItemPosition(testBean)  - getHeaderLayoutCount();
        LogUtils.dTag(TAG, "onBindViewHolder-position: " + position);
        RecyclerView.LayoutManager layoutManager = getRecyclerView().getLayoutManager();
        if (layoutManager instanceof PagerGridLayoutManager) {
            int onePageSize = ((PagerGridLayoutManager) layoutManager).getOnePageSize();
            if (position % onePageSize == 0) {
                holder.setBackgroundColor(R.id.surfaceView, Color.RED);
                holder.setTextColor(R.id.textView,Color.WHITE);
            }
            else if (position % onePageSize == onePageSize - 1) {
                holder.setBackgroundColor(R.id.surfaceView, Color.GREEN);
                holder.setTextColor(R.id.textView,Color.WHITE);
            }
            else {
                holder.setBackgroundColor(R.id.surfaceView, Color.YELLOW);
                holder.setTextColor(R.id.textView,Color.BLACK);
            }
        }
        else {
            holder.setBackgroundColor(R.id.surfaceView, Color.BLUE);
            holder.setTextColor(R.id.textView,Color.WHITE);
        }
        holder.setText(R.id.textView, testBean.getName());
    }
}
