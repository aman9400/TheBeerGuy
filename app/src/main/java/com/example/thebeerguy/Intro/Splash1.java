package com.example.thebeerguy.Intro;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.thebeerguy.R;

public class Splash1 extends Fragment {

    Button sp1_btn_skip;
    ViewPager viewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_splash1, container, false);



        sp1_btn_skip = view.findViewById(R.id.sp1_skip_btn);

        viewPager = getActivity().findViewById(R.id.viewPager1);

        sp1_btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(4);
            }
        });




        return view;
    }
}