package com.example.thebeerguy.DashBoard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thebeerguy.R;

public class NoproductScreen extends AppCompatActivity {



    ImageButton noProductScreen;

    CardView noProduct_cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noproduct_screen);

        getSupportActionBar().hide();


        noProduct_cardView = findViewById(R.id.noProduct_cardView);

        noProduct_cardView.setOnClickListener(v->{
            startActivity(new Intent(this, DashBoard.class));
        });

    }
}