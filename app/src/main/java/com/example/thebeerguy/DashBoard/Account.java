package com.example.thebeerguy.DashBoard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Profile.EditProfile;
import com.example.Profile.ManageAddress;
import com.example.Profile.PaymentMethod;
import com.example.Signup.SignUp;
import com.example.login.Login;
import com.example.thebeerguy.R;

public class Account extends Fragment {

    private ImageView  next_manage_account, next_payment_account, next_help_account, pro_account;
    private TextView name_account, phone_account, email_account, edit_text_account;

    ConstraintLayout account_constraintLayout_payment, account_constraintLayout_manageAddress;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);




        initView(view);

        edit_text_account.setOnClickListener(v->{
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
            boolean Islogin = prefs.getBoolean("Islogin", false); // get value of last login status

            if (Islogin){
                Intent intent = new Intent(getContext(), EditProfile.class);
                startActivity(intent);

            }else {
                Toast.makeText(getContext(), "Please Login First", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), Login.class));
            }

        });


        account_constraintLayout_manageAddress.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ManageAddress.class);
            startActivity(intent);
        });

        account_constraintLayout_payment.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), PaymentMethod.class);
            startActivity(intent);
        });


        SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(getContext());
        String emailname = prefs1.getString("email", "Guest@beerguy.com");
        String name = prefs1.getString("name", "");

        email_account.setText(emailname);
        phone_account.setText(SignUp.phone);
        name_account.setText(name);



        return view;
    }





    private void initView(View view) {

        next_manage_account = view.findViewById(R.id.next_manage_account);
        next_payment_account = view.findViewById(R.id.next_payment_account);
        next_help_account = view.findViewById(R.id.next_help_account);
        pro_account = view.findViewById(R.id.pro_account);

        name_account = view.findViewById(R.id.name_account);
        phone_account = view.findViewById(R.id.phone_account);
        email_account = view.findViewById(R.id.email_account);
        edit_text_account = view.findViewById(R.id.edit_text_account);

        account_constraintLayout_payment = view.findViewById(R.id.account_constraintLayout_payment);
        account_constraintLayout_manageAddress = view.findViewById(R.id.account_constraintLayout_manageAddress);



    }
}