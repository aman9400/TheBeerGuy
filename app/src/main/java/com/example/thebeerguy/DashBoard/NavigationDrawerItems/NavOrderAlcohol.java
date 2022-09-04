package com.example.thebeerguy.DashBoard.NavigationDrawerItems;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.thebeerguy.R;

public class NavOrderAlcohol extends AppCompatActivity {

    ImageButton order_alcohol_ImV_backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_order_alcohol);

        getSupportActionBar().hide();

        order_alcohol_ImV_backBtn = findViewById(R.id.order_alcohol_ImV_backBtn);

        order_alcohol_ImV_backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NavOrderAlcohol.super.onBackPressed();
            }
        });
    }
}