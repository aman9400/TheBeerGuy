package com.example.Forget;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.login.Login;
import com.example.thebeerguy.R;

public class ResetPassword extends AppCompatActivity {
    Button reset_btn_create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        getSupportActionBar().hide();

        reset_btn_create = findViewById(R.id.reset_btn_create);

        reset_btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reset_createIntent = new Intent(ResetPassword.this, Login.class);
                startActivity(reset_createIntent);
            }
        });


    }
}