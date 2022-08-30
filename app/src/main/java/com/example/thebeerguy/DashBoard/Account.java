package com.example.thebeerguy.DashBoard;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Profile.EditProfile;
import com.example.Profile.ManageAddress;
import com.example.Profile.PaymentMethod;
import com.example.thebeerguy.R;

public class Account extends Fragment {

    private ImageView backbtn_account, next_manage_account, next_payment_account, next_help_account, pro_account;
    private TextView name_account, phone_account, email_account, edit_text_account;

    ConstraintLayout account_constraintLayout_payment, account_constraintLayout_manageAddress;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        initView(view);

        edit_text_account.setOnClickListener(v->{
            Intent intent = new Intent(getContext(), EditProfile.class);
            startActivity(intent);
        });

        backbtn_account.setOnClickListener(v -> {
           Intent intent = new Intent(getContext(), DashBoard.class);
           startActivity(intent);
        });

        account_constraintLayout_manageAddress.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ManageAddress.class);
            startActivity(intent);
        });

        account_constraintLayout_payment.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), PaymentMethod.class);
            startActivity(intent);
        });



        return view;
    }

    private void initView(View view) {

        backbtn_account = view.findViewById(R.id.backbtn_editprofile);
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