package com.example.thebeerguy.DashBoard;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.Apis.APIClient;
import com.example.Apis.APIInterface;
import com.example.common.Common;
import com.example.common.CommonMethod;
import com.example.thebeerguy.DashBoard.Home.Adapters.CategoryAdapter;
import com.example.thebeerguy.DashBoard.Home.categoryResponse.ResponseCategory;
import com.example.thebeerguy.DashBoard.PurchaseHistoryResponse.ResponsePurchaseHistory;
import com.example.thebeerguy.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Orders extends Fragment {

    APIInterface apiInterface;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_orders, container, false);

        getActivity().getActionBar().hide();


        apiInterface = APIClient.getClient().create(APIInterface.class);

        purchaseHistoryApi();





    return view;
    }

    private void purchaseHistoryApi() {

            boolean networkCheck = CommonMethod.isNetworkAvailable(getContext());
            if (networkCheck) {

                Map<String, String> map = new HashMap<>();
                map.put(Common.Apikey_text, Common.Apikey_value);

//            map.put("is_topten", "1");
//                map.put(typeID"skin_id", "2");

                Call<List<ResponsePurchaseHistory>> call1 = apiInterface.purchaseHistory(map);

                call1.enqueue(new Callback<List<ResponsePurchaseHistory>>() {
                    @Override
                    public void onResponse(Call<List<ResponsePurchaseHistory>> call, Response<List<ResponsePurchaseHistory>> response) {
                        if (response.isSuccessful()) {
                            List<ResponsePurchaseHistory> loginResponse = response.body();
//                            Common.jwt = loginResponse.getJwt();

//                            categoryList = response.body();
//
//                            CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList, getContext(), typeID);
//
//                            beer_recyclerView.setHasFixedSize(true);
//                            beer_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//                            beer_recyclerView.setAdapter(categoryAdapter);

                        } else {
                            Toast.makeText(getContext(), "No Data found", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<List<ResponsePurchaseHistory>> call, Throwable t) {
                        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });

            } else {
                Toast.makeText(getContext(), "Please Check your internet.", Toast.LENGTH_SHORT).show();
            }

        }



}