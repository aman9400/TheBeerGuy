package com.example.thebeerguy.DashBoard.Home.SubCategory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Apis.APIClient;
import com.example.Apis.APIInterface;
import com.example.common.Common;
import com.example.common.CommonMethod;
import com.example.thebeerguy.DashBoard.Home.ResponseSearch.ResponseSearch;
import com.example.thebeerguy.DashBoard.Home.SubCategory.FilterAdapters.FilterCatagoryAdapter;
import com.example.thebeerguy.DashBoard.Home.SubCategory.FilterAdapters.FilterPriceRangeAdapter;
import com.example.thebeerguy.DashBoard.Home.SubCategory.FilterAdapters.FitlerOrginAdapter;
import com.example.thebeerguy.DashBoard.Home.SubCategory.FilterResponse.Option;
import com.example.thebeerguy.DashBoard.Home.SubCategory.FilterResponse.ResponseFilter;
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
    RecyclerView type_recyclerview, category_recyclerview, subCategory_recyclerview, origin_recyclerview,
            priceRange_recyclerview, liquorContainer_recyclerview;

    private androidx.appcompat.widget.Toolbar sub_category_toolbaar;
    boolean isCLicked_type = false, isCLicked_category = false, isCLicked_subCategory = false, isCLicked_origin = false,
            isCLicked_priceRange = false, isCLicked_liquorContainer= false, isCLicked_VAQ = false, isCLicked_kosher = false,
            isCLicked_lcboVintage = false, isCLicked_hashPhoto = false, isCLicked_bonusPoint = false;

    private List<ResponseProductList> subCategoryList = new ArrayList<>();
    private List<ResponseFilter> filter_cat = new ArrayList<>();

    APIInterface apiInterface;
    private String subCatID;
    private String typeID;
    private String name;
    Option optionOrigin, optionPriceRange;

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


        filterApi("");  // calling api


        SubCategoryToolbar_filter.setOnClickListener(v -> {

            Dialog dialog = new Dialog(SubCategory.this);
            dialog.setContentView(R.layout.sub_category_filter);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable());
            dialog.getWindow().setGravity(Gravity.RIGHT);
//            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(true);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);


            dialogList(dialog);  // manage dialog list

            dialog.show();

        });


    }

    private void dialogList(Dialog dialog) {


        ImageView subCategoryFilter_ImV_type, subCategoryFilter_ImV_category, subCategoryFilter_ImV_subCategory,
                subCategoryFilter_ImV_origin, subCategoryFilter_ImV_priceRange, subCategoryFilter_ImV_liquorContainer,
                subCategoryFilter_ImV_VAQ, subCategoryFilter_ImV_kosher, subCategoryFilter_ImV_lcboVintage,
                subCategoryFilter_ImV_hashPhoto, subCategoryFilter_ImV_bonusPoint;

        TextView subCategoryFilter_TV_dropDown;

        ConstraintLayout subCategoryFilter_CL_type, subCategoryFilter_CL_category, subCategoryFilter_CL_subCategory,
                subCategoryFilter_CL_origin, subCategoryFilter_CL_priceRange, subCategoryFilter_CL_liquorContainer,
                subCategoryFilter_CL_VAQ, subCategoryFilter_CL_kosher, subCategoryFilter_CL_lcboVintage,
                subCategoryFilter_CL_hashPhoto, subCategoryFilter_CL_bonusPoint;

        ConstraintLayout  subCategoryFilter_CL_type_option, subCat_filter_CL_beer, subCat_filter_CL_wine, subCat_filter_CL_liquor,
                subCategoryFilter_CL_VAQ_Option, subCategoryFilter_CL_kosherOption, subCategoryFilter_CL_lcboVintage_Option,
                subCategoryFilter_CL_hashPhoto_Option, subCategoryFilter_CL_bonusPoint_Option;

        subCat_filter_CL_beer = dialog.findViewById(R.id.subCat_filter_CL_beer);
        subCat_filter_CL_wine = dialog.findViewById(R.id.subCat_filter_CL_wine);
        subCat_filter_CL_liquor = dialog.findViewById(R.id.subCat_filter_CL_liquor);
        subCategoryFilter_CL_VAQ_Option = dialog.findViewById(R.id.subCategoryFilter_CL_VAQ_Option);
        subCategoryFilter_CL_kosherOption = dialog.findViewById(R.id.subCategoryFilter_CL_kosherOption);
        subCategoryFilter_CL_lcboVintage_Option = dialog.findViewById(R.id.subCategoryFilter_CL_lcboVintage_Option);
        subCategoryFilter_CL_hashPhoto_Option = dialog.findViewById(R.id.subCategoryFilter_CL_hashPhoto_Option);
        subCategoryFilter_CL_bonusPoint_Option = dialog.findViewById(R.id.subCategoryFilter_CL_bonusPoint_Option);
        subCategoryFilter_CL_type_option = dialog.findViewById(R.id.subCategoryFilter_CL_type_option);

        Button subCategoryFilter_Btn_apply, subCategoryFilter_Btn_reset;


        subCategoryFilter_TV_dropDown = dialog.findViewById(R.id.subCategoryFilter_TV_dropDown); // Drop Down Dialog List
        category_recyclerview = dialog.findViewById(R.id.category_recyclerview);
        subCategory_recyclerview = dialog.findViewById(R.id.subCategory_recyclerview);
        origin_recyclerview = dialog.findViewById(R.id.origin_recyclerview);
        priceRange_recyclerview = dialog.findViewById(R.id.priceRange_recyclerview);
        liquorContainer_recyclerview = dialog.findViewById(R.id.liquorContainer_recyclerview);

        subCategoryFilter_Btn_apply = dialog.findViewById(R.id.subCategoryFilter_Btn_apply);
        subCategoryFilter_Btn_reset = dialog.findViewById(R.id.subCategoryFilter_Btn_reset);

        subCategoryFilter_ImV_type = dialog.findViewById(R.id.subCategoryFilter_ImV_type);
        subCategoryFilter_ImV_category = dialog.findViewById(R.id.subCategoryFilter_ImV_category);
        subCategoryFilter_ImV_subCategory = dialog.findViewById(R.id.subCategoryFilter_ImV_subCategory);
        subCategoryFilter_ImV_origin = dialog.findViewById(R.id.subCategoryFilter_ImV_origin);
        subCategoryFilter_ImV_priceRange = dialog.findViewById(R.id.subCategoryFilter_ImV_priceRange);
        subCategoryFilter_ImV_liquorContainer = dialog.findViewById(R.id.subCategoryFilter_ImV_liquorContainer);
        subCategoryFilter_ImV_VAQ = dialog.findViewById(R.id.subCategoryFilter_ImV_VAQ);
        subCategoryFilter_ImV_kosher = dialog.findViewById(R.id.subCategoryFilter_ImV_kosher);
        subCategoryFilter_ImV_lcboVintage = dialog.findViewById(R.id.subCategoryFilter_ImV_lcboVintage);
        subCategoryFilter_ImV_hashPhoto = dialog.findViewById(R.id.subCategoryFilter_ImV_hashPhoto);
        subCategoryFilter_ImV_bonusPoint = dialog.findViewById(R.id.subCategoryFilter_ImV_bonusPoint);

        subCategoryFilter_CL_type = dialog.findViewById(R.id.subCategoryFilter_CL_type);
        subCategoryFilter_CL_category = dialog.findViewById(R.id.subCategoryFilter_CL_category);
        subCategoryFilter_CL_subCategory = dialog.findViewById(R.id.subCategoryFilter_CL_subCategory);
        subCategoryFilter_CL_origin = dialog.findViewById(R.id.subCategoryFilter_CL_origin);
        subCategoryFilter_CL_priceRange = dialog.findViewById(R.id.subCategoryFilter_CL_priceRange);
        subCategoryFilter_CL_liquorContainer = dialog.findViewById(R.id.subCategoryFilter_CL_liquorContainer);
        subCategoryFilter_CL_VAQ = dialog.findViewById(R.id.subCategoryFilter_CL_VAQ);
        subCategoryFilter_CL_kosher = dialog.findViewById(R.id.subCategoryFilter_CL_kosher);
        subCategoryFilter_CL_lcboVintage = dialog.findViewById(R.id.subCategoryFilter_CL_lcboVintage);
        subCategoryFilter_CL_hashPhoto = dialog.findViewById(R.id.subCategoryFilter_CL_hashPhoto);
        subCategoryFilter_CL_bonusPoint = dialog.findViewById(R.id.subCategoryFilter_CL_bonusPoint);


        FitlerOrginAdapter filterCatagoryAdapter = new FitlerOrginAdapter(SubCategory.this, optionOrigin);
        origin_recyclerview.setHasFixedSize(true);
        origin_recyclerview.setLayoutManager(new LinearLayoutManager(SubCategory.this, LinearLayoutManager.VERTICAL, false));
        origin_recyclerview.setAdapter(filterCatagoryAdapter);

        FilterPriceRangeAdapter filterPriceRangeAdapter = new FilterPriceRangeAdapter(SubCategory.this, optionPriceRange);
        priceRange_recyclerview.setHasFixedSize(true);
        priceRange_recyclerview.setLayoutManager(new LinearLayoutManager(SubCategory.this, LinearLayoutManager.VERTICAL, false));
        priceRange_recyclerview.setAdapter(filterPriceRangeAdapter);



        subCategoryFilter_TV_dropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                filterDropdownList(); // manage Drop Down List
            }
        });


        subCategoryFilter_CL_type.setOnClickListener(v1 -> {

            subCatClicked(subCategoryFilter_ImV_type, subCategoryFilter_CL_type_option);

        });

        subCategoryFilter_CL_category.setOnClickListener(v13 -> {

            subCatCategory(category_recyclerview, subCategoryFilter_ImV_category);
        });

        subCategoryFilter_CL_subCategory.setOnClickListener(v12 -> {

            subCatSubCat(subCategory_recyclerview, subCategoryFilter_ImV_subCategory);
        });

        subCategoryFilter_CL_origin.setOnClickListener(v14 -> {

            subCatOrigin(origin_recyclerview, subCategoryFilter_ImV_origin);

        });

        subCategoryFilter_CL_priceRange.setOnClickListener(v15 -> {
            subCatPriceRange(priceRange_recyclerview, subCategoryFilter_ImV_priceRange);
        });

        subCategoryFilter_CL_liquorContainer.setOnClickListener(v16 -> {

            subCatLiquor(liquorContainer_recyclerview, subCategoryFilter_ImV_liquorContainer);
        });

        subCategoryFilter_CL_VAQ.setOnClickListener(v17 -> {

            subCatVAQ(subCategoryFilter_ImV_VAQ, subCategoryFilter_CL_VAQ_Option);

        });

        subCategoryFilter_CL_kosher.setOnClickListener(v18 -> {

            subCatKoshar(subCategoryFilter_ImV_kosher, subCategoryFilter_CL_kosherOption);

        });

        subCategoryFilter_CL_lcboVintage.setOnClickListener(v19 -> {

            subCatVintage(subCategoryFilter_ImV_lcboVintage, subCategoryFilter_CL_lcboVintage_Option);

        });

        subCategoryFilter_CL_hashPhoto.setOnClickListener(v110 -> {

            subCatHasPhoto(subCategoryFilter_ImV_hashPhoto, subCategoryFilter_CL_hashPhoto_Option);


        });

        subCategoryFilter_CL_bonusPoint.setOnClickListener(v111 -> {

            subCatBonus(subCategoryFilter_ImV_bonusPoint, subCategoryFilter_CL_bonusPoint_Option);

        });

        subCategoryFilter_Btn_apply.setOnClickListener(view ->{
            dialog.cancel();
        });

        subCategoryFilter_Btn_reset.setOnClickListener(view -> {
            dialog.cancel();
        });

    }

    private void filterDropdownList() {

        Dialog dialog = new Dialog(SubCategory.this);
        dialog.setContentView(R.layout.product_down_dailog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        dialog.getWindow().setGravity(Gravity.RIGHT);
//        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);


        ConstraintLayout FilterDropDown_CL_selling, FilterDropDown_CL_ratting, FilterDropDown_CL_relavance,
                FilterDropDown_CL_lowToHigh, FilterDropDown_CL_highToLow, FilterDropDown_CL_nameAZ,
                FilterDropDown_CL_nameZA, FilterDropDown_CL_newest;

        FilterDropDown_CL_selling = dialog.findViewById(R.id.FilterDropDown_CL_selling);
        FilterDropDown_CL_ratting = dialog.findViewById(R.id.FilterDropDown_CL_ratting);
        FilterDropDown_CL_relavance = dialog.findViewById(R.id.FilterDropDown_CL_relavance);
        FilterDropDown_CL_lowToHigh = dialog.findViewById(R.id.FilterDropDown_CL_lowToHigh);
        FilterDropDown_CL_highToLow = dialog.findViewById(R.id.FilterDropDown_CL_highToLow);
        FilterDropDown_CL_nameAZ = dialog.findViewById(R.id.FilterDropDown_CL_nameAZ);
        FilterDropDown_CL_nameZA = dialog.findViewById(R.id.FilterDropDown_CL_nameZA);
        FilterDropDown_CL_newest = dialog.findViewById(R.id.FilterDropDown_CL_newest);

        dialog.show();







    }

    private void subCatBonus(ImageView subCategoryFilter_ImV_bonusPoint, ConstraintLayout subCategoryFilter_CL_bonusPoint_Option) {
        if (isCLicked_bonusPoint){
            isCLicked_bonusPoint = false;
            subCategoryFilter_CL_bonusPoint_Option.setVisibility(View.VISIBLE);
            subCategoryFilter_ImV_bonusPoint.setImageResource(R.drawable.ic_arrow_downward_24);

        }else{

            isCLicked_bonusPoint = true;
            subCategoryFilter_CL_bonusPoint_Option.setVisibility(View.GONE);
            subCategoryFilter_ImV_bonusPoint.setImageResource(R.drawable.ic_arrow_forward_24);
        }
    }

    private void subCatHasPhoto(ImageView subCategoryFilter_ImV_hashPhoto, ConstraintLayout subCategoryFilter_CL_hashPhoto_Option) {
        if (isCLicked_hashPhoto){
            isCLicked_hashPhoto = false;
            subCategoryFilter_CL_hashPhoto_Option.setVisibility(View.VISIBLE);
            subCategoryFilter_ImV_hashPhoto.setImageResource(R.drawable.ic_arrow_downward_24);

        }else {
            isCLicked_hashPhoto = true;
            subCategoryFilter_CL_hashPhoto_Option.setVisibility(View.GONE);
            subCategoryFilter_ImV_hashPhoto.setImageResource(R.drawable.ic_arrow_forward_24);
        }
    }

    private void subCatVintage(ImageView subCategoryFilter_ImV_lcboVintage, ConstraintLayout subCategoryFilter_CL_lcboVintage_Option) {
        if (isCLicked_lcboVintage){

            isCLicked_lcboVintage = false;
            subCategoryFilter_CL_lcboVintage_Option.setVisibility(View.VISIBLE);
            subCategoryFilter_ImV_lcboVintage.setImageResource(R.drawable.ic_arrow_downward_24);


        }else {

            isCLicked_lcboVintage = true;
            subCategoryFilter_CL_lcboVintage_Option.setVisibility(View.GONE);
            subCategoryFilter_ImV_lcboVintage.setImageResource(R.drawable.ic_arrow_forward_24);

        }
    }

    private void subCatKoshar(ImageView subCategoryFilter_ImV_kosher, ConstraintLayout subCategoryFilter_CL_kosherOption) {
        if (isCLicked_kosher){

            isCLicked_kosher = false;
            subCategoryFilter_CL_kosherOption.setVisibility(View.VISIBLE);
            subCategoryFilter_ImV_kosher.setImageResource(R.drawable.ic_arrow_downward_24);

        }else {
            isCLicked_kosher = true;
            subCategoryFilter_CL_kosherOption.setVisibility(View.GONE);
            subCategoryFilter_ImV_kosher.setImageResource(R.drawable.ic_arrow_forward_24);

        }
    }

    private void subCatVAQ(ImageView subCategoryFilter_ImV_VAQ, ConstraintLayout subCategoryFilter_CL_VAQ_Option) {
        if (isCLicked_VAQ){
            isCLicked_VAQ = false;
            subCategoryFilter_CL_VAQ_Option.setVisibility(View.VISIBLE);
            subCategoryFilter_ImV_VAQ.setImageResource(R.drawable.ic_arrow_downward_24);

        }else{

            isCLicked_VAQ = true;
            subCategoryFilter_CL_VAQ_Option.setVisibility(View.GONE);
            subCategoryFilter_ImV_VAQ.setImageResource(R.drawable.ic_arrow_forward_24);
        }
    }

    private void subCatLiquor(RecyclerView liquorContainer_recyclerview, ImageView subCategoryFilter_ImV_liquorContainer) {
        if (isCLicked_liquorContainer) {

            isCLicked_liquorContainer = false;

            liquorContainer_recyclerview.setVisibility(View.VISIBLE);
            subCategoryFilter_ImV_liquorContainer.setImageResource(R.drawable.ic_arrow_downward_24);

        } else {

            isCLicked_liquorContainer = true;

            liquorContainer_recyclerview.setVisibility(View.GONE);
            subCategoryFilter_ImV_liquorContainer.setImageResource(R.drawable.ic_arrow_forward_24);
        }
    }

    private void subCatPriceRange(RecyclerView priceRange_recyclerview, ImageView subCategoryFilter_ImV_priceRange) {
        if (isCLicked_priceRange ) {

            isCLicked_priceRange = false;

            priceRange_recyclerview.setVisibility(View.VISIBLE);
            subCategoryFilter_ImV_priceRange.setImageResource(R.drawable.ic_arrow_downward_24);

        } else {

            isCLicked_priceRange = true;

            priceRange_recyclerview.setVisibility(View.GONE);
            subCategoryFilter_ImV_priceRange.setImageResource(R.drawable.ic_arrow_forward_24);
        }
    }

    private void subCatOrigin(RecyclerView origin_recyclerview, ImageView subCategoryFilter_ImV_origin) {
        if (isCLicked_origin) {

            isCLicked_origin = false;

            origin_recyclerview.setVisibility(View.VISIBLE);
            subCategoryFilter_ImV_origin.setImageResource(R.drawable.ic_arrow_downward_24);


        } else {

            isCLicked_origin = true;

            origin_recyclerview.setVisibility(View.GONE);
            subCategoryFilter_ImV_origin.setImageResource(R.drawable.ic_arrow_forward_24);
        }
    }

    private void subCatSubCat(RecyclerView subCategory_recyclerview, ImageView subCategoryFilter_ImV_subCategory) {
        if (isCLicked_subCategory) {

            isCLicked_subCategory = false;

            subCategory_recyclerview.setVisibility(View.VISIBLE);
            subCategoryFilter_ImV_subCategory.setImageResource(R.drawable.ic_arrow_downward_24);

        } else {

            isCLicked_subCategory = true;

            subCategory_recyclerview.setVisibility(View.GONE);
            subCategoryFilter_ImV_subCategory.setImageResource(R.drawable.ic_arrow_forward_24);
        }
    }

    private void subCatCategory(RecyclerView category_recyclerview, ImageView subCategoryFilter_ImV_category) {
        if (isCLicked_category) {

            isCLicked_category = false;

            category_recyclerview.setVisibility(View.VISIBLE);

            subCategoryFilter_ImV_category.setImageResource(R.drawable.ic_arrow_downward_24);

        } else {

            isCLicked_category = true;

            category_recyclerview.setVisibility(View.GONE);

            subCategoryFilter_ImV_category.setImageResource(R.drawable.ic_arrow_forward_24);
        }
    }

    private void subCatClicked(ImageView subCategoryFilter_ImV_type, ConstraintLayout subCategoryFilter_CL_type_option) {
        if (isCLicked_type) {

            isCLicked_type = false;

            subCategoryFilter_ImV_type.setImageResource(R.drawable.ic_arrow_downward_24);

            subCategoryFilter_CL_type_option.setVisibility(View.VISIBLE);

        } else {

            isCLicked_type = true;

            subCategoryFilter_ImV_type.setImageResource(R.drawable.ic_arrow_forward_24);
            subCategoryFilter_CL_type_option.setVisibility(View.GONE);
        }
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

    private void filterApi(String value) {

        boolean networkCheck = CommonMethod.isNetworkAvailable(this);
        if (networkCheck) {

            Map<String, String> map = new HashMap<>();
            map.put(Common.Apikey_text, Common.Apikey_value);
//            map.put("keywords", value);
//            map.put("type", "1");
//            map.put("category", "Ale");
//            map.put("sub_category", "Pale");
//            map.put("origin", "Canada");
//            map.put("region", "Canada");
//            map.put("price_range", "");
//            map.put("beer_container", "");
//            map.put("wine_container", "");
//            map.put("beer_quantity", "");
//            map.put("alcohol_content", "5.00%");
//            map.put("is_kosher", "");
//            map.put("is_vqa", "0");
//            map.put("is_vintages", "0");
//            map.put("has_photos", "0");

            Call<ResponseFilter> call12 = apiInterface.filter(map);
//            Log.e("test", ""+call1 );
//
            call12.enqueue(new Callback<ResponseFilter>() {
                @Override
                public void onResponse(Call<ResponseFilter> call, Response<ResponseFilter> response) {
                    if (response.isSuccessful()) {
                        ResponseFilter filterResponse = response.body();

//                        filter_cat = response.body();
                        optionOrigin = filterResponse.getOutput().getOptions().get(1);
                        optionPriceRange = filterResponse.getOutput().getOptions().get(2);
//                        optionVAQ = filterResponse.getOutput().getOptions().get(2);
//                        optionPriceRange = filterResponse.getOutput().getOptions().get(2);
//                        optionPriceRange = filterResponse.getOutput().getOptions().get(2);
//                        optionPriceRange = filterResponse.getOutput().getOptions().get(2);
//                        optionPriceRange = filterResponse.getOutput().getOptions().get(2);


                    } else {
                        Toast.makeText(SubCategory.this, "No Data found", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<ResponseFilter> call, Throwable t) {
                    Toast.makeText(SubCategory.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(SubCategory.this, "Please Check your internet.", Toast.LENGTH_SHORT).show();
        }

    }
}

