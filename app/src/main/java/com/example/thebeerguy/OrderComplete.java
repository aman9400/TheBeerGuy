package com.example.thebeerguy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.thebeerguy.DashBoard.DashBoard;

public class OrderComplete extends AppCompatActivity {

    private Button oderComplete_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_complete);
        getSupportActionBar().hide();

        oderComplete_button = findViewById(R.id.oderComplete_button);

        oderComplete_button.setOnClickListener(v -> {
            Intent intent = new Intent(OrderComplete.this, DashBoard.class);
            startActivity(intent);
        });
    }
}