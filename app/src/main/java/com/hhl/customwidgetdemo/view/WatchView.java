package com.hhl.customwidgetdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hhl on 2017/11/27.
 */

public class WatchView extends View {
    private Paint paint;
    private Calendar calendar;

    public WatchView(Context context) {
        super(context);
        initView();
    }

    public WatchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public WatchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(1);
        calendar = Calendar.getInstance();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = this.getMeasuredWidth();
        int height = this.getMeasuredHeight();
        int length = Math.min(width, height);
        drawCircle(canvas, length);
        drawPointer(canvas, length);
    }

    /**
     * 画指针
     *
     * @param canvas
     * @param length
     */
    private void drawPointer(Canvas canvas, int length) {
        calendar.setTimeInMillis(System.currentTimeMillis());
        int hour = calendar.get(Calendar.HOUR) % 12;
        int minute = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);

        int degree = 360 / 12 * hour+(int) (30.0/60*minute);
        double radinas = Math.toRadians(degree);
        int radius = length >> 1;
        //画时针
        int startX = radius;
        int startY = radius;
        int stopX = (int) (startX + 0.5 * radius * Math.cos(radinas));
        int stopY = (int) (startY + 0.5 * radius * Math.sin(radinas));
        canvas.save();
        paint.setStrokeWidth(3);
        canvas.rotate(-90,radius,radius);
        canvas.drawLine(startX, startY, stopX, stopY, paint);
        canvas.restore();

        //画分针
        degree = 360 / 60 * minute;
        radinas = Math.toRadians(degree);
        stopX = startX + (int) (0.6 * radius * Math.cos(radinas));
        stopY = startY + (int) (0.6 * radius * Math.sin(radinas));
        canvas.save();
        paint.setStrokeWidth(2);
        canvas.rotate(-90,radius,radius);
        canvas.drawLine(startX, startY, stopX, stopY, paint);
        canvas.restore();

        //画秒针
        degree = 360 / 60 * seconds;
        radinas = Math.toRadians(degree);
        stopX = startX + (int) (0.8 * radius * Math.cos(radinas));
        stopY = startY + (int) (0.8 * radius * Math.sin(radinas));
        canvas.save();
        paint.setStrokeWidth(1);
        canvas.rotate(-90,radius,radius);
        canvas.drawLine(startX, startY, stopX, stopY, paint);
        canvas.restore();
    }

    /**
     * 画圆盘
     *
     * @param length
     */
    private void drawCircle(Canvas canvas, int length) {
        int radius = length >> 1;
        canvas.drawCircle(radius, radius, radius, paint);
        canvas.save();
        int lineLen;
        for (int i = 0; i <= 60; i++) {
            if (i % 5 == 0) {
                lineLen = 30;
                paint.setStrokeWidth(3);
            } else {
                lineLen = 10;
                paint.setStrokeWidth(1);
            }
            canvas.drawLine(length - lineLen, radius, length, radius, paint);
            canvas.rotate(6, radius, radius);
        }
        canvas.restore();
    }

    public void run(){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                postInvalidate();
            }
        },0,1000);
    }

}
