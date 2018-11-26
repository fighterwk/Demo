package com.kyle.myapplication.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/1/16
 */
public class MyWebView extends WebView {

    public MyWebView(Context context) {
        super(context);
        init();
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    @SuppressLint("JavascriptInterface")
    private void init() {


        addJavascriptInterface(new Object() {

            @JavascriptInterface
            public void resetHeight(int height) {
                Log.d("webView", "height:" + height);
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                layoutParams.height = height;
                setLayoutParams(layoutParams);
            }

        }, "my");

        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);

        settings.setUseWideViewPort(true);
        settings.setSupportMultipleWindows(true);
        settings.setAllowFileAccess(true);
        settings.setBuiltInZoomControls(false);

        setWebChromeClient(new WebChromeClient() {


        });

        setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // 加载完成，获取html的高度

                view.loadUrl("javascript:window.my.restHeight(document.body.clientHeight);");

            }
        });
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }


}
