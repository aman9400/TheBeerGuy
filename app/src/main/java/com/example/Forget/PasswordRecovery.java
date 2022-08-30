package com.example.Forget;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.login.Login;
import com.example.thebeerguy.R;

public class PasswordRecovery extends AppCompatActivity {

    Button passwordRecovery_btn_okay;
    ImageButton passwordRecovery_btn_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);
        getSupportActionBar().hide();

        passwordRecovery_btn_back = findViewById(R.id.passwordRecovery_btn_back);

        passwordRecovery_btn_okay = findViewById(R.id.passwordRecovery_btn_okay);



        passwordRecovery_btn_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent passwordRecovery_okayIntent = new Intent(PasswordRecovery.this, ResetPassword.class);
                startActivity(passwordRecovery_okayIntent);
            }
        });

        passwordRecovery_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent passwordRecovery_backIntent = new Intent(PasswordRecovery.this, Login.class);
                startActivity(passwordRecovery_backIntent);
            }
        });




    }
}