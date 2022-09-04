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

public class NavDeliveryRate extends AppCompatActivity {

    ImageButton nav_delivery_ImV_backbtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_delivery_rate);

        getSupportActionBar().hide();

        nav_delivery_ImV_backbtn = findViewById(R.id.nav_delivery_ImV_backbtn);

        nav_delivery_ImV_backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NavDeliveryRate.super.onBackPressed();
            }
        });


    }
}