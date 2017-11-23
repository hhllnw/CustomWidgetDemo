package com.hhl.customwidgetdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.github.hhllnw.pullrecyclerviewlibrary.BaseViewHolder;
import com.github.hhllnw.pullrecyclerviewlibrary.BaselistAdapter;
import com.github.hhllnw.pullrecyclerviewlibrary.DividerDecoration;
import com.github.hhllnw.pullrecyclerviewlibrary.ILayoutManager;
import com.github.hhllnw.pullrecyclerviewlibrary.ILinearLayoutManager;
import com.github.hhllnw.pullrecyclerviewlibrary.PullRecycler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hhl on 2017/11/17.
 */

public abstract class BaseListActivity<T> extends AppCompatActivity implements PullRecycler.OnRecyclerRefreshListener{
    protected List<T> mData;
    protected PullRecycler mPullRecycler;
    protected BaselistAdapter baselistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomContentView(R.layout.lay_pullrecycler);
        mPullRecycler = $(R.id.mPullRecycler);
        mData = new ArrayList<>();
        baselistAdapter = new MyListAdapter();
        mPullRecycler.setPullRefreshUI(new int[]{android.R.color.holo_blue_light, android.R.color.holo_red_light,
                android.R.color.holo_orange_light, android.R.color.holo_green_light});
        mPullRecycler.setLayoutManager(getLayoutManager());
        mPullRecycler.setOnRecyclerRefreshListener(this);
        mPullRecycler.setAdapter(baselistAdapter);

        DividerDecoration divider = new DividerDecoration.Builder(this)
                .setColorResource(R.color.colorAccent).build();
        mPullRecycler.addItemDecoration(divider);

        init();

        mPullRecycler.setFirstRefresh();
    }

    protected void setCustomContentView(int resId){
        setContentView(resId);
    }

    protected <T>T $(int resId){
        return (T) findViewById(resId);
    }

    protected ILayoutManager getLayoutManager() {
        return new ILinearLayoutManager(this);
    }

    protected abstract void init();

    private class MyListAdapter extends BaselistAdapter{

        @Override
        protected BaseViewHolder onCreateNormalViewHolder(ViewGroup viewGroup, int viewType) {
            return getViewHolder(viewGroup,viewType);
        }

        @Override
        protected int getDataCount() {
            return mData == null ? 0 :mData.size();
        }
    }


    protected abstract BaseViewHolder getViewHolder(ViewGroup viewGroup, int viewType);
}
