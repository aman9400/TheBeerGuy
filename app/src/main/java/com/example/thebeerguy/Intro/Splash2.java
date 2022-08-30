package com.example.thebeerguy.Intro;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.login.Login;
import com.example.thebeerguy.R;


public class Splash2 extends Fragment {

    ViewPager viewPager;
    Button sp2_skip_btn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_splash2, container, false);

        sp2_skip_btn = view.findViewById(R.id.sp2_skip_btn);
        viewPager = getActivity().findViewById(R.id.viewPager1);

        sp2_skip_btn.setOnClickListener(v -> {
            viewPager.setCurrentItem(4);
        });





        return view;

    }
}