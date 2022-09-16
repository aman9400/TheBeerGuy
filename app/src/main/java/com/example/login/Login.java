package com.example.login;

import android.content.Intent;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class Login extends AppCompatActivity implements View.OnClickListener {

    APIInterface apiInterface;
    private Button login_btn_login;
    private TextView login_Tv_signup;
    private TextView login_Tv_forgetPassword;
    private EditText login_email, login_password;
    private ImageButton logon_imgBtn_back;
    private CardView login_cardview_facebook_login, login_cardview_gmail_login;
    private Boolean Islogin = false;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 100;
    GoogleApiClient mGoogleApiClient;
    ConstraintLayout sign_in_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        findIds();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()

                .build();

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null) {
            Log.e("TAG", account.getDisplayName());
        }

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

//        SignInButton signInButton = findViewById(R.id.sign_in_button);
//        signInButton.setSize(SignInButton.SIZE_STANDARD);
//        signInButton.setOnClickListener(this);

        sign_in_button = findViewById(R.id.sign_in_button);
        sign_in_button.setOnClickListener(this);

        apiInterface = APIClient.getClient().create(APIInterface.class); //        APIClient.


        logon_imgBtn_back.setOnClickListener(v -> {
            Intent intentBack = new Intent(Login.this, LandingScreen.class);
            startActivity(intentBack);
            finish();
        });

        login_btn_login.setOnClickListener(v -> {
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

        login_cardview_gmail_login.setOnClickListener(v -> {
//            googleCardSignin();
        });
    }

    private void loginApi() {

        boolean networkCheck = CommonMethod.isNetworkAvailable(this);
        if (networkCheck) {
            if (login_email.getText().toString().isEmpty()) {
                Toast.makeText(this, R.string.empty_email_message, Toast.LENGTH_SHORT).show();
            } else if (login_password.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please enter your password address", Toast.LENGTH_SHORT).show();

            } else if (!Patterns.EMAIL_ADDRESS.matcher(login_email.getText().toString().trim()).matches()) {
                Toast.makeText(this, "Please enter correct email format", Toast.LENGTH_SHORT).show();

            } else {
                Map<String, String> map = new HashMap<>();
                map.put(Common.Apikey_text, Common.Apikey_value);
                map.put("email", login_email.getText().toString().trim());
                map.put("password", login_password.getText().toString().trim());
                Log.e("email", login_email.getText().toString() + login_password.getText().toString());
                map.put("skin_id", "2");
                Call<LoginResponse> call1 = apiInterface.createUser(map);

                call1.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            LoginResponse loginResponse = response.body();
                            if (loginResponse.getJwt() == null) {

                                Toast.makeText(Login.this, "Invalid Credential", Toast.LENGTH_SHORT).show();

                            } else {
                                Common.jwt = loginResponse.getJwt();
                                Common.Customer_ID = loginResponse.getData().getCustomer().getId();

                                Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                                Log.e("email", loginResponse.getJwt());
                                Islogin = true;
                                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Login.this);
                                prefs.edit().putBoolean("Islogin", true).commit();
                                prefs.edit().putString("LoginName", loginResponse.getData().getCustomer().getName()).commit();// islogin is a boolean value of your login status
                                prefs.edit().putString("email",loginResponse.getData().getCustomer().getEmail()).commit();//
                                prefs.edit().putString("name",loginResponse.getData().getCustomer().getName()).commit();//




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
        } else {
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
        finish();

        return;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            // ...
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();

            Log.e("TAG", "signInResult:success code=" + account.getDisplayName());
            // Signed in successfully, show authenticated UI.
//            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.e("TAG", "signInResult:failed code=" + e.getStatus());
//            updateUI(null);
        }
    }
}