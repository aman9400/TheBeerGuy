package com.example.thebeerguy.Intro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.example.thebeerguy.R;

public class NoCoverage extends AppCompatActivity {

    ImageView noCoverage_Backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_coverage);

        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.colorAccent));

        getSupportActionBar().hide();

        noCoverage_Backbtn = findViewById(R.id.noCoverage_Backbtn);

        noCoverage_Backbtn.setOnClickListener(v -> onBackPressed());
    }
}