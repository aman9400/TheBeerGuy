package com.example.thebeerguy.Intro;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import com.example.thebeerguy.DashBoard.DashBoard;
import com.example.thebeerguy.DashBoard.Home.Home;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.Login;
import com.example.thebeerguy.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class Splash4 extends Fragment {
    FusedLocationProviderClient fusedLocationProviderClient;
    Button sp4_btn_login, sp4_btn_signup;
    TabLayout tabLayout;
    SearchView splas4_searchView_location;
    private String country;
    private final static int REQUEST_CODE = 100;

    private FusedLocationProviderClient client;
    public  static  String Address;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_splash4, container, false);

        findViewIds(view);

//        ((LinearLayout) tabLayout.getTabAt(0).view).setVisibility(View.GONE);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());

        getLastLocation();

//        splas4_searchView_location.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });



        sp4_btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sp4_loginIntent = new Intent(getContext(), DashBoard.class);
                sp4_loginIntent.putExtra("country", country);
                sp4_loginIntent.putExtra("address", Address);
                getContext().startActivity(sp4_loginIntent);
            }
        });

        sp4_btn_signup.setOnClickListener(v -> {
            Intent sp4_signupIntent = new Intent(getContext(), Login.class);
            startActivity(sp4_signupIntent);

        });





        return view;
    }

    private void getLastLocation() {

        if (ContextCompat.checkSelfPermission
                (getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {


            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(location -> {

                        if (location != null) {

                            try {
                                Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//                                    lattitude.setText("Lattitude: "+addresses.get(0).getLatitude());
//                                    longitude.setText("Longitude: "+addresses.get(0).getLongitude());
//                                    address.setText("Address: " + addresses.get(0).getAddressLine(0));
                                splas4_searchView_location.setQueryHint(addresses.get(0).getAddressLine(0));
                                Toast.makeText(getContext(), ""+addresses.get(0).getAddressLine(0), Toast.LENGTH_SHORT).show();
                                Log.e("d",addresses.get(0).getAddressLine(0));
                                String[] x  = addresses.get(0).getAddressLine(0).split(",");
                                country = x[4];
                                Address = addresses.get(0).getAddressLine(0);
                                Log.e("d",country);
                                splas4_searchView_location.setQuery( addresses.get(0).getAddressLine(0), false);
//                                    country.setText("Country: " + addresses.get(0).getCountryName());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    });


        } else {

            askPermission();


        }


    }

    private void askPermission() {

        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);


    }


    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,
                                            int[] grantResults) {

        if (requestCode == REQUEST_CODE) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                getLastLocation();

            } else {


                Toast.makeText(getContext(), "Please provide the required permission", Toast.LENGTH_SHORT).show();

            }


        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }



    private void findViewIds(View view) {
        sp4_btn_login = view.findViewById(R.id.sp4_btn_login);
        sp4_btn_signup = view.findViewById(R.id.sp4_btn_signup);
        splas4_searchView_location =view.findViewById(R.id.splas4_searchView_location);

    }



}