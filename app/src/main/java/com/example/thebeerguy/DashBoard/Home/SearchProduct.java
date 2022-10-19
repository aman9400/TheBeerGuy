package com.example.thebeerguy.DashBoard.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.Apis.APIClient;
import com.example.Apis.APIInterface;
import com.example.common.Common;
import com.example.common.CommonMethod;
import com.example.thebeerguy.DashBoard.Home.ResponseSearch.ResponseSearch;
import com.example.thebeerguy.Product_Details.productListResponse.ResponseProductList;
import com.example.thebeerguy.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchProduct extends AppCompatActivity {

    SearchView search_searchView;

    GridView search_gridView;

    ImageButton searchProduct_ImV_backbtn;

    private String subCatID;
    private String typeID;
    private String name;
    private String s;

    private List<ResponseProductList> searchList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);
        getSupportActionBar().hide();



        search_gridView = findViewById(R.id.search_gridView);
        search_searchView = findViewById(R.id.search_searchView);
        searchProduct_ImV_backbtn = findViewById(R.id.searchProduct_ImV_backbtn);

        searchProduct_ImV_backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SearchProduct.super.onBackPressed();
            }
        });


        subCatID = getIntent().getStringExtra("subCatId");
        typeID = getIntent().getStringExtra("typeID");
        name = getIntent().getStringExtra("name");
        s = getIntent().getStringExtra("keyword");


        search_gridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchApi(s);
            }
        });


    }

    private void searchApi(String value) {

        boolean networkCheck = CommonMethod.isNetworkAvailable(this);
        if (networkCheck) {

            Map<String, String> map1 = new HashMap<>();
            map1.put(Common.Apikey_text, Common.Apikey_value);
            map1.put(s , value);
            map1.put("limit", "10");

            Call<ResponseSearch> call12 = APIClient.getClient().create(APIInterface.class).searchApiList(map1);
            Log.e("test", ""+call12 );

            call12.enqueue(new Callback<ResponseSearch>() {
                @Override
                public void onResponse(@NotNull Call<ResponseSearch> call, @NotNull Response<ResponseSearch> response) {
                    if (response.isSuccessful()) {
                        ResponseSearch searchResponse = response.body();

//                        subCategoryList = response.body();
                        SearchAdapter searchAdapter = new SearchAdapter( searchList, SearchProduct.this, searchResponse.getResults().get(0).getType(),  searchResponse.getResults().get(0).getCategory(),
                                searchResponse.getResults(), "search");
                        search_gridView.setAdapter(searchAdapter);

                    } else {
                        Toast.makeText(SearchProduct.this, "No Data found", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<ResponseSearch> call, Throwable t) {
                    Toast.makeText(SearchProduct.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(SearchProduct.this, "Please Check your internet.", Toast.LENGTH_SHORT).show();
        }

    }
}