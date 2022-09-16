package com.example.thebeerguy.Intro;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Apis.APIClient;
import com.example.Apis.APIInterface;
import com.example.common.Common;
import com.example.common.CommonMethod;
import com.example.login.Login;
import com.example.thebeerguy.DashBoard.DashBoard;
import com.example.thebeerguy.Intro.ResponseStore.ResponseStore;
import com.example.thebeerguy.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandingScreen extends AppCompatActivity implements PlacesAutoCompleteAdapter.ClickListener {

    private final static int REQUEST_CODE = 100;
    //    private FusedLocationProviderClient client;
    public static String Address;
    public static String hour;
    private final Boolean IsVisited = false;
    Button sp4_btn_login, sp4_btn_signup;
    TabLayout tabLayout;
    SearchView splas4_searchView_location;
    APIInterface apiInterface;
    double latitude, longitude;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private String country;
    private PlacesAutoCompleteAdapter mAutoCompleteAdapter;
    private RecyclerView recyclerView;
    private Place getDataPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_screen);

        getSupportActionBar().hide();

        findViewIds();  // calling find id

        apiInterface = APIClient.getClient().create(APIInterface.class); //        APIClient.


        recyclerView = findViewById(R.id.places_recycler_view);
        mAutoCompleteAdapter = new PlacesAutoCompleteAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAutoCompleteAdapter.setClickListener(this);
        recyclerView.setAdapter(mAutoCompleteAdapter);
        mAutoCompleteAdapter.notifyDataSetChanged();

        splas4_searchView_location.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (!s.equals("")) {
                    mAutoCompleteAdapter.getFilter().filter(s);
                    if (recyclerView.getVisibility() == View.GONE) {
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (recyclerView.getVisibility() == View.VISIBLE) {
                        recyclerView.setVisibility(View.GONE);
                    }
                }
                return false;
            }
        });


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(LandingScreen.this);
        prefs.edit().putBoolean("IsVisited", true).commit();

//        ((LinearLayout) tabLayout.getTabAt(0).view).setVisibility(View.GONE);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this); // initiate fused location

            getLastLocation();
        }
//        MyDatabase.getDatabase(LandingScreen.this).patientDAO().setCartNumber(
//                0
//        );

        // button to move to Dashboard

        sp4_btn_login.setOnClickListener(v -> {

            storeApi();


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

                                splas4_searchView_location.setQuery(addresses.get(0).getAddressLine(0), false);
                                splas4_searchView_location.setIconifiedByDefault(false);
//                                Toast.makeText(LandingScreen.this, ""+addresses.get(0).getAddressLine(0), Toast.LENGTH_SHORT).show();
//                                Log.e("d",addresses.get(0).getAddressLine(0));
                                String[] x = addresses.get(0).getAddressLine(0).split(",");
                                country = x[4];
                                Address = addresses.get(0).getAddressLine(0);
//                                Log.e("test",country);

                                latitude = addresses.get(0).getLatitude();
                                longitude = addresses.get(0).getLongitude();
//                                storeApi();

//                                Log.e("test", ""+addresses.get(0).getLatitude() + ","+ addresses.get(0).getLongitude());

                                splas4_searchView_location.setQuery(addresses.get(0).getAddressLine(0), false);
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
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
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
    private void storeApi() {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        boolean networkCheck = CommonMethod.isNetworkAvailable(this);
        if (networkCheck) {

            Map<String, String> map = new HashMap<>();
            map.put(Common.Apikey_text, Common.Apikey_value);
//
//            map.put("latitude", "51.2537750");
//            map.put("longitude", "-85.3232140");
            map.put("latitude", "" + latitude);
            map.put("longitude", "" + longitude);

            Call<List<ResponseStore>> call1 = apiInterface.storeApi(map);

            call1.enqueue(new Callback<List<ResponseStore>>() {
                @Override
                public void onResponse(Call<List<ResponseStore>> call, Response<List<ResponseStore>> response) {
                    progressDialog.dismiss();

                    if (response.isSuccessful()) {
                        List<ResponseStore> responseStores = response.body();


                        if (!responseStores.isEmpty()) {
                            Log.e("test", "0000" + responseStores.get(0).getLatitude());
                            Intent sp4_loginIntent = new Intent(LandingScreen.this, DashBoard.class);
                            sp4_loginIntent.putExtra("country", country);
                            sp4_loginIntent.putExtra("address", Address);
                            startActivity(sp4_loginIntent);
                            finish();
                            hour = responseStores.get(0).getHours().toString();
                            Common.phone = responseStores.get(0).getPhone().toString();

                        } else {

                            Intent intent = new Intent(LandingScreen.this, NoCoverage.class);
                            startActivity(intent);
//                            Toast.makeText(LandingScreen.this, "Not available on this address", Toast.LENGTH_SHORT).show();
                        }


                    }
                }

                @Override
                public void onFailure(Call<List<ResponseStore>> call, Throwable t) {
                    progressDialog.dismiss();
//                    Toast.makeText(LandingScreen.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(this, "Please Check your internet.", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void click(Place place) {
//        Toast.makeText(this, place.getAddress() + ", " + place.getLatLng().latitude + place.getLatLng().longitude, Toast.LENGTH_SHORT).show();
        getDataPlace = place;
        splas4_searchView_location.setQuery(place.getAddress(), true);
        recyclerView.setVisibility(View.GONE);
        latitude = Objects.requireNonNull(place.getLatLng()).latitude;
        longitude = place.getLatLng().longitude;

        Address = place.getAddress();

    }
}