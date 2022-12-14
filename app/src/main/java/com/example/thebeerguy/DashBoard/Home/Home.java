package com.example.thebeerguy.DashBoard.Home;

import static com.example.thebeerguy.DashBoard.DashBoard.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Apis.APIClient;
import com.example.Apis.APIInterface;
import com.example.common.Common;
import com.example.common.CommonMethod;
import com.example.thebeerguy.DashBoard.Home.Adapters.BeerAdapter;

import com.example.thebeerguy.DashBoard.Home.Adapters.CategoryAdapter;
import com.example.thebeerguy.DashBoard.Home.Adapters.LiquorAdapter;
import com.example.thebeerguy.DashBoard.Home.Adapters.OnSaleAdapter;
import com.example.thebeerguy.DashBoard.Home.Adapters.WhatsHotAdapter;
import com.example.thebeerguy.DashBoard.Home.Adapters.WineAdapter;
import com.example.thebeerguy.DashBoard.Home.SubCategory.SubCategory;
import com.example.thebeerguy.DashBoard.Home.categoryResponse.ResponseCategory;
import com.example.thebeerguy.DashBoard.ResponseJson.homeResponse.ResponseHome;
import com.example.thebeerguy.Intro.LandingScreen;
import com.example.thebeerguy.Intro.Splash4;
import com.example.thebeerguy.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends Fragment {

    int x = 1;
    private RecyclerView onSale_recyclerView, whatsHot_recyclerView, wine_recyclerView;
    private RecyclerView beer_recyclerView, liquor_recyclerView;
    private List<ResponseHome> homeList = new ArrayList<>();
    private List<ResponseCategory> categoryList = new ArrayList<>();
    //    private List<ResponseSearch> searchList = new ArrayList<>();
    private SearchView search_view;
    APIInterface apiInterface;
    private String country;
    private ProgressDialog progressDialog;
    private TextView tv_address_home;
    private ImageView downArrow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        find(view);

        downArrow = view.findViewById(R.id.downArrow);

        tv_address_home = view.findViewById(R.id.tv_address_home);
        tv_address_home.setText(LandingScreen.Address);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        downArrow.setOnClickListener(v->{

            changeLocation();
        });

        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
//                Intent intent = new Intent(getContext(), SubCategory.class);
//                intent.putExtra("subCatId", "1");
//                intent.putExtra("typeID", s);
//                intent.putExtra("name", "search");
//                startActivity(intent);

                Intent intent = new Intent(getContext(), SearchProduct.class);
                intent.putExtra("subCatId", "3968");
                intent.putExtra("typeID", "1");
                intent.putExtra("name", "search");
                intent.putExtra("keyword", s);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });

        apiInterface = APIClient.getClient().create(APIInterface.class);

        country = requireActivity().getIntent().getStringExtra("country");

        whatsHotApi("is_popular", "1");
        onSaleApi("is_onsale", "1");
        beerApi("type_id", "1");
        liquorApi("type_id", "3");
        wineApi("type_id", "2");

        return view;
    }

    private void changeLocation() {

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.location_change_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.getWindow().setWindowAnimations(R.style.AnimationForDialog);

        TextView changeLocation_TV_cancle, changeLocation_TV_yes;

        changeLocation_TV_cancle = dialog.findViewById(R.id.changeLocation_TV_cancle);
        changeLocation_TV_yes = dialog.findViewById(R.id.changeLocation_TV_yes);

        changeLocation_TV_cancle.setOnClickListener(v -> dialog.dismiss());

        changeLocation_TV_yes.setOnClickListener(v -> {
            dialog.dismiss();
            startActivity(new Intent(getContext(), LandingScreen.class));
        });

        dialog.show();

    }


    private void find(View view) {
        onSale_recyclerView = view.findViewById(R.id.OnSale_recyclerView);
        whatsHot_recyclerView = view.findViewById(R.id.whatsHot_recyclerView);
        beer_recyclerView = view.findViewById(R.id.beer_recyclerView);
        liquor_recyclerView = view.findViewById(R.id.liquor_recyclerView);
        wine_recyclerView = view.findViewById(R.id.wine_recyclerView);
        search_view = view.findViewById(R.id.search_view);


    }


    private void beerApi(String typeKey, String typeID) {

        boolean networkCheck = CommonMethod.isNetworkAvailable(getContext());
        if (networkCheck) {

            Map<String, String> map = new HashMap<>();
            map.put(Common.Apikey_text, Common.Apikey_value);
            map.put(typeKey, typeID);
//            map.put("is_topten", "1");
//                map.put(typeID"skin_id", "2");

            Call<List<ResponseCategory>> call1 = apiInterface.categoryList(map);

            call1.enqueue(new Callback<List<ResponseCategory>>() {
                @Override
                public void onResponse(Call<List<ResponseCategory>> call, Response<List<ResponseCategory>> response) {
                    if (response.isSuccessful()) {

                        categoryList = response.body();
                        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList, getContext(), typeID);
                            beer_recyclerView.setHasFixedSize(true);
                            beer_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                            beer_recyclerView.setAdapter(categoryAdapter);

                    } else {
                        Toast.makeText(getContext(), "No Data found", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<List<ResponseCategory>> call, Throwable t) {
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(getContext(), "Please Check your internet.", Toast.LENGTH_SHORT).show();
        }

    }

    private void liquorApi(String typeKey, String typeID) {

        boolean networkCheck = CommonMethod.isNetworkAvailable(getContext());
        if (networkCheck) {

            Map<String, String> map = new HashMap<>();
            map.put(Common.Apikey_text, Common.Apikey_value);
            map.put(typeKey, typeID);

            Call<List<ResponseCategory>> call1 = apiInterface.categoryList(map);

            call1.enqueue(new Callback<List<ResponseCategory>>() {
                @Override
                public void onResponse(Call<List<ResponseCategory>> call, Response<List<ResponseCategory>> response) {
                    if (response.isSuccessful()) {

                        categoryList = response.body();

                        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList, getContext(), typeID);

                            liquor_recyclerView.setHasFixedSize(true);
                            liquor_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                            liquor_recyclerView.setAdapter(categoryAdapter);

                    } else {
                        Toast.makeText(getContext(), "No Data found", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<List<ResponseCategory>> call, Throwable t) {
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(getContext(), "Please Check your internet.", Toast.LENGTH_SHORT).show();
        }

    }

    private void wineApi(String typeKey, String typeID) {

        boolean networkCheck = CommonMethod.isNetworkAvailable(getContext());
        if (networkCheck) {

            Map<String, String> map = new HashMap<>();
            map.put(Common.Apikey_text, Common.Apikey_value);
            map.put(typeKey, typeID);
//            map.put("is_topten", "1");
//                map.put(typeID"skin_id", "2");

            Call<List<ResponseCategory>> call1 = apiInterface.categoryList(map);

            call1.enqueue(new Callback<List<ResponseCategory>>() {
                @Override
                public void onResponse(Call<List<ResponseCategory>> call, Response<List<ResponseCategory>> response) {
                    if (response.isSuccessful()) {

                        categoryList = response.body();

                        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList, getContext(), typeID);

                            wine_recyclerView.setHasFixedSize(true);
                            wine_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                            wine_recyclerView.setAdapter(categoryAdapter);
                    } else {
                        Toast.makeText(getContext(), "No Data found", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<List<ResponseCategory>> call, Throwable t) {
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(getContext(), "Please Check your internet.", Toast.LENGTH_SHORT).show();
        }

    }

    private void onSaleApi(String typeKey, String typeID) {

        boolean networkCheck = CommonMethod.isNetworkAvailable(getContext());
        if (networkCheck) {

            Map<String, String> map = new HashMap<>();
            map.put(Common.Apikey_text, Common.Apikey_value);
            map.put(typeKey, typeID);
            map.put("is_topten", "1");

            Call<List<ResponseHome>> call1 = apiInterface.home(map);

            call1.enqueue(new Callback<List<ResponseHome>>() {
                @Override
                public void onResponse(Call<List<ResponseHome>> call, Response<List<ResponseHome>> response) {
                    if (response.isSuccessful()) {
                        List<ResponseHome> loginResponse = response.body();


                        homeList = response.body();

                        OnSaleAdapter onSaleAdapter = new OnSaleAdapter(getContext(), homeList);

                            onSale_recyclerView.setHasFixedSize(true);
                            onSale_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                            onSale_recyclerView.setAdapter(onSaleAdapter);
//                            onSaleApi("is_onsale", "1");

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

    public void whatsHotApi(String typeKey, String typeID) {

        boolean networkCheck = CommonMethod.isNetworkAvailable(getContext());
        if (networkCheck) {

            Map<String, String> map = new HashMap<>();
            map.put(Common.Apikey_text, Common.Apikey_value);
            map.put(typeKey, typeID);
            map.put("is_topten", "1");
//                map.put(typeID"skin_id", "2");

            Call<List<ResponseHome>> call1 = apiInterface.home(map);

            call1.enqueue(new Callback<List<ResponseHome>>() {
                @Override
                public void onResponse(Call<List<ResponseHome>> call, Response<List<ResponseHome>> response) {
                    if (response.isSuccessful()) {
                        List<ResponseHome> loginResponse = response.body();

                        progressDialog.dismiss();
                        homeList = response.body();

                        WhatsHotAdapter whatsHotAdapter = new WhatsHotAdapter(getContext(), homeList);

                            whatsHot_recyclerView.setHasFixedSize(true);
                            whatsHot_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                            whatsHot_recyclerView.setAdapter(whatsHotAdapter);

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