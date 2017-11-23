package com.hhl.customwidgetdemo;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by hhl on 2017/11/21.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            if (getIntent() != null && getIntent().hasExtra(Contants.INTENT_KEY_TITLE)){
                String title = getIntent().getStringExtra(Contants.INTENT_KEY_TITLE);
                actionBar.setTitle(title);
            }
        }
        setCustomContentView();
        initView();
        initData();
    }

    protected abstract void setCustomContentView();

    protected abstract void initView();

    protected abstract void initData();

    protected <T> T $(int resId) {
        T t = (T) findViewById(resId);
        return t;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
