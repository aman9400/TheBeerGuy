package com.example.thebeerguy.DashBoard.Home.SubCategory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Apis.APIClient;
import com.example.Apis.APIInterface;
import com.example.common.Common;
import com.example.common.CommonMethod;
import com.example.thebeerguy.Product_Details.productListResponse.ResponseProductList;
import com.example.thebeerguy.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubCategory extends AppCompatActivity {

    GridView sub_category_gridView;
    ImageView SubCategoryToolbar_filter;


    private androidx.appcompat.widget.Toolbar sub_category_toolbaar;
    boolean isCLicked = false;
    private List<ResponseProductList> subCategoryList = new ArrayList<>();

    APIInterface apiInterface;
    private String subCatID;
    private String typeID;
    private String name;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);


        if(getIntent().getStringExtra("name").equalsIgnoreCase("search")){
            searchApi(getIntent().getStringExtra("typeID"));
        }else {
            subCatID = getIntent().getStringExtra("subCatId");
            typeID = getIntent().getStringExtra("typeID");
            name = getIntent().getStringExtra("name");
        }



//        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#ffffff\">" + name + "</font>"));

//        getSupportActionBar().show();
//        getSupportActionBar().setTitle(name);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        sub_category_gridView = findViewById(R.id.sub_category_gridView);
        SubCategoryToolbar_filter = findViewById(R.id.SubCategoryToolbar_filter);

        sub_category_toolbaar = findViewById(R.id.Sub_category_toolbaar);
        setSupportActionBar(sub_category_toolbaar);


        apiInterface = APIClient.getClient().create(APIInterface.class);


        subCategoryAPi("type_id", typeID, subCatID);


        SubCategoryToolbar_filter.setOnClickListener(v -> {


            Dialog dialog = new Dialog(SubCategory.this);
            dialog.setContentView(R.layout.sub_category_filter);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable());
            dialog.getWindow().setGravity(Gravity.RIGHT);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

            RecyclerView type_recyclerview, category_recyclerview, subCategory_recyclerview, origin_recyclerview,
                    priceRange_recyclerview, liquorContainer_recyclerview, VAQ_recyclerview, kosher_recyclerview,
                    lcboVintage_recyclerview, hashPhoto_recycler, bonusPoint_recycler;

            TextView subCategoryFilter_TV_type, subCategoryFilter_TV_category, subCategoryFilter_TV_subCategory,
                    subCategoryFilter_TV_origin, subCategoryFilter_TV_priceRange, subCategoryFilter_TV_liquorContainer,
                    subCategoryFilter_TV_VAQ, subCategoryFilter_TV_kosher, subCategoryFilter_TV_lcboVintage,
                    subCategoryFilter_TV_hashPhoto, subCategoryFilter_TV_bonusPoint;

            Button subCategoryFilter_Btn_apply, subCategoryFilter_Btn_reset;



            type_recyclerview = dialog.findViewById(R.id.type_recyclerview);
            category_recyclerview = dialog.findViewById(R.id.category_recyclerview);
            subCategory_recyclerview = dialog.findViewById(R.id.subCategory_recyclerview);
            origin_recyclerview = dialog.findViewById(R.id.origin_recyclerview);
            priceRange_recyclerview = dialog.findViewById(R.id.priceRange_recyclerview);
            liquorContainer_recyclerview = dialog.findViewById(R.id.liquorContainer_recyclerview);
            VAQ_recyclerview = dialog.findViewById(R.id.VAQ_recyclerview);
            kosher_recyclerview = dialog.findViewById(R.id.kosher_recyclerview);
            lcboVintage_recyclerview = dialog.findViewById(R.id.lcboVintage_recyclerview);
            hashPhoto_recycler = dialog.findViewById(R.id.hashPhoto_recycler);
            bonusPoint_recycler = dialog.findViewById(R.id.bonusPoint_recycler);

            subCategoryFilter_Btn_apply = dialog.findViewById(R.id.subCategoryFilter_Btn_apply);
            subCategoryFilter_Btn_reset = dialog.findViewById(R.id.subCategoryFilter_Btn_reset);

            subCategoryFilter_TV_type = dialog.findViewById(R.id.subCategoryFilter_TV_type);
            subCategoryFilter_TV_category = dialog.findViewById(R.id.subCategoryFilter_TV_category);
            subCategoryFilter_TV_subCategory = dialog.findViewById(R.id.subCategoryFilter_TV_subCategory);
            subCategoryFilter_TV_origin = dialog.findViewById(R.id.subCategoryFilter_TV_origin);
            subCategoryFilter_TV_priceRange = dialog.findViewById(R.id.subCategoryFilter_TV_priceRange);
            subCategoryFilter_TV_liquorContainer = dialog.findViewById(R.id.subCategoryFilter_TV_liquorContainer);
            subCategoryFilter_TV_VAQ = dialog.findViewById(R.id.subCategoryFilter_TV_VAQ);
            subCategoryFilter_TV_kosher = dialog.findViewById(R.id.subCategoryFilter_TV_kosher);
            subCategoryFilter_TV_lcboVintage = dialog.findViewById(R.id.subCategoryFilter_TV_lcboVintage);
            subCategoryFilter_TV_hashPhoto = dialog.findViewById(R.id.subCategoryFilter_TV_hashPhoto);
            subCategoryFilter_TV_bonusPoint = dialog.findViewById(R.id.subCategoryFilter_TV_bonusPoint);

            subCategoryFilter_TV_type.setOnClickListener(v1 -> {
                if (isCLicked) {
                    type_recyclerview.setVisibility(View.VISIBLE);

                } else {
                    type_recyclerview.setVisibility(View.GONE);
                }
            });

            subCategoryFilter_TV_category.setOnClickListener(v13 -> {

                if (isCLicked) {
                    category_recyclerview.setVisibility(View.VISIBLE);

                } else {
                    category_recyclerview.setVisibility(View.GONE);
                }
            });

            subCategoryFilter_TV_subCategory.setOnClickListener(v12 -> {

                if (isCLicked) {
                    subCategory_recyclerview.setVisibility(View.VISIBLE);

                } else {
                    subCategory_recyclerview.setVisibility(View.GONE);
                }
            });

            subCategoryFilter_TV_origin.setOnClickListener(v14 -> {

                if (isCLicked) {
                    origin_recyclerview.setVisibility(View.VISIBLE);

                } else {
                    origin_recyclerview.setVisibility(View.GONE);
                }

            });

            subCategoryFilter_TV_priceRange.setOnClickListener(v15 -> {
                if (isCLicked ) {
                    priceRange_recyclerview.setVisibility(View.VISIBLE);

                } else {
                    priceRange_recyclerview.setVisibility(View.GONE);
                }
            });

            subCategoryFilter_TV_liquorContainer.setOnClickListener(v16 -> {

                if (isCLicked) {
                    liquorContainer_recyclerview.setVisibility(View.VISIBLE);

                } else {
                    liquorContainer_recyclerview.setVisibility(View.GONE);
                }
            });

            subCategoryFilter_TV_VAQ.setOnClickListener(v17 -> {
                if (isCLicked) {
                    VAQ_recyclerview.setVisibility(View.VISIBLE);

                } else {
                    VAQ_recyclerview.setVisibility(View.GONE);
                }
            });

            subCategoryFilter_TV_kosher.setOnClickListener(v18 -> {
                if (isCLicked) {
                    kosher_recyclerview.setVisibility(View.VISIBLE);

                } else {
                    kosher_recyclerview.setVisibility(View.GONE);
                }
            });

            subCategoryFilter_TV_lcboVintage.setOnClickListener(v19 -> {

                if (isCLicked) {
                    lcboVintage_recyclerview.setVisibility(View.VISIBLE);

                } else {
                    lcboVintage_recyclerview.setVisibility(View.GONE);
                }
            });

            subCategoryFilter_TV_hashPhoto.setOnClickListener(v110 -> {

                if (isCLicked) {
                    hashPhoto_recycler.setVisibility(View.VISIBLE);

                } else {
                    hashPhoto_recycler.setVisibility(View.GONE);
                }
            });

            subCategoryFilter_TV_bonusPoint.setOnClickListener(v111 -> {
                if (isCLicked) {

                    bonusPoint_recycler.setVisibility(View.VISIBLE);

                } else {

                    bonusPoint_recycler.setVisibility(View.GONE);
                }
            });

            subCategoryFilter_Btn_apply.setOnClickListener(view ->{
                dialog.cancel();
            });

            subCategoryFilter_Btn_reset.setOnClickListener(view -> {
                dialog.cancel();
            });

            dialog.show();

        });


    }

    private void subCategoryAPi(String typeKey, String typeID, String categoryID) {

        boolean networkCheck = CommonMethod.isNetworkAvailable(this);
        if (networkCheck) {

            Map<String, String> map = new HashMap<>();
            map.put(Common.Apikey_text, Common.Apikey_value);
            map.put(typeKey, typeID);
            map.put("category_id", categoryID);
            map.put("is_topten", "1");

            Call<List<ResponseProductList>> call1 = apiInterface.productList(map);

            call1.enqueue(new Callback<List<ResponseProductList>>() {
                @Override
                public void onResponse(Call<List<ResponseProductList>> call, Response<List<ResponseProductList>> response) {
                    if (response.isSuccessful()) {
                        List<ResponseProductList> loginResponse = response.body();
//                            Common.jwt = loginResponse.getJwt();


                        subCategoryList = response.body();
                        SubCategoryAdapter subCategoryAdapter = new SubCategoryAdapter(subCategoryList, SubCategory.this,
                                typeID, categoryID);
                        sub_category_gridView.setAdapter(subCategoryAdapter);


//                            Toast.makeText(getContext(), "Beer list", Toast.LENGTH_SHORT).show();

//                            startActivity(new Intent(getContext(), DashBoard.class));
                    } else {
                        Toast.makeText(SubCategory.this, "No Data found", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<List<ResponseProductList>> call, Throwable t) {
                    Toast.makeText(SubCategory.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(SubCategory.this, "Please Check your internet.", Toast.LENGTH_SHORT).show();
        }

    }

    private void searchApi(String value) {

        boolean networkCheck = CommonMethod.isNetworkAvailable(this);
        if (networkCheck) {

            Map<String, String> map = new HashMap<>();
            map.put(Common.Apikey_text, Common.Apikey_value);
            map.put("keywords", value);
            map.put("is_topten", "1");

            Call<List<ResponseProductList>> call12 = apiInterface.searchApiList(map);
//            Log.e("test", ""+call1 );

            call12.enqueue(new Callback<List<ResponseProductList>>() {
                @Override
                public void onResponse(Call<List<ResponseProductList>> call, Response<List<ResponseProductList>> response) {
                    if (response.isSuccessful()) {
                        List<ResponseProductList> loginResponse = response.body();

                        subCategoryList = response.body();
                        SubCategoryAdapter subCategoryAdapter = new SubCategoryAdapter(subCategoryList, SubCategory.this,
                                subCategoryList.get(0).getType(), subCategoryList.get(0).getCategory());
                        sub_category_gridView.setAdapter(subCategoryAdapter);

                    } else {
                        Toast.makeText(SubCategory.this, "No Data found", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<List<ResponseProductList>> call, Throwable t) {
                    Toast.makeText(SubCategory.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(SubCategory.this, "Please Check your internet.", Toast.LENGTH_SHORT).show();
        }

    }
}

