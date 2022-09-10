package com.example.thebeerguy.DashBoard;

import static com.example.common.Common.jwt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.sax.StartElementListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.Apis.APIClient;
import com.example.Apis.APIInterface;
import com.example.common.Common;
import com.example.common.CommonMethod;
import com.example.login.Login;
import com.example.login.responseLogin.LoginResponse;
import com.example.thebeerguy.DashBoard.Home.Adapters.BeerAdapter;
import com.example.thebeerguy.DashBoard.Home.Adapters.GridAdapter;

import com.example.thebeerguy.DashBoard.ResponseJson.homeResponse.ResponseHome;
import com.example.thebeerguy.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Recent extends Fragment {

    private GridView recent_gridView;
    private List<ResponseHome> recentList = new ArrayList<>();

    APIInterface apiInterface;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recent, container, false);

        recent_gridView = view.findViewById(R.id.recent_gridView);

        apiInterface = APIClient.getClient().create(APIInterface.class);

//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
//        boolean Islogin = prefs.getBoolean("Islogin", false); // get value of last login status
//
//        if (Islogin){
//
        recentApi("is_recent", "1");
//        }else {
//            Toast.makeText(getContext(), "Please Login First", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent (getContext(),Login.class));
//        }





        return view;


    }

//    private void modelListRecent() {
//
//        list.add(new SampleModel(R.drawable.beer, "Budweiser", "$2.90 - $104.95", "1"));
//        list.add(new SampleModel(R.drawable.vodka, "Budweiser", "$2.90 - $104.95", "2"));
//        list.add(new SampleModel(R.drawable.beer, "Budweiser", "$2.90 - $104.95", "3"));
//        list.add(new SampleModel(R.drawable.vodka, "Budweiser", "$2.90 - $104.95", "4"));
//        list.add(new SampleModel(R.drawable.beer, "Budweiser", "$2.90 - $104.95", "5"));
//        list.add(new SampleModel(R.drawable.beer, "Budweiser", "$2.90 - $104.95", "5"));
//        list.add(new SampleModel(R.drawable.beer, "Budweiser", "$2.90 - $104.95", "5"));
//        list.add(new SampleModel(R.drawable.beer, "Budweiser", "$2.90 - $104.95", "5"));
//
//    }

    private void recentApi(String typeKey, String typeID) {

        boolean networkCheck = CommonMethod.isNetworkAvailable(getContext());
        if (networkCheck) {

            Map<String, String> map = new HashMap<>();
            map.put(Common.Apikey_text, Common.Apikey_value);
            map.put(typeKey, typeID);
//            map.put("is_topten", "1");
//                map.put("skin_id", "2");

            Call<List<ResponseHome>> call1 = apiInterface.home(map);

            call1.enqueue(new Callback<List<ResponseHome>>() {
                @Override
                public void onResponse(Call<List<ResponseHome>> call, Response<List<ResponseHome>> response) {
                    if (response.isSuccessful()) {
                        List<ResponseHome> responseHomes = response.body();
//                            Common.jwt = loginResponse.getJwt();

                        recentList = response.body();

//                        GridAdapter gridAdapter = new GridAdapter(getContext(), list);
//                        if (typeID.equalsIgnoreCase("is_recent")) {
                            GridAdapter gridAdapter = new GridAdapter(getContext(), recentList);
                            recent_gridView.setAdapter(gridAdapter);



//                            Toast.makeText(getContext(), "Beer list", Toast.LENGTH_SHORT).show();

//                            startActivity(new Intent(getContext(), DashBoard.class));
                    } else {
                        Toast.makeText(getContext(), "No Data found", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<List<ResponseHome>> call, Throwable t) {
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(getContext(), "Please Check your internet.", Toast.LENGTH_SHORT).show();
        }

    }
}