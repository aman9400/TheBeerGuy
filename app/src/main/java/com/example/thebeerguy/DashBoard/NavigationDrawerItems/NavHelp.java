package com.example.thebeerguy.DashBoard.NavigationDrawerItems;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.example.thebeerguy.R;

public class NavHelp extends AppCompatActivity {

    ImageButton help_ImV_backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_help);

        getSupportActionBar().hide();

        help_ImV_backBtn = findViewById(R.id.help_ImV_backBtn);

        help_ImV_backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHelp.super.onBackPressed();
            }
        });
    }
}