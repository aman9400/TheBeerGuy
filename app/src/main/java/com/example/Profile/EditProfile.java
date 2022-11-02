package com.example.Profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Apis.APIClient;
import com.example.Apis.APIInterface;
import com.example.Profile.AddAddressResponse.ResponseAddAddress;
import com.example.Signup.SignUp;
import com.example.Signup.responseSignup.ResponseSignup;
import com.example.common.Common;
import com.example.common.CommonMethod;
import com.example.login.Login;
import com.example.thebeerguy.Intro.LandingScreen;
import com.example.thebeerguy.R;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfile extends AppCompatActivity {

    private ImageView pro_editProfile, backbtn_editprofile;
    private TextView addprofile_editprofile, phone_editProfile,email_editProfile, address_editProfile;

    private EditText firstname_editProfile, buzzer_editProfile;
    private ConstraintLayout savebtn_editProfile;

    APIInterface apiInterface;
    SharedPreferences prefs1;
    String emailname ;
    String name ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        getSupportActionBar().hide();

        initView();


        apiInterface = APIClient.getClient().create(APIInterface.class);

        prefs1 = PreferenceManager.getDefaultSharedPreferences(this);

        emailname = prefs1.getString("email", "");
        name = prefs1.getString("name", "");

        email_editProfile.setText(emailname);
        phone_editProfile.setText(SignUp.phone);
        firstname_editProfile.setText(name);

        addprofile_editprofile.setText(LandingScreen.Address);


        savebtn_editProfile.setOnClickListener(v -> {

            Intent intent = new Intent(EditProfile.this, ManageAddress.class);
            startActivity(intent);

        });





        backbtn_editprofile.setOnClickListener(v->{
            super.onBackPressed();
        });
    }

    private void initView() {
        pro_editProfile = findViewById(R.id.pro_editProfile);

        addprofile_editprofile = findViewById(R.id.addprofile_editprofile);

        firstname_editProfile = findViewById(R.id.firstname_editProfile);
        address_editProfile = findViewById(R.id.address_editProfile);
        phone_editProfile = findViewById(R.id.phone_editProfile);
        email_editProfile = findViewById(R.id.email_editProfile);

        savebtn_editProfile = findViewById(R.id.savebtn_editProfile);

        backbtn_editprofile = findViewById(R.id.backbtn_editprofile);
        buzzer_editProfile = findViewById(R.id.buzzer_editProfile);

    }


}