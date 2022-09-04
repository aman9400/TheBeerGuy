package com.example.thebeerguy.DashBoard.NavigationDrawerItems;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.thebeerguy.R;

public class NavFranchiseSponsorship extends AppCompatActivity {

    ImageButton nav_FS_ImV_back_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_franchise_sponsorship);

        getSupportActionBar().hide();

        nav_FS_ImV_back_Btn = findViewById(R.id.nav_FS_ImV_back_Btn);

        nav_FS_ImV_back_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NavFranchiseSponsorship.super.onBackPressed();
            }
        });

    }
}