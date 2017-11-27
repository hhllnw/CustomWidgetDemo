package com.hhl.customwidgetdemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.hhl.customwidgetdemo.R;

/**
 * Created by hhl on 2017/11/23.
 */

public class ClipView extends View {
    private Bitmap bitmap;
    private int n;

    public ClipView(Context context) {
        super(context);
        initView(context);
    }

    public ClipView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ClipView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.img_bz);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();

        int clipWidth = bitmapWidth / 7;
        Rect rect = new Rect(0, 0, clipWidth, bitmapHeight);
        canvas.save();
        canvas.clipRect(rect);
        canvas.drawBitmap(bitmap, -n * clipWidth, 0, null);
        canvas.restore();
        if (n < 7) {
            n++;
        } else {
            n = 0;
        }
    }
}
