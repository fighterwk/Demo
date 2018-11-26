package com.kyle.myapplication;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.administrator.myapplication", appContext.getPackageName());
    }


    @Test
    public void test_static_layout() throws Exception {
        String content = "这是一篇关于StaticLayout的测试文章，在這里将使用StaticLayout技术完成，TextView字符缺省功能，并使用'查看更多','收起'功能";
        TextPaint paint = new TextPaint();
        paint.setTextSize(12);

        int layoutWidth = 200;
        StaticLayout layout = new StaticLayout(content, paint, layoutWidth, Layout.Alignment.ALIGN_NORMAL,
                1.0f, 0f, true);

        // 布局宽度
        int width = layout.getWidth();
        // 布局高度
        int height = layout.getHeight();
        // 文本行数
        int line = layout.getLineCount();

        int lineEnd = layout.getLineEnd(line - 2);


        Log.d("Test", String.format("width:%d, height:%d, line:%d, lineEnd:%d", width, height,
                line, lineEnd));

        Log.d("Test", content.substring(lineEnd, lineEnd + 3));
    }
}
