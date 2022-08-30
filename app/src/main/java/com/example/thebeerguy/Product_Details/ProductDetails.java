package com.example.thebeerguy.Product_Details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Apis.APIClient;
import com.example.Apis.APIInterface;
import com.example.Signup.SignUp;
import com.example.common.Common;
import com.example.common.CommonMethod;
import com.example.login.Login;
import com.example.login.responseLogin.LoginResponse;
import com.example.thebeerguy.DashBoard.Home.Adapters.GridAdapter;
import com.example.thebeerguy.DashBoard.ResponseJson.homeResponse.ResponseHome;
import com.example.thebeerguy.DashBoard.ReviewCart;
import com.example.thebeerguy.Intro.Splash4;
import com.example.thebeerguy.Product_Details.AddToCartResponse.ResponseAddToCart;
import com.example.thebeerguy.Product_Details.ProductDetailsResponse.Package;
import com.example.thebeerguy.Product_Details.ProductDetailsResponse.ResponseProductDetail;
import com.example.thebeerguy.R;
import com.example.thebeerguy.databinding.ProductDetailDialogRecyclerBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetails extends AppCompatActivity {

    GridView productDetail_grid;
    private List<ResponseHome> list = new ArrayList<>();
    private TextView product_arrow_down, product_TV_price, product_TV_name,
            product_TV_ratting, product_TV_time, product_TV_rating2,
            product_TV_discription, product_brewer, product_alcohol;

    private Button productDetail_btn_addToCard;

    private List<ResponseHome> productDetailsList = new ArrayList<>();

    private List<Package> pakageList = new ArrayList<>();



    private ImageView product_IV_image;
    private String productID;
    private String type_id;
    private String cat_id;

    APIInterface apiInterface;
    private ProgressDialog progressDialog;

    String Address = Splash4.Address;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        findIds();



        apiInterface = APIClient.getClient().create(APIInterface.class);



        productID = getIntent().getStringExtra("productID");
        String name = getIntent().getStringExtra("name");
        type_id = getIntent().getStringExtra("type");
        cat_id = getIntent().getStringExtra("cat");

        Log.e("test", productID + ", "+ name + ", "+ type_id+ ", "+ cat_id);


        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#ffffff\">" + name+ "</font>"));


//        getSupportActionBar().show();
//        getSupportActionBar().setTitle(name);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);


        productApi();


        product_arrow_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(ProductDetails.this);
                dialog.setContentView(R.layout.product_dialog);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                dialog.getWindow().setWindowAnimations(R.style.AnimationForDialog);

                TextView productDialog_TV_cancle = dialog.findViewById(R.id.productDialog_TV_cancle);
                TextView productDialog_TV_ok = dialog.findViewById(R.id.productDialog_TV_ok);
                RecyclerView productDialog_recyclerView = dialog.findViewById(R.id.productDialog_recyclerView);

                ProductDetailsDialogAdapter productDetailsDialogAdapter = new ProductDetailsDialogAdapter(ProductDetails.this, pakageList);
                productDialog_recyclerView.setLayoutManager(new LinearLayoutManager(ProductDetails.this, LinearLayoutManager.HORIZONTAL, false));
                productDialog_recyclerView.setAdapter(productDetailsDialogAdapter);



                productDialog_TV_cancle.setOnClickListener(v1 -> dialog.cancel());

                productDialog_TV_ok.setOnClickListener(v12 -> dialog.cancel());

                dialog.show();

            }
        });


        ProductDetailsGridAPI("is_popular", "1");


        productDetail_btn_addToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCartApi();
            }
        });

    }

    private void findIds() {

        product_arrow_down = findViewById(R.id.product_arrow_down);
        product_IV_image = findViewById(R.id.product_IV_image);
        product_TV_price = findViewById(R.id.product_TV_price);
        product_TV_name = findViewById(R.id.product_TV_name);
        product_TV_ratting = findViewById(R.id.product_TV_ratting);
        product_TV_time = findViewById(R.id.product_TV_time);
        product_TV_rating2 = findViewById(R.id.product_TV_rating2);
        product_alcohol = findViewById(R.id.product_alcohol);
        product_brewer = findViewById(R.id.product_brewer);
        product_TV_discription = findViewById(R.id.product_TV_discription);
        productDetail_grid = findViewById(R.id.productDetail_grid);

        productDetail_btn_addToCard = findViewById(R.id.productDetail_btn_addToCard);

    }

    private void modelListSimilar() {

//        list.add(new SampleModel(R.drawable.beer, "Budweiser", "$2.90 - $104.95", "1"));
//        list.add(new SampleModel(R.drawable.vodka, "Budweiser", "$2.90 - $104.95", "2"));
//        list.add(new SampleModel(R.drawable.beer, "Budweiser", "$2.90 - $104.95", "3"));
//        list.add(new SampleModel(R.drawable.vodka, "Budweiser", "$2.90 - $104.95", "4"));
    }


    private void productApi() {

        boolean networkCheck = CommonMethod.isNetworkAvailable(this);
        if (!networkCheck) {
            Toast.makeText(this, "check network", Toast.LENGTH_SHORT).show();
        } else {
            Map<String, String> map = new
                    HashMap<>();
            map.put(Common.Apikey_text, Common.Apikey_value);
            map.put("product_id", "60519");
            map.put("category_id", "1");
            map.put("type_id", "Beer");



            Call<ResponseProductDetail> call1 = apiInterface.productDetail(map);

            call1.enqueue(new Callback<ResponseProductDetail>() {
                @Override
                public void onResponse(Call<ResponseProductDetail> call, Response<ResponseProductDetail> response) {
                    if (response.isSuccessful()) {
                        ResponseProductDetail responseProductDetail = response.body();

                        pakageList = responseProductDetail.getPackages();

//                            Common.jwt = responseSignup.getJwt();
//                            Log.e("response : " , String.valueOf(response));
//                            Toast.makeText(SignUp.this, "Signup Successful", Toast.LENGTH_SHORT).show();

//                            startActivity(new Intent(SignUp.this, Login.class));


                        Picasso.get().load(responseProductDetail.getImage()).into(product_IV_image);
                        product_TV_name.setText(responseProductDetail.getLabel());
                        product_TV_ratting.setText(responseProductDetail.getRating());
                        product_brewer.setText(responseProductDetail.getBrewer());
                        product_alcohol.setText(responseProductDetail.getAlcoholContent());

//                        


                    } else {
                        Toast.makeText(ProductDetails.this, "Data not found", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<ResponseProductDetail> call, Throwable t) {
                    Toast.makeText(ProductDetails.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void ProductDetailsGridAPI(String typeKey, String typeID) {

        boolean networkCheck = CommonMethod.isNetworkAvailable(getApplication());
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


                        productDetailsList = response.body();

                        GridAdapter gridAdapter = new GridAdapter(ProductDetails.this, productDetailsList);
                        productDetail_grid.setAdapter(gridAdapter);
//                            whatsHotApi("is_popular", "1");


//                            Toast.makeText(getContext(), "Beer list", Toast.LENGTH_SHORT).show();

//                            startActivity(new Intent(getContext(), DashBoard.class));
                    } else {
                        Toast.makeText(ProductDetails.this, "No Data found", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<List<ResponseHome>> call, Throwable t) {
                    Toast.makeText(ProductDetails.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(ProductDetails.this, "Please Check your internet.", Toast.LENGTH_SHORT).show();
        }

    }

    private void addToCartApi() {

        boolean networkCheck = CommonMethod.isNetworkAvailable(this);
        if (networkCheck) {

            Map<String, String> map = new HashMap<>();
            map.put(Common.Apikey_text, Common.Apikey_value);
            map.put("ext_shopping_cart_id", "12345689");
            map.put("ext_customer_id", "12345678");
            map.put("ext_location_id", "2315");
            map.put("address", Address);





            Call<ResponseAddToCart> call1 = apiInterface.addToCart(map);

            call1.enqueue(new Callback<ResponseAddToCart>() {
                @Override
                public void onResponse(Call<ResponseAddToCart> call, Response<ResponseAddToCart> response) {
                    if(response.isSuccessful()){
                        ResponseAddToCart responseSignup = response.body();
//                        Common.jwt = responseSignup.getJwt();
                        Log.e("response : " , String.valueOf(response));
                        Toast.makeText(ProductDetails.this, "Signup Successful", Toast.LENGTH_SHORT).show();

//                        startActivity(new Intent(ProductDetails.this, ReviewCart.class));
                    }

                }

                @Override
                public void onFailure(Call<ResponseAddToCart> call, Throwable t) {
                    Toast.makeText(ProductDetails.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(ProductDetails.this, "Please Check your internet.", Toast.LENGTH_SHORT).show();
        }

    }


}