package com.hhl.customwidgetdemo.activity;

import com.hhl.customwidgetdemo.BaseActivity;
import com.hhl.customwidgetdemo.R;
import com.hhl.customwidgetdemo.view.BallMoveView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hhl on 2017/11/23.
 */

public class BallMoveActivity extends BaseActivity {
    private BallMoveView ballMoveView;

    @Override
    protected void setCustomContentView() {
        setContentView(R.layout.activity_ball_move);
    }

    @Override
    protected void initView() {
        ballMoveView = $(R.id.mBallMoveView);
    }

    @Override
    protected void initData() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                ballMoveView.postInvalidate();
            }
        }, 0, 100);
    }
}
