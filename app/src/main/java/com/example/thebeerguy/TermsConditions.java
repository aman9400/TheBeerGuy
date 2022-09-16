package com.example.thebeerguy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class TermsConditions extends AppCompatActivity {

    ImageView TC_back_Btn;
    WebView TC_webView;

    public final String postUrl ="https://www.thebeerguy.ca/corporate/terms_and_conditions/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_conditions);
        getSupportActionBar().hide();

        TC_webView = findViewById(R.id.TC_webView);

        TC_webView.loadUrl(postUrl);
        WebSettings webSettings = TC_webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        TC_webView.setWebViewClient(new WebViewClient());
        TC_webView.setWebChromeClient(new WebChromeClient());

        TC_back_Btn = findViewById(R.id.TC_back_Btn);

        TC_back_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TermsConditions.super.onBackPressed();
            }
        });
    }
}