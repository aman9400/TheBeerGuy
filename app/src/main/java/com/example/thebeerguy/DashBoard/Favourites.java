package com.example.thebeerguy.DashBoard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
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


public class Favourites extends Fragment {

    private GridView fav_gridView;
    private List<ResponseHome> BeerList = new ArrayList<>();
    APIInterface apiInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);

        fav_gridView = view.findViewById(R.id.fav_gridView);

        apiInterface = APIClient.getClient().create(APIInterface.class);

            homeApi("is_fave", "1");
//            Toast.makeText(getContext(), "Favourites", Toast.LENGTH_SHORT).show();

        return view;
    }

    private void homeApi(String typeKey, String typeID) {

        boolean networkCheck = CommonMethod.isNetworkAvailable(getContext());
        if (networkCheck) {

            Map<String, String> map = new HashMap<>();
            map.put(Common.Apikey_text, Common.Apikey_value);
            map.put(typeKey, typeID);
            map.put("is_topten", "1");
//                map.put("skin_id", "2");

            Call<List<ResponseHome>> call1 = apiInterface.home( map);

            call1.enqueue(new Callback<List<ResponseHome>>() {
                @Override
                public void onResponse(Call<List<ResponseHome>> call, Response<List<ResponseHome>> response) {
                    if (response.isSuccessful()) {
                        List<ResponseHome> loginResponse = response.body();
//                           Common.jwt = loginResponse.getJwt();

                        BeerList = response.body();

//                        BeerAdapter fav_adapter = new BeerAdapter(getContext(), BeerList);
                        if (typeID.equalsIgnoreCase("is_fave")) {
                            GridAdapter gridAdapter = new GridAdapter(getContext(), BeerList);
                            fav_gridView.setAdapter(gridAdapter);

                        }

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