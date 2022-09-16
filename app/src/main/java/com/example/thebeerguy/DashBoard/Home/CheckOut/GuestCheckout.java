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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

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

public class GuestCheckout extends AppCompatActivity {

    EditText Guest_name,guest_phone, guest_email, guest_address, guest_apt, guest_buzzer, guest_extraInfo,
            checkOut_TV_extraInfo, guestCheck_editText_pass;

    CheckBox guest_business_radioBtn, guest_hotel_radioBtn;
    ImageView guest_checkout_ImV_backBtn;

    TextView guestCheckOut_TV_days, guestCheckOut_TV_time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_checkout);

        getSupportActionBar().hide();

        GuestfindIds();

        guest_address.setText(LandingScreen.Address);

        guest_checkout_ImV_backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GuestCheckout.super.onBackPressed();
            }
        });

        guestCheckOut_TV_days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GuestDilogTimings();
            }
        });
        guestCheckOut_TV_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GuestDilogTimings();

            }
        });



    }

    private void GuestDilogTimings() {

        Dialog dialog = new Dialog(GuestCheckout.this);
        dialog.setContentView(R.layout.product_dialog);
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
    }


}