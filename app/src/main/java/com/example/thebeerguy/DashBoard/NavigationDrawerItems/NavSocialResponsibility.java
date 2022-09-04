package com.example.thebeerguy.DashBoard.NavigationDrawerItems;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import com.example.thebeerguy.R;

public class NavSocialResponsibility extends AppCompatActivity {

    ImageButton socialResponsibility_ImV_backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_social_responsibility);

        getSupportActionBar().hide();

        socialResponsibility_ImV_backbtn = findViewById(R.id.socialResponsibility_ImV_backbtn);

        socialResponsibility_ImV_backbtn.setOnClickListener(v ->
                NavSocialResponsibility.super.onBackPressed());


    }
}