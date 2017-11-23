package com.hhl.customwidgetdemo.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.widget.ImageView;

import com.hhl.customwidgetdemo.BaseActivity;
import com.hhl.customwidgetdemo.R;

/**
 * Created by hhl on 2017/11/21.
 */

public class FirstActivity extends BaseActivity{
    private ImageView imageView;

    @Override
    protected void setCustomContentView() {
        setContentView(R.layout.activity_first);
    }

    @Override
    protected void initView() {

        imageView = $(R.id.imageView);

        Bitmap bitmapBuffer = Bitmap.createBitmap(1000,800, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapBuffer);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        //原大小绘制
        /*canvas.drawBitmap(bitmap,0,0,null);
        int with = bitmap.getWidth();
        int height = bitmap.getHeight();
        //对图片进行缩放
        Rect src = new Rect(0,0,with,height);
        Rect dst = new Rect(0,height,with*2,height +height*3);
        canvas.drawBitmap(bitmap,src,dst,null);*/

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.FILL);
        /*canvas.drawPoint(100,50,paint);

        float[] points = {10,20,30,40,50,60,70,80,90,100};
        paint.setColor(Color.BLUE);
        canvas.drawLines(points,paint);*/
        RectF rect = new RectF(100,100,400,400);
        /*canvas.drawRect(rect,paint);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        canvas.drawOval(rect,paint);*/
       /* canvas.drawCircle(250,250,150,paint);
        paint.setColor(Color.RED);
        canvas.drawArc(rect,30,60,false,paint);
        canvas.drawArc(rect,-30,-60,true,paint);*/
        Path path1 = new Path();
        path1.addRect(rect, Path.Direction.CCW);
        Path path2 = new Path();
        path2.addCircle(500,250,150, Path.Direction.CCW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            path1.op(path2, Path.Op.XOR);
        }
        //canvas.drawPath(path2,paint);
        paint.setColor(Color.RED);
        canvas.drawPath(path1,paint);

        imageView.setImageBitmap(bitmapBuffer);

    }

    @Override
    protected void initData() {

    }
}
