package com.example.thebeerguy.DashBoard.NavigationDrawerItems;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.thebeerguy.R;

public class NavTestimonials extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_testimonials);

        getSupportActionBar().hide();
    }
}