package com.example.thebeerguy.DashBoard.Home.CheckOut;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.Profile.PaymentMethod;
import com.example.thebeerguy.Product_Details.ProductDetails;
import com.example.thebeerguy.Product_Details.ProductDetailsDialogAdapter;
import com.example.thebeerguy.Product_Details.ProductDetailsResponse.Package;
import com.example.thebeerguy.R;

import java.util.ArrayList;
import java.util.List;

public class Checkout extends AppCompatActivity {

   private TextView checkOut_TV_address, checkOut_TV_days, checkOut_TV_time;
   private EditText checkOut_TV_extraInfo;
   private Button checkout_button_continue;
   private AppCompatImageView checkout_ImV_backBtn;

    private List<CheckOutModel> checkOutModelList2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        findIds();

        checkout_ImV_backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        checkout_button_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Checkout.this, PaymentMethod.class));
            }
        });



        checkOut_TV_days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dilogtimings();
            }
        });

        checkOut_TV_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dilogtimings();
            }
        });

    }

    private void dilogtimings() {

        Dialog dialog = new Dialog(Checkout.this);
        dialog.setContentView(R.layout.product_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.getWindow().setWindowAnimations(R.style.AnimationForDialog);

        TextView checkoutDialog_TV_cancle = dialog.findViewById(R.id.checkoutDialog_TV_cancle);
        TextView checkoutDialog_TV_ok = dialog.findViewById(R.id.checkoutDialog_TV_ok);
        RecyclerView checkoutDialog_recyclerView = dialog.findViewById(R.id.checkoutDialog_recyclerView);

        CheckoutDialogAdapter checkoutDialogAdapter = new CheckoutDialogAdapter(Checkout.this, checkOutModelList2);
        checkoutDialog_recyclerView.setLayoutManager(new LinearLayoutManager(Checkout.this, LinearLayoutManager.VERTICAL, false));
        checkoutDialog_recyclerView.setAdapter(checkoutDialogAdapter);

        checkoutDialog_TV_cancle.setOnClickListener(v1 -> dialog.cancel());

        checkoutDialog_TV_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();

    }

    private void findIds() {
        checkOut_TV_address = findViewById(R.id.checkOut_TV_address);
        checkOut_TV_days = findViewById(R.id.checkOut_TV_days);
        checkOut_TV_time = findViewById(R.id.checkOut_TV_time);
        checkOut_TV_extraInfo = findViewById(R.id.checkOut_TV_extraInfo);
        checkout_button_continue = findViewById(R.id.checkout_button_continue);
        checkout_ImV_backBtn = findViewById(R.id.checkout_ImV_backBtn);


    }
}