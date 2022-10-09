package com.example.thebeerguy.DashBoard.Home.CheckOut;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.Profile.PaymentMethod;
import com.example.Signup.SignUp;
import com.example.login.Login;
import com.example.thebeerguy.Intro.LandingScreen;
import com.example.thebeerguy.Product_Details.ProductDetails;
import com.example.thebeerguy.Product_Details.ProductDetailsDialogAdapter;
import com.example.thebeerguy.Product_Details.ProductDetailsResponse.Package;
import com.example.thebeerguy.R;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Checkout extends AppCompatActivity implements SelectedDates {

   private TextView checkOut_TV_address, checkOut_TV_days, checkOut_TV_time, checkOut_TV_name;
   private EditText checkOut_TV_extraInfo;
   private Button checkout_button_continue;
   private AppCompatImageView checkout_ImV_backBtn;
    List<String> dateList = new ArrayList<>();

    String selectedDate;
    private List<CheckOutModel> checkOutModelList2 = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        getSupportActionBar().hide();

        findIds();

         getlastTenDaysDates();

        checkOut_TV_address.setText(LandingScreen.Address);

        SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(this);
        String nameLoggedIn = prefs1.getString("LoginName", "");
        checkOut_TV_name.setText(nameLoggedIn);

        checkout_ImV_backBtn.setOnClickListener(v -> onBackPressed());

        checkout_button_continue.setOnClickListener(v -> {
            Intent intent = new Intent(Checkout.this, PaymentMethod.class);
            startActivity(intent);

        });



        checkOut_TV_days.setOnClickListener(v -> dilogtimings());

        checkOut_TV_time.setOnClickListener(v -> dilogtimings());

    }

    private void dilogtimings() {

        Dialog dialog = new Dialog(Checkout.this);
        dialog.setContentView(R.layout.checkout_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.getWindow().setWindowAnimations(R.style.AnimationForDialog);

        TextView checkoutDialog_TV_cancle = dialog.findViewById(R.id.checkoutDialog_TV_cancle);
        TextView checkoutDialog_TV_ok = dialog.findViewById(R.id.checkoutDialog_TV_ok);
        RecyclerView checkoutDialog_recyclerView = dialog.findViewById(R.id.checkoutDialog_recyclerView);

        CheckoutDialogAdapter checkoutDialogAdapter = new CheckoutDialogAdapter(Checkout.this, dateList , this,
                "date");
        checkoutDialog_recyclerView.setLayoutManager(new LinearLayoutManager(Checkout.this, LinearLayoutManager.VERTICAL, false));
        checkoutDialog_recyclerView.setAdapter(checkoutDialogAdapter);

        checkoutDialog_TV_cancle.setOnClickListener(v1 -> dialog.cancel());

        checkoutDialog_TV_ok.setOnClickListener(v -> {
            checkOut_TV_days.setText(selectedDate);
        });

        dialog.show();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void getlastTenDaysDates() {
        List<String> days = new ArrayList<> ();
        LocalDate now = LocalDate.now();

        for (LocalDate d = now.minusDays(5); !d.isAfter(now); d = d.plusDays(1)) {
            days.add(d.toString());
        }

        for (String x : days) {
            dateList.add(x);
        }
    }


    private void findIds() {
        checkOut_TV_address = findViewById(R.id.checkOut_TV_address);
        checkOut_TV_days = findViewById(R.id.checkOut_TV_days);
        checkOut_TV_time = findViewById(R.id.checkOut_TV_time);
        checkOut_TV_extraInfo = findViewById(R.id.checkOut_TV_extraInfo);
        checkout_button_continue = findViewById(R.id.checkout_button_continue);
        checkout_ImV_backBtn = findViewById(R.id.checkout_ImV_backBtn);
        checkOut_TV_name = findViewById(R.id.checkOut_TV_name);


    }

    @Override
    public void getSelectedDate(String date, String msg) {
        selectedDate = date;
    }
}