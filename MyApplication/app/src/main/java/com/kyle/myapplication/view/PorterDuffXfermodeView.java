package com.kyle.myapplication.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/9/7
 */
public class PorterDuffXfermodeView extends View {

    public PorterDuffXfermodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private Paint paint;
    private BitmapShader bg;

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(3);

        // 创建2像素的图片
        Bitmap bm = Bitmap.createBitmap(new int[]{0xFFFFFFFF, 0xFFCCCCCC,
                        0xFFCCCCCC, 0xFFFFFFFF}, 2, 2,
                Bitmap.Config.RGB_565);

        // 设置着色器，图片为平铺
        bg = new BitmapShader(bm, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        Matrix matrix = new Matrix();
        matrix.setScale(6, 6);
        bg.setLocalMatrix(matrix);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawColor(Color.GRAY);
        canvas.save();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        // 设置着色器
        paint.setShader(bg);
        // 绘制背景
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
        // 取消着色器
        paint.setShader(null);
        canvas.restore();

        canvas.translate(100, 400);
        // 保持一个layer
        int i = canvas.saveLayer(0, 0, getWidth(), getHeight(), null,
                Canvas.ALL_SAVE_FLAG);
        int r = 100;
        paint.setColor(Color.RED);
        // dst
        canvas.drawCircle(r, r, r, paint);

        paint.setColor(Color.YELLOW);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        // src
        canvas.drawRect(r, r, r * 2.5f, r * 2.5f, paint);

        paint.setXfermode(null);
        canvas.restoreToCount(i);
    }
}
