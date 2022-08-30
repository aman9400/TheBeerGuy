package com.example.thebeerguy.DashBoard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.thebeerguy.R;

public class ReviewCart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_cart);

        getSupportActionBar().hide();
    }
}