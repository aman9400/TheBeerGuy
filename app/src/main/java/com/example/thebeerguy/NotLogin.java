package com.example.thebeerguy;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.login.Login;
import com.example.thebeerguy.DashBoard.DashBoard;


public class NotLogin extends Fragment {

    TextView notLogin_TV_login;

    ImageButton notLogin_back_Btn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_not_login, container, false);

        notLogin_TV_login = view.findViewById(R.id.notLogin_TV_login);
        notLogin_back_Btn = view.findViewById(R.id.notLogin_back_Btn);

        notLogin_back_Btn.setOnClickListener(v -> startActivity(new Intent(getContext(), DashBoard.class)));

        notLogin_TV_login.setOnClickListener(v -> startActivity(new Intent(getContext(), Login.class)));

    return view;
    }
}