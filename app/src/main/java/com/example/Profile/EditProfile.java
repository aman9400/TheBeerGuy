package com.example.Profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thebeerguy.R;

public class EditProfile extends AppCompatActivity {

    private ImageView pro_editProfile, backbtn_editprofile;
    private TextView addprofile_editprofile;

    private EditText firstname_editProfile, lastname_editProfile, phone_editProfile, email_editProfile;
    private ConstraintLayout savebtn_editProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        getSupportActionBar().hide();

        initView();

        backbtn_editprofile.setOnClickListener(v->{
            super.onBackPressed();
        });
    }

    private void initView() {
        pro_editProfile = findViewById(R.id.pro_editProfile);

        addprofile_editprofile = findViewById(R.id.addprofile_editprofile);

        firstname_editProfile = findViewById(R.id.firstname_editProfile);
        lastname_editProfile = findViewById(R.id.lastname_editProfile);
        phone_editProfile = findViewById(R.id.phone_editProfile);
        email_editProfile = findViewById(R.id.email_editProfile);

        savebtn_editProfile = findViewById(R.id.savebtn_editProfile);

        backbtn_editprofile = findViewById(R.id.backbtn_editprofile);
    }
}