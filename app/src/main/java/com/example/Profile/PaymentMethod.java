package com.example.Profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

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

    }

    private void findId() {

        payment_button_complete = findViewById(R.id.payment_button_complete);
        payment_ImV_backBtn = findViewById(R.id.payment_ImV_backBtn);
        payment_COD_radioBtn = findViewById(R.id.payment_COD_radioBtn);
        payment_card_radioBtn = findViewById(R.id.payment_card_radioBtn);




    }

}