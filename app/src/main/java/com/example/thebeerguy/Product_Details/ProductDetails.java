package com.example.thebeerguy.Product_Details;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Apis.APIClient;
import com.example.Apis.APIInterface;
import com.example.Databse.MyDatabase;
import com.example.common.Common;
import com.example.common.CommonMethod;
import com.example.thebeerguy.DashBoard.DashBoard;
import com.example.thebeerguy.DashBoard.Home.Adapters.WhatsHotAdapter;
import com.example.thebeerguy.DashBoard.ResponseJson.homeResponse.ResponseHome;
import com.example.thebeerguy.DashBoard.ReviewCart;
import com.example.thebeerguy.Intro.LandingScreen;
import com.example.thebeerguy.Intro.ResponseStore.ResponseStore;
import com.example.thebeerguy.Product_Details.AddToCartResponse.ResponseAddToCart;
import com.example.thebeerguy.Product_Details.FavResponse.ResponseFav;
import com.example.thebeerguy.Product_Details.ProductDetailsResponse.Package;
import com.example.thebeerguy.Product_Details.ProductDetailsResponse.ResponseProductDetail;
import com.example.thebeerguy.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetails<textHoure> extends AppCompatActivity implements GetProductPackageId {

    RecyclerView productDetail_recycler;
    APIInterface apiInterface;
    String Address = LandingScreen.Address;
    private final List<ResponseHome> list = new ArrayList<>();
    private TextView product_arrow_down, product_TV_price, product_TV_name,
            product_TV_ratting, product_TV_time, product_TV_rating2,
            product_TV_discription, product_brewer, product_alcohol;
    private Button productDetail_btn_addToCard;
    private ImageView product_ImV_fav;
    private List<ResponseHome> productDetailsList = new ArrayList<>();
    private List<Package> pakageList = new ArrayList<>();
    private ImageView product_IV_image;
    private String productID;
    private String type_id;
    private String cat_id;
    private ProgressDialog progressDialog;
    private int productPackageId;
    private boolean isClicked = false;
    TextView tv;

    String product_name, product_price, product_image;







    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        findIds();

        if (Common.Hour != null){

            product_TV_time.setText("Deliver as early as 1 hour");
        }else {
            product_TV_time.setText("We Open tomorrow at 9:30am ");
        }




        product_ImV_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isClicked) {
//                    isClicked = true;
                    product_ImV_fav.setImageResource(R.drawable.ic_favorite_24);
                    favApi();
                    Toast.makeText(ProductDetails.this, "Added to favourite list ", Toast.LENGTH_SHORT).show();

                } else {
//                    isClicked = false;
                    product_ImV_fav.setImageResource(R.drawable.ic_unfavorite_24);
                }
            }
        });


        apiInterface = APIClient.getClient().create(APIInterface.class);


        productID = getIntent().getStringExtra("productID");
        String name = getIntent().getStringExtra("name");
        type_id = getIntent().getStringExtra("type");
        cat_id = getIntent().getStringExtra("cat");

        Log.e("test", productID + ", " + name + ", " + type_id + ", " + cat_id);


        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#ffffff\">" + name + "</font>"));


//        getSupportActionBar().show();
//        getSupportActionBar().setTitle(name);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);



        productApi();


        product_arrow_down.setOnClickListener(v -> {

            Dialog dialog = new Dialog(ProductDetails.this);
            dialog.setContentView(R.layout.product_dialog);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            dialog.getWindow().setWindowAnimations(R.style.AnimationForDialog);

            TextView productDialog_TV_cancle = dialog.findViewById(R.id.checkoutDialog_TV_cancle);
            TextView productDialog_TV_ok = dialog.findViewById(R.id.checkoutDialog_TV_ok);
            RecyclerView productDialog_recyclerView = dialog.findViewById(R.id.checkoutDialog_recyclerView);

            ProductDetailsDialogAdapter productDetailsDialogAdapter = new ProductDetailsDialogAdapter(ProductDetails.this, pakageList, this);
            productDialog_recyclerView.setLayoutManager(new LinearLayoutManager(ProductDetails.this, LinearLayoutManager.VERTICAL, false));
            productDialog_recyclerView.setAdapter(productDetailsDialogAdapter);


            productDialog_TV_cancle.setOnClickListener(v1 -> dialog.cancel());

            productDialog_TV_ok.setOnClickListener(v12 -> {
                productApi();
                dialog.cancel();
            });

            dialog.show();

        });


        ProductDetailsRecyclerdAPI("is_popular", "1");


        productDetail_btn_addToCard.setOnClickListener(v -> addToCartApi());

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, DashBoard.class);
        startActivity(intent);
//        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);

        MenuItem item = menu.findItem(R.id.badge);
        MenuItemCompat.setActionView(item, R.layout.actionbar_badge_layout);

       RelativeLayout badgeLayout = (RelativeLayout)    MenuItemCompat.getActionView(item);

         tv = (TextView) badgeLayout.findViewById(R.id.actionbar_notifcation_textview);
        int newCartNumber2 = MyDatabase.getDatabase(ProductDetails.this).patientDAO().getCartNumber() ;
        tv.setText(""+ newCartNumber2);

        ImageView imageView = (ImageView) badgeLayout.findViewById(R.id.clickCartIconMenu);
        imageView.setOnClickListener(v->{
            if(newCartNumber2 == 0){
                Toast.makeText(this, "No Product added", Toast.LENGTH_SHORT).show();
            }else {
                Intent intent = new Intent(this, ReviewCart.class);
                startActivity(intent);
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void findIds() {

        product_arrow_down = findViewById(R.id.product_arrow_down);
        product_IV_image = findViewById(R.id.product_IV_image);
        product_TV_price = findViewById(R.id.product_TV_price);
        product_TV_name = findViewById(R.id.product_TV_name);
        product_TV_ratting = findViewById(R.id.product_TV_ratting);
        product_TV_time = findViewById(R.id.product_TV_time);
        product_alcohol = findViewById(R.id.product_alcohol);
        product_brewer = findViewById(R.id.product_brewer);
        product_TV_discription = findViewById(R.id.product_TV_discription);
        productDetail_recycler = findViewById(R.id.productDetail_grid);

        product_ImV_fav = findViewById(R.id.product_ImV_fav);

        productDetail_btn_addToCard = findViewById(R.id.productDetail_btn_addToCard);

    }

    private void productApi() {

        boolean networkCheck = CommonMethod.isNetworkAvailable(this);
        if (!networkCheck) {
            Toast.makeText(this, "check network", Toast.LENGTH_SHORT).show();
        } else {
            Map<String, String> map = new
                    HashMap<>();
            map.put(Common.Apikey_text, Common.Apikey_value);
            map.put("product_id", productID);
            map.put("category_id", cat_id);
            map.put("type_id", type_id);


            Call<List<ResponseProductDetail>> call1 = apiInterface.productDetail(map);

            call1.enqueue(new Callback<List<ResponseProductDetail>>() {
                @Override
                public void onResponse(Call<List<ResponseProductDetail>> call, Response<List<ResponseProductDetail>> response) {
                    if (response.isSuccessful()) {
                        List<ResponseProductDetail> responseProductDetail = response.body();

                        pakageList = responseProductDetail.get(0).getPackages();


                        Log.e("test", "" + pakageList.size());

                        Picasso.get().load(responseProductDetail.get(0).getImage()).into(product_IV_image);
                        product_TV_name.setText(responseProductDetail.get(0).getLabel());

                        product_name = responseProductDetail.get(0).getLabel();
                        product_image = responseProductDetail.get(0).getImage();

                        product_TV_ratting.setText( responseProductDetail.get(0).getRating());
                        product_brewer.setText( responseProductDetail.get(0).getBrewer());
                        product_alcohol.setText(responseProductDetail.get(0).getAlcoholContent());

                        product_price = "$"+responseProductDetail.get(0).getCommonPrice();

                        product_TV_price.setText("$" + responseProductDetail.get(0).getMinPrice() + "-$" + responseProductDetail.get(0).getMaxPrice());
//                        product_TV_rating2.setText(responseProductDetail.get(0).getRating());
                        product_TV_discription.setText(responseProductDetail.get(0).getDescription());

                    } else {
                        Toast.makeText(ProductDetails.this, "Data not found", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<List<ResponseProductDetail>> call, Throwable t) {
                    Toast.makeText(ProductDetails.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("test", t.getMessage());
                }
            });
        }
    }

    private void ProductDetailsRecyclerdAPI(String typeKey, String typeID) {

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

                        WhatsHotAdapter whatsHotAdapter = new WhatsHotAdapter(ProductDetails.this, productDetailsList);
                        productDetail_recycler.setHasFixedSize(true);
                        productDetail_recycler.setLayoutManager(new LinearLayoutManager(ProductDetails.this, LinearLayoutManager.HORIZONTAL, false));
                        productDetail_recycler.setAdapter(whatsHotAdapter);
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
            SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(this);
            String nameLoggedIn = prefs1.getString("LoginName", "Guest");

            Long shopping_id = System.currentTimeMillis();

            Map<String, String> map = new HashMap<>();
            map.put(Common.Apikey_text, Common.Apikey_value);
            map.put("ext_shopping_cart_id", ""+ shopping_id);
            map.put("ext_customer_id", ""+Common.Customer_ID);
            map.put("ext_location_id", "2315");
            map.put("address", Address);
            map.put("name", nameLoggedIn);
            map.put("phone", "416-555-1234");
            map.put("products", productID);


            Call<ResponseAddToCart> call1 = apiInterface.addToCart(map);

            call1.enqueue(new Callback<ResponseAddToCart>() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onResponse(Call<ResponseAddToCart> call, Response<ResponseAddToCart> response) {
                    if (response.isSuccessful()) {
                        ResponseAddToCart responseAddToCart = response.body();

                        Common.responseAddToCart = responseAddToCart;

//                        Common.jwt = responseSignup.getJwt();
                        Log.e("response : ", String.valueOf(response.body().getTotalAmount()));

                        Common.cartNumber = Common.cartNumber + 1;
                        Log.e("test", "+"+ Common.cartNumber);
                        tv.setText(""+ Common.cartNumber);

                        int newCartNumber = MyDatabase.getDatabase(ProductDetails.this).patientDAO().getCartNumber() + 1;
                        tv.setText(""+ newCartNumber);

                        MyDatabase.getDatabase(ProductDetails.this).patientDAO().setCartNumber(
                          newCartNumber
                        );

                        long getBack = MyDatabase.getDatabase(ProductDetails.this).patientDAO()
                                .insertIntoTable(Integer.parseInt(productID), 1, product_name, product_image, product_price);

                        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        final VibrationEffect vibrationEffect5;
                        Toast.makeText(ProductDetails.this, "Product added to Cart", Toast.LENGTH_SHORT).show();
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {

                            // create vibrator effect with the constant EFFECT_HEAVY_CLICK
                            vibrationEffect5 = VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK);

                            // it is safe to cancel other vibrations currently taking place
                            vibrator.cancel();

                            vibrator.vibrate(vibrationEffect5);
                        }else{
                            long[] wave_time = {0, 100, 0, 100, 0, 100, 0, 100, 0, 100};
                            int[] wave_ampl = {0, 50, 0, 100, 0, 150, 0, 200, 0, 255};

                            VibrationEffect vibrationEffect = null;
                            vibrationEffect = VibrationEffect.createWaveform(wave_time, wave_ampl, -1);
                            vibrator.vibrate(vibrationEffect);
                        }

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


    @Override
    public void getId(int id) {
        productPackageId = id;
    }


    private void favApi() {

        boolean networkCheck = CommonMethod.isNetworkAvailable(this);
        if (networkCheck) {

            Map<String, String> map = new HashMap<>();
            map.put(Common.Apikey_text, Common.Apikey_value);
            map.put("products", productID);


            Call<ResponseFav> call1 = apiInterface.fav(map, "Bearer "+Common.jwt);

            call1.enqueue(new Callback<ResponseFav>() {
                @Override
                public void onResponse(Call<ResponseFav> call, Response<ResponseFav> response) {
                    if (response.isSuccessful()) {
                        ResponseFav responseFav = response.body();
                        Common.jwt = responseFav.getResult();
                        Log.e("response : ", String.valueOf(response));


//                        Toast.makeText(ProductDetails.this, "Added to fav ", Toast.LENGTH_SHORT).show();

//

//                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ProductDetails.this);
//                        prefs.edit().putBoolean("products", productID).commit();
                    }

                }

                @Override
                public void onFailure(Call<ResponseFav> call, Throwable t) {
                    Toast.makeText(ProductDetails.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(ProductDetails.this, "Please Check your internet.", Toast.LENGTH_SHORT).show();
        }
    }


}