package com.kyle.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/1/16
 */
public class MyWebViewActivity extends AppCompatActivity {

    private static String TAG = MyWebViewActivity.class.getSimpleName();

    WebView myWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_webview);

        myWebView = (WebView) findViewById(R.id.web_view);

        WebSettings settings = myWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setUseWideViewPort(true);
        settings.setBuiltInZoomControls(false);
        settings.setAllowFileAccess(true);

        myWebView.addJavascriptInterface(this, "WK");
        myWebView.addJavascriptInterface(this, "HealthBAT");

        myWebView.setWebChromeClient(new WebChromeClient());

        myWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d(TAG, "shouldOverrideUrlLoading()");
                view.loadUrl(url);
                return true;
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                Log.d(TAG, "shouldInterceptRequest:" + url);
                return super.shouldInterceptRequest(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                Log.d(TAG, "onPageFinished()");
            }
        });

        Log.d(TAG, "loadUrl()");
//        myWebView.loadUrl("file:///android_asset/html/demo3.html");
        myWebView.loadUrl("http://mobile.hmtest.kmhealthcloud.cn:8165?appkey=e38ad4f48133c76ad8e6165ccc427211&timestamp=2018-02-06 10:11:20&sign=9b3cb1a67729b49e8b570bf87b78aa19&phone=15982299692&src=2");
    }


    @JavascriptInterface
    public void pageFinish() {
        Log.d(TAG, "pageFinish()");
    }

    @JavascriptInterface
    public void removeAnimation() {
        Log.i(TAG, "removeAnimation()");
    }
}
