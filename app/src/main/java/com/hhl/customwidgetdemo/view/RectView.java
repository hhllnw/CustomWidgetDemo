package com.hhl.customwidgetdemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.hhl.customwidgetdemo.util.PaintAttributesTool;

/**
 * Created by hhl on 2017/11/28.
 */

public class RectView extends View {
    private int downX;
    private int downY;
    private Path path;
    private Bitmap bitmapBuffer;
    private Canvas canvasBitmap;
    private PaintAttributesTool paintTool;

    public RectView(Context context) {
        super(context);
        initView();
    }

    public RectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public RectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        paintTool = PaintAttributesTool.getTool();
        paintTool.setFill(false);
        paintTool.setColor(Color.RED);
        paintTool.setStrokeWidth(2);
        path = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (bitmapBuffer == null) {
            int width = getMeasuredWidth();
            int heigth = getMeasuredHeight();
            bitmapBuffer = Bitmap.createBitmap(width, heigth, Bitmap.Config.ARGB_8888);
            canvasBitmap = new Canvas(bitmapBuffer);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmapBuffer, 0, 0, null);
        canvas.drawPath(path, paintTool.getPaint());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = x;
                downY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                path.reset();
                if (downX < x && downY < y) {
                    path.addRect(new RectF(downX, downY, x, y), Path.Direction.CCW);
                } else if (downX > x && downY > y) {
                    path.addRect(new RectF(x, y, downX, downY), Path.Direction.CCW);
                } else if (downX > x && downY < y) {
                    path.addRect(new RectF(x, downY, downX, y), Path.Direction.CCW);
                } else if (downX < x && downY > y) {
                    path.addRect(new RectF(downX, y, x, downY), Path.Direction.CCW);
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                canvasBitmap.drawPath(path, paintTool.getPaint());
                invalidate();
                break;
        }
        return true;
    }
}
