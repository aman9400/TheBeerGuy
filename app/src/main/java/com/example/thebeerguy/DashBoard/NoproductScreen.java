package com.example.thebeerguy.DashBoard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thebeerguy.R;

public class NoproductScreen extends AppCompatActivity {

    TextView tv_click_dash;
    ImageButton noProductScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noproduct_screen);

        getSupportActionBar().hide();

        tv_click_dash = findViewById(R.id.tv_click_dash);
        tv_click_dash.setOnClickListener(view->{
            startActivity(new Intent(this, DashBoard.class));
        });

        noProductScreen = findViewById(R.id.noProductScreen);
        noProductScreen.setOnClickListener(v->{
            startActivity(new Intent(this, DashBoard.class));
        });

    }
}