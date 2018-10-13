package com.example.maxwe.studentportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class WebViewClass extends AppCompatActivity {
    private static WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        webView = (WebView)findViewById(R.id.webView);

        Intent intent = getIntent();
        String urlValue = intent.getStringExtra("urlWebView");

        webView.loadUrl(urlValue);
        webView.getSettings().getJavaScriptEnabled();
    }
}
