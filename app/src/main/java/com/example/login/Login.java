package com.example.login;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Apis.APIClient;
import com.example.Apis.APIInterface;
import com.example.Forget.ForgotPassword;
import com.example.Signup.SignUp;
import com.example.common.Common;
import com.example.common.CommonMethod;
import com.example.login.responseLogin.LoginResponse;
import com.example.thebeerguy.DashBoard.DashBoard;
import com.example.thebeerguy.Intro.LandingScreen;
import com.example.thebeerguy.R;


import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity   {

    private Button login_btn_login;
    private TextView login_Tv_signup;
    private TextView login_Tv_forgetPassword;
    private EditText login_email, login_password;
    private ImageButton logon_imgBtn_back;
    private CardView login_cardview_facebook_login, login_cardview_gmail_login;
    APIInterface apiInterface;

    private Boolean Islogin = false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        findIds();

        apiInterface = APIClient.getClient().create(APIInterface.class); //        APIClient.


        logon_imgBtn_back.setOnClickListener(v->{
            Intent intentBack = new Intent(Login.this, LandingScreen.class);
            startActivity(intentBack);
        });

        login_btn_login.setOnClickListener(v->{
            loginApi();
//            Intent intent= new Intent(this,DashBoard.class);
//            startActivity(intent);
        });


        login_Tv_signup.setOnClickListener(v -> {

            Intent login_signupIntent = new Intent(Login.this, SignUp.class);
            startActivity(login_signupIntent);
        });

        login_Tv_forgetPassword.setOnClickListener(v -> {
            Intent login_forgetPassIntent = new Intent(Login.this, ForgotPassword.class);
            startActivity(login_forgetPassIntent);
        });

        login_cardview_gmail_login.setOnClickListener(v->{
//            googleCardSignin();
        });
    }
    private void loginApi() {

        boolean networkCheck = CommonMethod.isNetworkAvailable(this);
       if(networkCheck){
           if(login_email.getText().toString().isEmpty()){
               Toast.makeText(this, R.string.empty_email_message, Toast.LENGTH_SHORT).show();
           }else if(login_password.getText().toString().isEmpty()){
               Toast.makeText(this, "Please enter your password address", Toast.LENGTH_SHORT).show();

           }else if(!Patterns.EMAIL_ADDRESS.matcher(login_email.getText().toString().trim()).matches()){
               Toast.makeText(this, "Please enter correct email format", Toast.LENGTH_SHORT).show();

           }else {
               Map<String, String> map = new HashMap<>();
               map.put(Common.Apikey_text, Common.Apikey_value);
               map.put("email", login_email.getText().toString().trim());
               map.put("password", login_password.getText().toString().trim());
               Log.e("email",login_email.getText().toString()+login_password.getText().toString());
               map.put("skin_id", "2");
               Call<LoginResponse> call1 = apiInterface.createUser(map);

               call1.enqueue(new Callback<LoginResponse>() {
                   @Override
                   public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                       if(response.isSuccessful()){
                           LoginResponse loginResponse = response.body();
                           if(loginResponse.getJwt()==null){

                               Toast.makeText(Login.this, "Invalid Credential", Toast.LENGTH_SHORT).show();

                           }else{
                               Common.jwt = loginResponse.getJwt();
                               Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                               Log.e("email", loginResponse.getJwt());
                                Islogin = true;
                               SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Login.this);
                               prefs.edit().putBoolean("Islogin", true).commit();
                               prefs.edit().putString("LoginName", loginResponse.getData().getCustomer().getName()).commit();// islogin is a boolean value of your login status

                               startActivity(new Intent(Login.this, DashBoard.class));
                              finish();
                           }
                           }
                   }

                   @Override
                   public void onFailure(Call<LoginResponse> call, Throwable t) {
                       Toast.makeText(Login.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                   }
               });
           }
       }
       else {
           Toast.makeText(this, "Please Check your internet.", Toast.LENGTH_SHORT).show();
       }

    }

    private void findIds() {
        login_Tv_signup = findViewById(R.id.login_Tv_signup);
        login_Tv_forgetPassword = findViewById(R.id.login_Tv_forgetPassword);
        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);
        login_btn_login = findViewById(R.id.login_btn_login);
        logon_imgBtn_back = findViewById(R.id.logon_imgBtn_back);
        login_cardview_gmail_login = findViewById(R.id.login_cardview_gmail_login);
        login_cardview_facebook_login = findViewById(R.id.login_cardview_facebook_login);

    }

    public void onBackPressed() {
//        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(Login.this, DashBoard.class);
//        Intent setIntent = new Intent(Intent.ACTION_MAIN);
//        setIntent.addCategory(Intent.CATEGORY_HOME);
//        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);

        return;
    }
}