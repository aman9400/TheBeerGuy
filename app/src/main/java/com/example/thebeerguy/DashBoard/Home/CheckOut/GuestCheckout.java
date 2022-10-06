package com.example.thebeerguy.DashBoard.Home.CheckOut;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Profile.PaymentMethod;
import com.example.Signup.SignUp;
import com.example.Signup.responseSignup.ResponseSignup;
import com.example.common.Common;
import com.example.common.CommonMethod;
import com.example.login.Login;
import com.example.thebeerguy.Intro.LandingScreen;
import com.example.thebeerguy.R;
import com.example.thebeerguy.TermsConditions;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuestCheckout extends AppCompatActivity {

    EditText Guest_name,guest_phone, guest_email, guest_address, guest_apt, guest_buzzer, guest_extraInfo,
            checkOut_TV_extraInfo, guestCheck_editText_pass;

    CheckBox guest_business_radioBtn, guest_hotel_radioBtn;
    ImageView guest_checkout_ImV_backBtn;

    TextView guestCheckOut_TV_days, guestCheckOut_TV_time, guestCheckOut_TC_tV;

    Button guestCheckout_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_checkout);

        getSupportActionBar().hide();

        GuestfindIds();



        guestCheckOut_TC_tV.setOnClickListener(v -> {
            Intent intent = new Intent(GuestCheckout.this, TermsConditions.class);
            startActivity(intent);
        });

        guest_address.setText(LandingScreen.Address);

        guest_checkout_ImV_backBtn.setOnClickListener(v -> GuestCheckout.super.onBackPressed());

        guestCheckOut_TV_days.setOnClickListener(v -> GuestDilogTimings());
        guestCheckOut_TV_time.setOnClickListener(v -> GuestDilogTimings());


        if(guest_email.getText().toString().isEmpty()){
            Toast.makeText(this, R.string.empty_email_message, Toast.LENGTH_SHORT).show();
        }else if(guestCheck_editText_pass.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();

        }else if(!Patterns.EMAIL_ADDRESS.matcher(guest_email.getText().toString().trim()).matches()){
            Toast.makeText(this, "Please enter correct email format", Toast.LENGTH_SHORT).show();

        }else if (Guest_name.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();

        }else if (guest_phone.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter phone number", Toast.LENGTH_SHORT).show();

        }else if (guest_apt.getText().toString().isEmpty()){
            Toast.makeText(this, "enter correct APT ", Toast.LENGTH_SHORT).show();
        }else if (guest_buzzer.getText().toString().isEmpty()){

            Toast.makeText(this, "enter correct Buzzer", Toast.LENGTH_SHORT).show();
        }else{
            guestCheckout_button.setEnabled(false);
            guestCheckout_button.setBackground(getDrawable(R.drawable.button_fade));
        }

        guestCheckout_button.setOnClickListener(v -> {
            Intent intent = new Intent(GuestCheckout.this, PaymentMethod.class);
            startActivity(intent);
        });



    }

    private void GuestDilogTimings() {

        Dialog dialog = new Dialog(GuestCheckout.this);
        dialog.setContentView(R.layout.checkout_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.getWindow().setWindowAnimations(R.style.AnimationForDialog);

        TextView checkoutDialog_TV_cancle = dialog.findViewById(R.id.checkoutDialog_TV_cancle);
        TextView checkoutDialog_TV_ok = dialog.findViewById(R.id.checkoutDialog_TV_ok);
        RecyclerView checkoutDialog_recyclerView = dialog.findViewById(R.id.checkoutDialog_recyclerView);

//        CheckoutDialogAdapter checkoutDialogAdapter = new CheckoutDialogAdapter(GuestCheckout.this, checkOutModelList2);
//        checkoutDialog_recyclerView.setLayoutManager(new LinearLayoutManager(GuestCheckout.this, LinearLayoutManager.VERTICAL, false));
//        checkoutDialog_recyclerView.setAdapter(checkoutDialogAdapter);

        checkoutDialog_TV_cancle.setOnClickListener(v1 -> dialog.cancel());

        checkoutDialog_TV_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();

    }

    private void GuestfindIds() {

        Guest_name = findViewById(R.id.Guest_name);
        guest_phone = findViewById(R.id.guest_phone);
        guest_email = findViewById(R.id.guest_email);
        guest_address = findViewById(R.id.guest_address);
        guest_apt = findViewById(R.id.guest_apt);
        guest_buzzer = findViewById(R.id.guest_buzzer);
        guest_extraInfo = findViewById(R.id.guest_extraInfo);
        checkOut_TV_extraInfo = findViewById(R.id.checkOut_TV_extraInfo);
        guestCheck_editText_pass = findViewById(R.id.guestCheck_editText_pass);
        guest_business_radioBtn = findViewById(R.id.guest_business_radioBtn);
        guest_hotel_radioBtn = findViewById(R.id.guest_hotel_radioBtn);
        guest_checkout_ImV_backBtn = findViewById(R.id.guest_checkout_ImV_backBtn);
        guestCheckOut_TV_time = findViewById(R.id.guestCheckOut_TV_time);
        guestCheckOut_TV_days = findViewById(R.id.guestCheckOut_TV_days);
        guestCheckOut_TC_tV = findViewById(R.id.guestCheckOut_TC_tV);
        guestCheckout_button = findViewById(R.id.guestCheckout_button);
    }


}