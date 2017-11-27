package com.hhl.customwidgetdemo.activity;

import com.hhl.customwidgetdemo.BaseActivity;
import com.hhl.customwidgetdemo.R;
import com.hhl.customwidgetdemo.view.BallMoveView;
import com.hhl.customwidgetdemo.view.ClipView;
import com.hhl.customwidgetdemo.view.WatchView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hhl on 2017/11/23.
 */

public class BallMoveActivity extends BaseActivity {
    private BallMoveView ballMoveView;
    private ClipView mClipView;
    private WatchView watchView;

    @Override
    protected void setCustomContentView() {
        setContentView(R.layout.activity_ball_move);
    }

    @Override
    protected void initView() {
        ballMoveView = $(R.id.mBallMoveView);
        mClipView = $(R.id.mClipView);
        watchView = $(R.id.mWatchView);
        watchView.run();
    }

    @Override
    protected void initData() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                ballMoveView.postInvalidate();
                mClipView.postInvalidate();
            }
        }, 200, 100);
    }
}
