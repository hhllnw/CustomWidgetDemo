package com.hhl.customwidgetdemo;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.hhllnw.pullrecyclerviewlibrary.BaseViewHolder;
import com.github.hhllnw.pullrecyclerviewlibrary.PullRecycler;
import com.hhl.customwidgetdemo.activity.BallMoveActivity;
import com.hhl.customwidgetdemo.activity.FirstActivity;
import com.hhl.customwidgetdemo.activity.YZMActivity;

public class MainActivity extends BaseListActivity<MainActivity.ListItem> {

    private Class<?>[] ACTIVITY = {FirstActivity.class, YZMActivity.class, BallMoveActivity.class};

    @Override
    protected void init() {

    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup viewGroup, int viewType) {
        return new ViewHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.item_list, null));
    }

    @Override
    public void onPullRefresh(int i) {
        if (i == PullRecycler.ACTION_PULL_REFRESH) {
            mData.clear();
        }
        getData();
    }


    private class ViewHolder extends BaseViewHolder {
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_text);
        }

        @Override
        public void bind(int i) {
            String title = "";
            if (mData.size() != 0 && mData.get(i) != null && mData.get(i).getTitle() != null) {
                title = mData.get(i).getTitle();
            }
            textView.setText(title);
        }

        @Override
        public void OnItemClick(View view, int position) {
            super.OnItemClick(view, position);
            startActivity(new Intent(MainActivity.this, mData.get(position).getaClass())
                    .putExtra(Contants.INTENT_KEY_TITLE, mData.get(position).getTitle()));
        }
    }


    private void getData() {

        new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < ACTIVITY.length; i++) {
                    ListItem item = new ListItem();
                    item.setaClass(ACTIVITY[i]);
                    item.setTitle(ACTIVITY[i].getSimpleName());
                    mData.add(item);
                }

                myHander.sendEmptyMessage(1);
            }
        }.start();
    }

    private MyHander myHander = new MyHander();

    private class MyHander extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            baselistAdapter.notifyDataSetChanged();
            mPullRecycler.onComplete();
        }
    }


    public class ListItem {
        private Class<?> aClass;
        private String title;

        public Class<?> getaClass() {
            return aClass;
        }

        public void setaClass(Class<?> aClass) {
            this.aClass = aClass;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
