package com.hhl.customwidgetdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hhl on 2017/11/23.
 * 移动的小球
 */

public class BallMoveView extends View {
    private Paint mPaint;
    /**
     * 小球的水平位置
     */
    private int x;

    /**
     * 小球的竖直位置
     *
     * @param context
     */
    private final int y = 100;

    /**
     * 移动方向
     */
    private boolean dircetion;
    /**
     * 小球半径
     */
    private int radius = 20;

    private final int move_x = 50;

    public BallMoveView(Context context) {
        super(context);
        initView();
    }

    public BallMoveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public BallMoveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        x = radius;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(x, y, radius, mPaint);
        int width = getMeasuredWidth();//组件宽度
        if (x <= radius) {
            dircetion = true;
        } else if (x >= width - radius) {
            dircetion = false;
        }

        if (dircetion) {
            x += move_x;
        } else {
            x -= move_x;
        }
    }
}
