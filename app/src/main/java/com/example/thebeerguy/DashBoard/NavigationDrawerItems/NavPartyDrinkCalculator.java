package com.example.thebeerguy.DashBoard.NavigationDrawerItems;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.thebeerguy.R;

public class NavPartyDrinkCalculator extends AppCompatActivity {

    ImageButton party_calculator_ImV_backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_party_drink_calculator);
//        getSupportActionBar().hide();

        party_calculator_ImV_backBtn = findViewById(R.id.party_calculator_ImV_backBtn);

        party_calculator_ImV_backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavPartyDrinkCalculator.super.onBackPressed();
            }
        });
    }
}