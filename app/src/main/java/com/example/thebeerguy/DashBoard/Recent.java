package com.example.thebeerguy.DashBoard;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.Apis.APIClient;
import com.example.Apis.APIInterface;
import com.example.Databse.MyDatabase;
import com.example.Databse.RecentProduct;
import com.example.common.Common;
import com.example.common.CommonMethod;
import com.example.login.Login;
import com.example.login.responseLogin.LoginResponse;
import com.example.thebeerguy.DashBoard.Home.Adapters.BeerAdapter;
import com.example.thebeerguy.DashBoard.Home.Adapters.GridAdapter;

import com.example.thebeerguy.DashBoard.RecentResponse.ResponseRecent;
import com.example.thebeerguy.DashBoard.ResponseJson.ProductReq;
import com.example.thebeerguy.DashBoard.ResponseJson.homeResponse.ResponseHome;
import com.example.thebeerguy.Product_Details.ProductDetails;
import com.example.thebeerguy.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Recent extends Fragment {

    private GridView recent_gridView;
    private List<ResponseRecent> recentList = new ArrayList<>();

    APIInterface apiInterface;
     String recentId = ProductDetails.productID;


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
//        recentApi("is_recent", "1");
//        }else {
//            Toast.makeText(getContext(), "Please Login First", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent (getContext(),Login.class));
//        }

       RecentProduct[] recentProducts = MyDatabase.getDatabase(getContext()).patientDAO().showRecent();


            recentApi("is_recent", "1", recentProducts);




        return view;


    }

    public static <T> List<T> ArrayToListConversion(T array[])
    {
//creating the constructor of the List class
        List<T> list = new ArrayList<>();
//using for-each loop to iterate over the array
        for (T t : array)
        {
//adding each element to the List
            list.add(t);
        }
//returns the list converted into Array
        return list;
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

    private void recentApi(String typeKey, String typeID, RecentProduct[] recentProducts) {

        boolean networkCheck = CommonMethod.isNetworkAvailable(getContext());
        if (networkCheck) {

            List<ProductReq> list = new ArrayList<>();
            List<RecentProduct> listProd = ArrayToListConversion(recentProducts);

            for (int i = 0; i < recentProducts.length; i++) {
                ProductReq productReq = new ProductReq();
                productReq.setProductId(recentProducts[i].getProduct_id());
                productReq.setPackageId(recentProducts[i].getPackage_id());
                productReq.setPrice(recentProducts[i].getPrice());
                productReq.setQuantity(recentProducts[i].getQuantity());
                list.add(productReq);

            }

            RecentProductRequest recentProductRequest = new RecentProductRequest();
            recentProductRequest.setApiKey(Common.Apikey_value);
            recentProductRequest.setIs_recent(1);
            recentProductRequest.setProducts(list);

            GridAdapter gridAdapter = new GridAdapter(getContext(), listProd);
            recent_gridView.setAdapter(gridAdapter);

            Call<List<ResponseRecent>> call1 = apiInterface.recent(recentProductRequest, Common.jwt);

            call1.enqueue(new Callback<List<ResponseRecent>>() {
                @Override
                public void onResponse(Call<List<ResponseRecent>> call, Response<List<ResponseRecent>> response) {
                    if (response.isSuccessful()) {
                        List<ResponseRecent> responseHomes = response.body();
//                            Common.jwt = loginResponse.getJwt();

                        recentList = response.body();

//                        GridAdapter gridAdapter = new GridAdapter(getContext(), list);
//                        if (typeID.equalsIgnoreCase("is_recent")) {
//                            GridAdapter gridAdapter = new GridAdapter(getContext(), recentList);
//                            recent_gridView.setAdapter(gridAdapter);

                        Log.e("response", response.toString());



//                            Toast.makeText(getContext(), "Beer list", Toast.LENGTH_SHORT).show();

//                            startActivity(new Intent(getContext(), DashBoard.class));
                    } else {
                        Toast.makeText(getContext(), "No Data found", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<List<ResponseRecent>> call, Throwable t) {
                    Toast.makeText(getContext(), "Not logged in yet", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(getContext(), "Please Check your internet.", Toast.LENGTH_SHORT).show();
        }

    }
}