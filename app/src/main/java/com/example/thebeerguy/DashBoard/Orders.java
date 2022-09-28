package com.example.thebeerguy.DashBoard;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.Apis.APIClient;
import com.example.Apis.APIInterface;
import com.example.common.Common;
import com.example.common.CommonMethod;
import com.example.thebeerguy.DashBoard.Home.Adapters.CategoryAdapter;
import com.example.thebeerguy.DashBoard.Home.categoryResponse.ResponseCategory;
import com.example.thebeerguy.DashBoard.PurchaseHistoryResponse.ResponsePurchaseHistory;
import com.example.thebeerguy.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Orders extends Fragment {

    APIInterface apiInterface;

    RecyclerView orderHistory_recycleView;

    ImageButton orderHistoryImV_backBtn;

    private List<ResponsePurchaseHistory> orderHistoryList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_orders, container, false);



        orderHistoryImV_backBtn = view.findViewById(R.id.orderHistoryImV_backBtn);
        orderHistory_recycleView = view.findViewById(R.id.orderHistory_recycleView);

        orderHistoryImV_backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent  =  new Intent(getContext(), DashBoard.class);
                startActivity(intent);
            }
        });




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

                            orderHistoryList = response.body();

                            PurchaseHistoryAdapter purchaseHistoryAdapter = new PurchaseHistoryAdapter(getContext(), orderHistoryList);

                            orderHistory_recycleView.setHasFixedSize(true);
                            orderHistory_recycleView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                            orderHistory_recycleView.setAdapter(purchaseHistoryAdapter);

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