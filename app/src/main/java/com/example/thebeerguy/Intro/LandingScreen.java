package com.example.thebeerguy.Intro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.Apis.APIClient;
import com.example.Apis.APIInterface;
import com.example.common.Common;
import com.example.common.CommonMethod;
import com.example.login.Login;
import com.example.login.responseLogin.LoginResponse;
import com.example.thebeerguy.DashBoard.DashBoard;
import com.example.thebeerguy.Intro.ResponseStore.ResponseStore;
import com.example.thebeerguy.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandingScreen extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationProviderClient;
    Button sp4_btn_login, sp4_btn_signup;
    TabLayout tabLayout;
    SearchView splas4_searchView_location;
    private String country;
    private final static int REQUEST_CODE = 100;
    APIInterface apiInterface;

//    private FusedLocationProviderClient client;
    public  static  String Address;

    private Boolean IsVisited = false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_screen);
        getSupportActionBar().hide();

        findViewIds();  // calling find id

        apiInterface = APIClient.getClient().create(APIInterface.class); //        APIClient.


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(LandingScreen.this);
        prefs.edit().putBoolean("IsVisited", true).commit();

//        ((LinearLayout) tabLayout.getTabAt(0).view).setVisibility(View.GONE);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this); // initiate fused location

        getLastLocation();

        // button to move to Dashboard
        sp4_btn_login.setOnClickListener(v -> {
            Intent sp4_loginIntent = new Intent(LandingScreen.this, DashBoard.class);
            sp4_loginIntent.putExtra("country", country);
            sp4_loginIntent.putExtra("address", Address);
            startActivity(sp4_loginIntent);
            finish();

        });

        // button to move to Login
        sp4_btn_signup.setOnClickListener(v -> {
            Intent sp4_signupIntent = new Intent(this, Login.class);
            startActivity(sp4_signupIntent);

        });
    }

    // method to get last location
    private void getLastLocation() {

        if (ContextCompat.checkSelfPermission
                (LandingScreen.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {


            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(location -> {

                        if (location != null) {

                            try {
                                Geocoder geocoder = new Geocoder(LandingScreen.this, Locale.getDefault());
                                List<android.location.Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//                                    lattitude.setText("Lattitude: "+addresses.get(0).getLatitude());
//                                    longitude.setText("Longitude: "+addresses.get(0).getLongitude());
//                                    address.setText("Address: " + addresses.get(0).getAddressLine(0));

                                splas4_searchView_location.setQuery(addresses.get(0).getAddressLine(0), true);
                                splas4_searchView_location.setIconifiedByDefault(false);
//                                Toast.makeText(LandingScreen.this, ""+addresses.get(0).getAddressLine(0), Toast.LENGTH_SHORT).show();
//                                Log.e("d",addresses.get(0).getAddressLine(0));
                                String[] x  = addresses.get(0).getAddressLine(0).split(",");
                                country = x[4];
                                Address = addresses.get(0).getAddressLine(0);
//                                Log.e("test",country);

                                storeApi(addresses.get(0).getLatitude(), addresses.get(0).getLongitude());

//                                Log.e("test", ""+addresses.get(0).getLatitude() + ","+ addresses.get(0).getLongitude());

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

    // ask permission method
    private void askPermission() {

        ActivityCompat.requestPermissions((LandingScreen.this), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);


    }


    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,
                                           int[] grantResults) {

        if (requestCode == REQUEST_CODE) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                getLastLocation();

            } else {

                Toast.makeText(LandingScreen.this, "Please provide the required permission", Toast.LENGTH_SHORT).show();
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    // find IDs
    private void findViewIds() {
        sp4_btn_login = findViewById(R.id.sp4_btn_login);
        sp4_btn_signup = findViewById(R.id.sp4_btn_signup);
        splas4_searchView_location = findViewById(R.id.splas4_searchView_location);

    }

    // Store location [ send lat and long ] respond
    private void storeApi(double latitude, double longitude) {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        boolean networkCheck = CommonMethod.isNetworkAvailable(this);
        if(networkCheck){

                Map<String, String> map = new HashMap<>();
                map.put(Common.Apikey_text, Common.Apikey_value);
                map.put("latitude", ""+latitude);
                map.put("longitude", ""+longitude);

                Call<List<ResponseStore>> call1 = apiInterface.storeApi(map);

                call1.enqueue(new Callback<List<ResponseStore>>() {
                    @Override
                    public void onResponse(Call<List<ResponseStore>> call, Response<List<ResponseStore>> response) {
                        progressDialog.dismiss();

                        if(response.isSuccessful()){
                            List<ResponseStore> loginResponse = response.body();
                            if(!loginResponse.isEmpty()){
                                Log.e("test", "0000"+loginResponse.get(0).getLatitude());

                            }else{
                                Toast.makeText(LandingScreen.this, "Not available on this address", Toast.LENGTH_SHORT).show();
                            }


                        }
                    }

                    @Override
                    public void onFailure(Call<List<ResponseStore>> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(LandingScreen.this, ""+ t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        }
        else {
            Toast.makeText(this, "Please Check your internet.", Toast.LENGTH_SHORT).show();
        }

    }
}