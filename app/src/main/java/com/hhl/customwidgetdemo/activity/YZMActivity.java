package com.hhl.customwidgetdemo.activity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.widget.ImageView;

import com.hhl.customwidgetdemo.BaseActivity;
import com.hhl.customwidgetdemo.R;

/**
 * Created by hhl on 2017/11/23.
 * 验证码
 */

public class YZMActivity extends BaseActivity {
    private ImageView imageView;

    @Override
    protected void setCustomContentView() {
        setContentView(R.layout.activity_first);
    }

    @Override
    protected void initView() {
        imageView = $(R.id.imageView);

        Bitmap bitmapBuffer = Bitmap.createBitmap(1000,1000, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapBuffer);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(2);
        for (int i = 0;i<100;i++){
            float startX = (float) Math.random()*1000;
            float startY = (float) Math.random()*1000;
            float stopX = (float) Math.random()*1000;
            float stopY = (float) Math.random()*1000;
            canvas.drawLine(startX,startY,stopX,stopY,paint);
        }
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(120);
        Path path = new Path();
        path.moveTo(250,500);
        path.quadTo(250,400,1000,500);
        StringBuilder text = new StringBuilder();
        for (int i = 0;i<4;i++){
            int x = (int) (Math.random()*10);
            text.append(x+"  ");
        }
        canvas.drawTextOnPath(text.toString(),path,30,30,paint);
        imageView.setImageBitmap(bitmapBuffer);

    }

    @Override
    protected void initData() {

    }
}
