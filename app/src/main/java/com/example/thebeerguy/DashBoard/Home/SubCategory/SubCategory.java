package com.example.thebeerguy.DashBoard.Home.SubCategory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
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


        Window window = getWindow();

        window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        apiInterface = APIClient.getClient().create(APIInterface.class);

        if(getIntent().getStringExtra("name").equalsIgnoreCase("search")){
            searchApi(getIntent().getStringExtra("keyword"));
        }else {
            subCatID = getIntent().getStringExtra("subCatId");
            typeID = getIntent().getStringExtra("typeID");
            name = getIntent().getStringExtra("name");

            subCategoryAPi("type_id", typeID, subCatID);
        }

        sub_category_gridView = findViewById(R.id.sub_category_gridView);
        SubCategoryToolbar_filter = findViewById(R.id.SubCategoryToolbar_filter);

        sub_category_toolbaar = findViewById(R.id.Sub_category_toolbaar);
        setSupportActionBar(sub_category_toolbaar);

        SubCategoryToolbar_filter.setOnClickListener(v -> {


            clckShowDialog();

        });


    }

    private void clckShowDialog() {
        Dialog dialog = new Dialog(SubCategory.this);
        dialog.setContentView(R.layout.sub_category_filter);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        dialog.getWindow().setGravity(Gravity.RIGHT);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        RecyclerView type_recyclerview = null, category_recyclerview, subCategory_recyclerview, origin_recyclerview,
                priceRange_recyclerview, liquorContainer_recyclerview;

        TextView subCategoryFilter_TV_type, subCategoryFilter_TV_category, subCategoryFilter_TV_subCategory,
                subCategoryFilter_TV_origin, subCategoryFilter_TV_priceRange, subCategoryFilter_TV_liquorContainer,
                subCategoryFilter_TV_VAQ, subCategoryFilter_TV_kosher, subCategoryFilter_TV_lcboVintage,
                subCategoryFilter_TV_hashPhoto, subCategoryFilter_TV_bonusPoint;

        Button subCategoryFilter_Btn_apply, subCategoryFilter_Btn_reset;


        category_recyclerview = dialog.findViewById(R.id.category_recyclerview);
        subCategory_recyclerview = dialog.findViewById(R.id.subCategory_recyclerview);
        origin_recyclerview = dialog.findViewById(R.id.origin_recyclerview);
        priceRange_recyclerview = dialog.findViewById(R.id.priceRange_recyclerview);
        liquorContainer_recyclerview = dialog.findViewById(R.id.liquorContainer_recyclerview);

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

        });

        subCategoryFilter_TV_kosher.setOnClickListener(v18 -> {

        });

        subCategoryFilter_TV_lcboVintage.setOnClickListener(v19 -> {

        });

        subCategoryFilter_TV_hashPhoto.setOnClickListener(v110 -> {

        });

        subCategoryFilter_TV_bonusPoint.setOnClickListener(v111 -> {

        });

        subCategoryFilter_Btn_apply.setOnClickListener(view ->{
            dialog.cancel();
        });

        subCategoryFilter_Btn_reset.setOnClickListener(view -> {
            dialog.cancel();
        });

        dialog.show();
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
                        List<ResponseProductList> productListResponse = response.body();

                        subCategoryList = response.body();
                        SubCategoryAdapter subCategoryAdapter = new SubCategoryAdapter(subCategoryList, SubCategory.this,
                                typeID, categoryID, "normal");
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

    private void searchApi(String value) {

        boolean networkCheck = CommonMethod.isNetworkAvailable(this);
        if (networkCheck) {

            Map<String, String> map1 = new HashMap<>();
            map1.put(Common.Apikey_text, Common.Apikey_value);
            map1.put("keywords", value);
            map1.put("limit", "10");

            Call<ResponseSearch> call12 = APIClient.getClient().create(APIInterface.class).searchApiList(map1);
            Log.e("test", ""+call12 );

            call12.enqueue(new Callback<ResponseSearch>() {
                @Override
                public void onResponse(@NotNull Call<ResponseSearch> call, @NotNull Response<ResponseSearch> response) {
                    if (response.isSuccessful()) {
                        ResponseSearch searchResponse = response.body();

//                        subCategoryList = response.body();
                        SubCategoryAdapter subCategoryAdapter = new SubCategoryAdapter( SubCategory.this,
                                searchResponse.getResults().get(0).getType(),  searchResponse.getResults().get(0).getCategory(),
                                searchResponse.getResults(), "search");
                        sub_category_gridView.setAdapter(subCategoryAdapter);

                    } else {
                        Toast.makeText(SubCategory.this, "No Data found", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<ResponseSearch> call, Throwable t) {
                    Toast.makeText(SubCategory.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(SubCategory.this, "Please Check your internet.", Toast.LENGTH_SHORT).show();
        }

    }

 /*   private void filterApi(String value) {

        boolean networkCheck = CommonMethod.isNetworkAvailable(this);
        if (networkCheck) {

            Map<String, String> map = new HashMap<>();
            map.put(Common.Apikey_text, Common.Apikey_value);
            map.put("keywords", value);
            map.put("type", "1");
            map.put("category", "Ale");
            map.put("sub_category", "Pale");
            map.put("origin", "Canada");
            map.put("region", "Canada");
            map.put("price_range", "");
            map.put("beer_container", "");
            map.put("wine_container", "");
            map.put("beer_quantity", "");
            map.put("alcohol_content", "5.00%");
            map.put("is_kosher", "");
            map.put("is_vqa", "0");
            map.put("is_vintages", "0");
            map.put("has_photos", "0");

            Call<ResponseSearch> call12 = apiInterface.searchApiList(map);
//            Log.e("test", ""+call1 );
//
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

    }*/
}

