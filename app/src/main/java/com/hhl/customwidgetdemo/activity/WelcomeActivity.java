package com.hhl.customwidgetdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hhl.customwidgetdemo.MainActivity;
import com.hhl.customwidgetdemo.R;

import cn.magicwindow.MLinkAPIFactory;
import cn.magicwindow.MWConfiguration;
import cn.magicwindow.MagicWindowSDK;
import cn.magicwindow.Session;

/**
 * Created by hhl on 2017/12/4.
 */

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MWConfiguration config = new MWConfiguration(this);
        config.setLogEnable(true);//打开魔窗Log信息
        MagicWindowSDK.initSDK(config);

        MLinkAPIFactory.createAPI(this).registerWithAnnotation(this);

        if (getIntent().getData() != null) {
            MLinkAPIFactory.createAPI(this).router(this, getIntent().getData());
            //跳转后结束当前activity
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            startActivity(new Intent(this, MainActivity.class));
            finish();

        }
    }

    @Override
    protected void onPause() {
        Session.onPause(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        Session.onResume(this);
        super.onResume();
    }
}
