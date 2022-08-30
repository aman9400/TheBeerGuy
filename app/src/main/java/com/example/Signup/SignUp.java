package com.example.Signup;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Apis.APIClient;
import com.example.Apis.APIInterface;
import com.example.Signup.responseSignup.ResponseSignup;
import com.example.common.Common;
import com.example.common.CommonMethod;
import com.example.login.Login;
import com.example.thebeerguy.R;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

     TextView signup_Tv_login;
     Button signup_button_signup;
    private ImageButton signup_btn_back;
    EditText sign_mobile, sign_email, sign_name,sign_password, sign_address,sign_apt,sign_buzzer, sign_extraInfo;
    APIInterface apiInterface;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();

        findIds();

        apiInterface = APIClient.getClient().create(APIInterface.class);

        signup_button_signup.setOnClickListener(v -> {
            signupApi();
        });

        signup_Tv_login.setOnClickListener(v -> {
            Intent signup_login = new Intent(SignUp.this, Login.class);
            startActivity(signup_login);
        });

        signup_btn_back.setOnClickListener(v -> {
            Intent signup_backIntent = new Intent(SignUp.this, Login.class);
            startActivity(signup_backIntent);
        });

    }

        private void signupApi() {

            boolean networkCheck = CommonMethod.isNetworkAvailable(this);
            if(networkCheck){
                if(sign_email.getText().toString().isEmpty()){
                    Toast.makeText(this, R.string.empty_email_message, Toast.LENGTH_SHORT).show();
                }else if(sign_password.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();

                }else if(!Patterns.EMAIL_ADDRESS.matcher(sign_email.getText().toString().trim()).matches()){
                    Toast.makeText(this, "Please enter correct email format", Toast.LENGTH_SHORT).show();

                }else if (sign_name.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();

                }else if (sign_address.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please enter correct Address", Toast.LENGTH_SHORT).show();

                }else if (sign_mobile.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please enter phone number", Toast.LENGTH_SHORT).show();

                }
//                else if (){
//                    Toast.makeText(this, "Please enter correct phone number", Toast.LENGTH_SHORT).show();
//
//                }


                else{
                    Map<String, String> map = new HashMap<>();
                    map.put(Common.Apikey_text, Common.Apikey_value);
                    map.put("email", sign_email.getText().toString().trim());
                    map.put("password", sign_password.getText().toString().trim());
                    map.put("name", sign_name.getText().toString().trim());
                    map.put("phone", sign_mobile.getText().toString().trim());
                    map.put("address", sign_address.getText().toString().trim());
                    map.put("addr2", sign_apt.getText().toString().trim());
                    map.put("buzzer", sign_buzzer.getText().toString().trim());
                    map.put("location_extra", sign_extraInfo.getText().toString().trim());
                    map.put("cell", "");
                    map.put("ext_location_id","332488");
                    map.put("intersection", "");


                    Call<ResponseSignup> call1 = apiInterface.signupUser(map);

                    call1.enqueue(new Callback<ResponseSignup>() {
                        @Override
                        public void onResponse(Call<ResponseSignup> call, Response<ResponseSignup> response) {
                            if(response.isSuccessful()){
                                ResponseSignup responseSignup = response.body();
                                Common.jwt = responseSignup.getJwt();
                                Log.e("response : " , String.valueOf(response));
                                Toast.makeText(SignUp.this, "Signup Successful", Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(SignUp.this, Login.class));
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseSignup> call, Throwable t) {
                            Toast.makeText(SignUp.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }else {
                Toast.makeText(this, "Please Check your internet.", Toast.LENGTH_SHORT).show();
            }


        }

        private void findIds() {

            signup_button_signup = findViewById(R.id.signup_button_signup);
            signup_Tv_login = findViewById(R.id.signup_Tv_login);
            signup_btn_back = findViewById(R.id.signup_btn_back);
            sign_mobile = findViewById(R.id.sign_mobile);
            sign_password = findViewById(R.id.sign_password);
            sign_address = findViewById(R.id.sign_address);
            sign_apt = findViewById(R.id.sign_apt);
            sign_buzzer = findViewById(R.id.sign_buzzer);
            sign_extraInfo = findViewById(R.id.sign_extrainfo);
            sign_email = findViewById(R.id.sign_email);
            sign_name = findViewById(R.id.sign_name);


        }

}