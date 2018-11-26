package com.kyle.myapplication.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.kyle.myapplication.R;


/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/9/13
 */
public class RoundView extends View {

    private Paint mPaint;
    private int width;
    private int height;

    private Bitmap bitmap;
    private RoundDrawable drawable;

    public RoundView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        drawable = new RoundDrawable(this.bitmap);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.width = getWidth();
        this.height = getHeight();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawable.setBounds(new Rect(0, 0, width, height));
        drawable.draw(canvas);
//        canvas.drawColor(Color.RED);
//        RectF srcF = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
//        RectF dstF = new RectF(0, 0, width, height);
//
//        Matrix matrix = new Matrix();
////        matrix.setRectToRect(srcF, dstF, Matrix.ScaleToFit.END);
//        int minSize = (int) Math.min(srcF.width(), srcF.height());
//        float scale = dstF.width() / minSize;
//        matrix.setScale(scale, scale);
//        matrix.postTranslate(Math.round((width - srcF.width() * scale) * 0.5f),
//                Math.round((height - srcF.height() * scale) * 0.5));
//        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        shader.setLocalMatrix(matrix);
//        mPaint.setShader(shader);
//
//        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, mPaint);
//        canvas.drawBitmap(bitmap, matrix, mPaint);
    }

    private static class RoundDrawable extends Drawable {
        private static String TAG = RoundDrawable.class.getSimpleName();
        private Bitmap srcBitmap;
        private BitmapShader shader;

        private Paint mPaint;
        private Paint mBorderPaint;

        private int mBorderWidth = 10;

        private Rect mBoundRect;
        private Rect mBorderBoundRect;

        public RoundDrawable(Bitmap srcBitmap) {
//            this.srcBitmap = srcBitmap;
            int bW = srcBitmap.getWidth();
            int bH = srcBitmap.getHeight();
            int minSize = Math.min(bW, bH);

// 切割正方形
            this.srcBitmap = Bitmap.createBitmap(srcBitmap, (bW - minSize) / 2, (bH - minSize) / 2,
                    minSize, minSize);
//            this.srcBitmap = srcBitmap;
            shader = new BitmapShader(this.srcBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setShader(shader);
            paint.setFilterBitmap(true);
            paint.setDither(true);
            mPaint = paint;

            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(mBorderWidth);
            paint.setColor(Color.BLUE);
            mBorderPaint = paint;

            mBoundRect = new Rect();
            mBorderBoundRect = new Rect();


        }

        @Override
        public void draw(@NonNull Canvas canvas) {
            mPaint.setShader(shader);
            // 绘制图片
            canvas.drawCircle(mBoundRect.centerX(), mBoundRect.centerX(),
                    (mBoundRect.width() * 0.5f - mBorderWidth), mPaint);

            // 绘制边框
            canvas.drawCircle(mBorderBoundRect.centerX(),
                    mBorderBoundRect.centerY(), mBorderBoundRect.width() / 2, mBorderPaint);
        }

        @Override
        public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
            if (mPaint.getAlpha() != alpha) {
                mPaint.setAlpha(alpha);
                invalidateSelf();
            }
        }

        @Override
        public void setColorFilter(@Nullable ColorFilter colorFilter) {
            mPaint.setColorFilter(colorFilter);
            invalidateSelf();
        }


        @Override
        protected void onBoundsChange(Rect bounds) {
            super.onBoundsChange(bounds);
            Log.e(TAG, "onBoundsChange: ");
            mBoundRect.set(bounds);
            float bW = getIntrinsicWidth();
            float scale = bounds.width() / bW;
            Matrix matrix = new Matrix();
            matrix.setScale(scale, scale);
            Log.e(TAG, "onBoundsChange: scale>>" + scale );
            shader.setLocalMatrix(matrix);
            bounds.inset(mBorderWidth / 2, mBorderWidth / 2);
            mBorderBoundRect.set(bounds);
        }

        @Override
        public void setDither(boolean dither) {
            super.setDither(dither);

            mPaint.setDither(dither);
            invalidateSelf();
        }

        @Override
        public int getIntrinsicHeight() {
            return srcBitmap.getHeight();
        }

        @Override
        public int getIntrinsicWidth() {
            return srcBitmap.getWidth();
        }

        public Bitmap getSrcBitmap() {
            return srcBitmap;
        }

        @Override
        public int getOpacity() {
            return PixelFormat.TRANSLUCENT;
        }
    }
}
