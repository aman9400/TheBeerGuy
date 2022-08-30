package com.example.Profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;

import com.example.thebeerguy.R;

public class PaymentMethod extends AppCompatActivity {

    ConstraintLayout payment_constraintLayout_card, payment_constraintLayout_netBanking, payment_constraintLayout_wallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        findId();

    }

    private void findId() {

        payment_constraintLayout_card = findViewById(R.id.payment_constraintLayout_card);
        payment_constraintLayout_netBanking = findViewById(R.id.payment_constraintLayout_netBanking);
        payment_constraintLayout_wallet = findViewById(R.id.payment_constraintLayout_wallet);


    }

}