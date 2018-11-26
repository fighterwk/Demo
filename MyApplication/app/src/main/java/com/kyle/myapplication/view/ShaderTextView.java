package com.kyle.myapplication.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextPaint;
import android.util.AttributeSet;

import com.kyle.myapplication.R;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/11/9
 */
public class ShaderTextView extends AppCompatTextView {

    BitmapShader bitmapShader;

    public ShaderTextView(Context context) {
        this(context, null);
    }

    public ShaderTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        TextPaint paint = getPaint();
        LinearGradient shader = new LinearGradient(0, getHeight() / 2, getWidth() / 2,
                getHeight(), Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
        ComposeShader composeShader = new ComposeShader(shader, bitmapShader, PorterDuff.Mode.SRC_OVER);
        paint.setShader(composeShader);
        super.onDraw(canvas);
    }
}
