package com.example.thebeerguy.DashBoard.NavigationDrawerItems;

import androidx.appcompat.app.AppCompatActivity;



import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;

import com.example.thebeerguy.R;

public class NavContactUs extends AppCompatActivity {

    ImageButton contact_ImV_baclBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_contact_us);

        getSupportActionBar().hide();

        contact_ImV_baclBtn = findViewById(R.id.contact_ImV_baclBtn);

        contact_ImV_baclBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NavContactUs.super.onBackPressed();
            }
        });


    }
}