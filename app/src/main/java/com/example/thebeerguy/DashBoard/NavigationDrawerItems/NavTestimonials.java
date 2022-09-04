package com.example.thebeerguy.DashBoard.NavigationDrawerItems;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.thebeerguy.R;

public class NavTestimonials extends AppCompatActivity {

    ImageButton testimonials_ImV_backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_testimonials);

        getSupportActionBar().hide();

        testimonials_ImV_backBtn = findViewById(R.id.testimonials_ImV_backBtn);

        testimonials_ImV_backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NavTestimonials.super.onBackPressed();
            }
        });
    }
}