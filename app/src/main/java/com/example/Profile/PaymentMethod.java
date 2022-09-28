package com.example.Profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.example.thebeerguy.OrderComplete;
import com.example.thebeerguy.R;

public class PaymentMethod extends AppCompatActivity {

    ImageView payment_ImV_backBtn;
    RadioButton payment_COD_radioBtn, payment_card_radioBtn;
    Button payment_button_complete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        getSupportActionBar().hide();

        findId();

        payment_button_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentMethod.this, OrderComplete.class);
                startActivity(intent);
            }
        });

        payment_ImV_backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PaymentMethod.super.onBackPressed();
            }
        });


    }

    private void findId() {

        payment_button_complete = findViewById(R.id.payment_button_complete);
        payment_ImV_backBtn = findViewById(R.id.payment_ImV_backBtn);
        payment_COD_radioBtn = findViewById(R.id.payment_COD_radioBtn);
        payment_card_radioBtn = findViewById(R.id.payment_card_radioBtn);




    }

}