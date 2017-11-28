package com.hhl.customwidgetdemo.util;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by hhl on 2017/11/28.
 * Paint工具类
 */

public class PaintAttributesTool {
    private static PaintAttributesTool tool;
    private static Paint paint;
    private int color;
    private int strokeWidth;
    private boolean isFill;


    public PaintAttributesTool() {
        reset();
    }

    public static PaintAttributesTool getTool() {
        if (tool == null) {
            tool = new PaintAttributesTool();
        }
        return tool;
    }

    private void reset() {
        this.color = Color.BLACK;
        this.strokeWidth = 1;
        this.isFill = false;
    }

    public Paint getPaint() {
        if (paint == null) {
            paint = new Paint();
        }
        paint.setAntiAlias(true);
        paint.setColor(getColor());
        paint.setStrokeWidth(getStrokeWidth());
        paint.setStyle(isFill() ? Paint.Style.FILL : Paint.Style.STROKE);
        return paint;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public boolean isFill() {
        return isFill;
    }

    public void setFill(boolean fill) {
        isFill = fill;
    }
}
