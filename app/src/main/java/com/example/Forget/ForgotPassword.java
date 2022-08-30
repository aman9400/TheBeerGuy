package com.example.Forget;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.Apis.APIClient;
import com.example.Apis.APIInterface;
import com.example.Forget.forgetPasswordResponse.Responseforget;
import com.example.common.Common;
import com.example.common.CommonMethod;
import com.example.login.Login;
import com.example.login.responseLogin.LoginResponse;
import com.example.thebeerguy.DashBoard.DashBoard;
import com.example.thebeerguy.R;

import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassword extends AppCompatActivity {

    private Button forgetPassword_btn_send;
    private  ImageButton forgetPassword_btn_back;
    private EditText forgetPassword_email;
    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getSupportActionBar().hide();

        findId();

        apiInterface = APIClient.getClient().create(APIInterface.class);


        forgetPassword_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgetPass_backIntent = new Intent(ForgotPassword.this, Login.class);
                startActivity(forgetPass_backIntent);
            }
        });

        forgetPassword_btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              forgetPasswordAPI();
            }
        });

    }

    private void forgetPasswordAPI(){

        boolean networkCheck = CommonMethod.isNetworkAvailable(this);

        if(networkCheck){
            if(forgetPassword_email.getText().toString().isEmpty()){
                Toast.makeText(this, R.string.empty_email_message, Toast.LENGTH_SHORT).show();

            }else{
                Map<String, String> map = new HashMap<>();
                map.put(Common.Apikey_text, Common.Apikey_value);
                map.put("email", forgetPassword_email.getText().toString().trim());
                map.put("skin_id", "");
                Call<Responseforget> call1 = apiInterface.forgetUser(map);

                call1.enqueue(new Callback<Responseforget>() {
                    @Override
                    public void onResponse(Call<Responseforget> call, Response<Responseforget> response) {
                        if(response.isSuccessful()){
                            Responseforget forgetPassResponse = response.body();
                            if(forgetPassResponse.getResult().equalsIgnoreCase("error")){
                                Toast.makeText(ForgotPassword.this, forgetPassResponse.getErrors().get(0), Toast.LENGTH_SHORT).show();

                            }else{
                                Toast.makeText(ForgotPassword.this, forgetPassResponse.getResult(), Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(ForgotPassword.this, PasswordRecovery.class));

                            }

                        }

                    }

                    @Override
                    public void onFailure(Call<Responseforget> call, Throwable t) {
                        Toast.makeText(ForgotPassword.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }else {
            Toast.makeText(this, "Please Check your internet.", Toast.LENGTH_SHORT).show();
        }



    }


    private void findId(){
        forgetPassword_btn_send = findViewById(R.id.forgetPassword_btn_send);
        forgetPassword_btn_back = findViewById(R.id.forgetPassword_btn_back);
        forgetPassword_email = findViewById(R.id.forgetPassword_email);

    }
}