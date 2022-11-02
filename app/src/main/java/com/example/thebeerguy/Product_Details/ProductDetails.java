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
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
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
import com.example.Databse.Store;
import com.example.common.Common;
import com.example.common.CommonMethod;
import com.example.login.Login;
import com.example.thebeerguy.DashBoard.DashBoard;
import com.example.thebeerguy.DashBoard.Home.Adapters.WhatsHotAdapter;
import com.example.thebeerguy.DashBoard.ResponseJson.ProductReq;
import com.example.thebeerguy.DashBoard.ResponseJson.homeResponse.ResponseHome;
import com.example.thebeerguy.DashBoard.ReviewCart;
import com.example.thebeerguy.DashBoard.ReviewModel;
import com.example.thebeerguy.Intro.LandingScreen;
import com.example.thebeerguy.Product_Details.AddToCartResponse.ResponseAddToCart;
import com.example.thebeerguy.Product_Details.FavResponse.ResponseFav;
import com.example.thebeerguy.Product_Details.ProductDetailsResponse.Package;
import com.example.thebeerguy.Product_Details.ProductDetailsResponse.ResponseProductDetail;
import com.example.thebeerguy.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

public class ProductDetails extends AppCompatActivity implements GetProductPackageId {

    RecyclerView productDetail_recycler;
    APIInterface apiInterface;
    String Address = LandingScreen.Address;
    private final List<ResponseHome> list = new ArrayList<>();
    private TextView product_arrow_down, product_TV_price, product_TV_name,
            product_TV_ratting, product_TV_time, product_TV_rating2, product_TV_packageID,
            product_details_TV_store,
            product_TV_discription, product_brewer, product_alcohol;
    private Button productDetail_btn_addToCard;
    private ImageView product_ImV_fav;
    private List<ResponseHome> productDetailsList = new ArrayList<>();
    private List<Package> pakageList = new ArrayList<>();
    private ImageView product_IV_image;
    public static String productID;
    private String type_id;
    private String cat_id;
    private ProgressDialog progressDialog;
    private int productPackageId = 0;
    private boolean isClicked = false;
    TextView tv;
    Dialog dialog;
    private TextView textView43;
    int newCartNumber2;
    int newNumber = 0;
    private Boolean IsFav = false;

    List<ProductReq> list1 = new ArrayList<>();
    private String packageName;

    private  RatingBar ratingBar;

    String product_name, product_price, product_image;
    private Object ReviewModel;

    @SuppressLint({"RestrictedApi", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        findIds();
        textView43 = findViewById(R.id.product_TV_packageID);
//        int newCartNumber = MyDatabase.getDatabase(ProductDetails.this).patientDAO().getW() ;
//        tv.setText(""+ newCartNumber);

        if (Common.Hour != null){

            product_TV_time.setText("Deliver as early as 1 hour");
        }else {
            product_TV_time.setText("We Open tomorrow at 9:30am ");
            product_TV_time.setTextColor(R.color.red);
        }

        product_ImV_fav.setOnClickListener(v -> {

            if (!isClicked) {
                isClicked = true;
                product_ImV_fav.setImageResource(R.drawable.ic_favorite_24);
                favApi();
//                Toast.makeText(ProductDetails.this, "Added to favourite list ", Toast.LENGTH_SHORT).show();

            } else {
                isClicked = false;
                product_ImV_fav.setImageResource(R.drawable.ic_unfavorite_24);
            }
        });

        apiInterface = APIClient.getClient().create(APIInterface.class);

        productID = getIntent().getStringExtra("productID");
        String name = getIntent().getStringExtra("name");
        type_id = getIntent().getStringExtra("type");
        cat_id = getIntent().getStringExtra("cat");

        Log.e("test", productID + ", " + name + ", " + type_id + ", " + cat_id);

        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#ffffff\">" + name + "</font>"));

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        productApi();

        product_arrow_down.setOnClickListener(v -> {

            dialog = new Dialog(ProductDetails.this);
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


        ProductDetailsRecyclerdAPI("type_id", "1");


        productDetail_btn_addToCard.setOnClickListener(v -> {
            addToCartApi();

        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, DashBoard.class);
        startActivity(intent);
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);

        MenuItem item = menu.findItem(R.id.badge);
        MenuItemCompat.setActionView(item, R.layout.actionbar_badge_layout);

       RelativeLayout badgeLayout = (RelativeLayout)    MenuItemCompat.getActionView(item);

         tv = (TextView) badgeLayout.findViewById(R.id.actionbar_notifcation_textview);
         newCartNumber2 = MyDatabase.getDatabase(ProductDetails.this).patientDAO().getW() ;
        tv.setText(""+ newCartNumber2);

        ImageView imageView = (ImageView) badgeLayout.findViewById(R.id.clickCartIconMenu);
        imageView.setOnClickListener(v->{
            if(newNumber == 0){
                Toast.makeText(this, "No Product added", Toast.LENGTH_SHORT).show();
            }else {
                Intent intent = new Intent(this, ReviewCart.class);
                startActivity(intent);
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), DashBoard.class);
        startActivityForResult(myIntent, 0);
        onBackPressed();
        return true;
    }

    private void findIds() {

        product_arrow_down = findViewById(R.id.product_arrow_down);
        product_IV_image = findViewById(R.id.product_IV_image);
        product_TV_price = findViewById(R.id.product_TV_price);
        product_TV_name = findViewById(R.id.product_TV_name);
        product_TV_time = findViewById(R.id.product_TV_time);
        product_alcohol = findViewById(R.id.product_alcohol);
        product_brewer = findViewById(R.id.product_brewer);
        product_TV_discription = findViewById(R.id.product_TV_discription);
        productDetail_recycler = findViewById(R.id.productDetail_grid);
        product_details_TV_store = findViewById(R.id.product_details_TV_store);

        product_ImV_fav = findViewById(R.id.product_ImV_fav);
        ratingBar = findViewById(R.id.ratingBar);
        product_TV_packageID = findViewById(R.id.product_TV_packageID);

        productDetail_btn_addToCard = findViewById(R.id.productDetail_btn_addToCard);

    }

    private void productApi() {

        ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
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

            if(productPackageId != 0){
                map.put("package_id", ""+productPackageId);
            }


            Call<List<ResponseProductDetail>> call1 = apiInterface.productDetail(map);

            call1.enqueue(new Callback<List<ResponseProductDetail>>() {
                @Override
                public void onResponse(Call<List<ResponseProductDetail>> call, Response<List<ResponseProductDetail>> response) {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        List<ResponseProductDetail> responseProductDetail = response.body();

                        pakageList = responseProductDetail.get(0).getPackages();



                        textView43.setText(responseProductDetail.get(0).getPackages().get(0).getQuantity() +
                                "x" + responseProductDetail.get(0).getPackages().get(0).getSize() +" "+
                                responseProductDetail.get(0).getPackages().get(0).getContainer() + " for $ " +
                                responseProductDetail.get(0).getPackages().get(0).getPrice());

                        Log.e("test  size ", "" + pakageList.size());
                        Log.e("test price common ", "" + responseProductDetail.get(0).getCommonPrice());

                        packageName = responseProductDetail.get(0).getPackages().get(0).getQuantity() +
                                "x" + responseProductDetail.get(0).getPackages().get(0).getSize() +" "+
                                responseProductDetail.get(0).getPackages().get(0).getContainer() + " for $ " +
                                responseProductDetail.get(0).getPackages().get(0).getPrice();

                        Picasso.get().load(responseProductDetail.get(0).getImage()).into(product_IV_image);
                        product_TV_name.setText(responseProductDetail.get(0).getLabel());

                        product_name = responseProductDetail.get(0).getLabel();
                        product_image = responseProductDetail.get(0).getImage();
                        productPackageId = pakageList.get(0).getPackageId();
//                        product_

//                        ratingBar.setRating(getResources().getColor(R.color.colorPrimary));
                        ratingBar.setRating(Float.parseFloat(responseProductDetail.get(0).getRating()));

                        product_brewer.setText( responseProductDetail.get(0).getBrewer());
                        product_alcohol.setText(responseProductDetail.get(0).getAlcoholContent());
                        product_details_TV_store.setText(responseProductDetail.get(0).getPackages().get(0).getStoreAbbrev());


//                        product_price = responseProductDetail.get(0).getCommonPrice();
                        product_price =   responseProductDetail.get(0).getPackages().get(0).getPrice();

                        MyDatabase.getDatabase(ProductDetails.this).patientDAO().insertTecent(
                                productID, ""+productPackageId, product_price, "1"
                        );

//                        product_TV_packageID = responseProductDetail.get(0).get;

                        product_TV_price.setText("$"+responseProductDetail.get(0).getPackages().get(0).getPrice());
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
//            map.put("is_topten", "1");
            map.put("category_id", cat_id);
            map.put("type_id", type_id);
//                map.put(typeID"skin_id", "2");

            Call<List<ResponseHome>> call1 = apiInterface.home(map);

            call1.enqueue(new Callback<List<ResponseHome>>() {
                @Override
                public void onResponse(Call<List<ResponseHome>> call, Response<List<ResponseHome>> response) {
                    if (response.isSuccessful()) {

                        productDetailsList = response.body();

                        WhatsHotAdapter whatsHotAdapter = new WhatsHotAdapter(ProductDetails.this, productDetailsList);
                        productDetail_recycler.setHasFixedSize(true);
                        productDetail_recycler.setLayoutManager(new LinearLayoutManager(ProductDetails.this, LinearLayoutManager.HORIZONTAL, false));
                        productDetail_recycler.setAdapter(whatsHotAdapter);

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


            ProductReq productReq = new ProductReq();
            productReq.setProductId(""+productID);
            productReq.setPrice(""+product_price);
            productReq.setPackageId(""+productPackageId);
            productReq.setQuantity(""+1);

            list1.add(productReq);


            Long shopping_id = System.currentTimeMillis();

            ReviewModel reviewModel = new ReviewModel();
            reviewModel.setApiKey("codewraps-app-dev");
            reviewModel.setExtShoppingCartId(12345689);
            reviewModel.setExtCustomerId(12345678);
            reviewModel.setExtLocationId(1000);
            reviewModel.setAddress("123 Test St, Toronto, ON, M8Z4G2");
            reviewModel.setName(nameLoggedIn);
            reviewModel.setPhone("416-555-1234");
            reviewModel.setProducts(list1);


            Call<ResponseAddToCart> call1 = apiInterface.addToCart(reviewModel);

            call1.enqueue(new Callback<ResponseAddToCart>() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onResponse(Call<ResponseAddToCart> call, Response<ResponseAddToCart> response) {
                    if (response.isSuccessful()) {
                        ResponseAddToCart responseAddToCart = response.body();

                        Common.responseAddToCart = responseAddToCart;

                        Log.e("response : ", String.valueOf(response.body().getTotalAmount()));

                        Common.cartNumber = Common.cartNumber + 1;
                        Log.e("test cart number", "+"+ Common.cartNumber);

                        Store[] countStore = MyDatabase.getDatabase(ProductDetails.this).patientDAO().productIdFetch(Integer.parseInt(productID));

                        if(countStore.length >= 1){
                           int num =  MyDatabase.getDatabase(ProductDetails.this).patientDAO().getQuatity(Integer.parseInt(productID));
                            MyDatabase.getDatabase(ProductDetails.this).patientDAO().updateTable(num + 1, Integer.parseInt(productID));
                        }else{
                            long getBack = MyDatabase.getDatabase(ProductDetails.this).patientDAO()
                                    .insertIntoTable(Integer.parseInt(productID), 1,
                                            product_name,
                                            product_image,
                                            product_price,
                                            productPackageId,
                                            packageName);
                        }

                        int newCartNumber = MyDatabase.getDatabase(ProductDetails.this).patientDAO().getW() ;
                        tv.setText(""+ newCartNumber);

                        MyDatabase.getDatabase(ProductDetails.this).patientDAO().setCartNumber(
                                newCartNumber
                        );

                        newNumber = 1;


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

//                        startActivity(new Intent(ProductDetails.this, ProductDetails.class));
                    }

                }

                @Override
                public void onFailure(Call<ResponseAddToCart> call, Throwable t) {
//                    Toast.makeText(ProductDetails.this, "Something went wrong" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(ProductDetails.this, "Please Check your internet.", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void getId(int id, String name, String price) {
        packageName = name;

        productPackageId = id;
        textView43.setText(name);
        product_TV_price.setText("$"+price);
        dialog.dismiss();
//        productApi();
    }


    private void favApi() {

        boolean networkCheck = CommonMethod.isNetworkAvailable(this);
        if (networkCheck) {

            Map<String, String> map = new HashMap<>();
            map.put(Common.Apikey_text, Common.Apikey_value);
            map.put("ext_customer_id", ""+Common.Customer_ID);
            map.put("products", productID);


            Call<ResponseFav> call1 = apiInterface.fav(map, "Bearer "+Common.jwt);

            call1.enqueue(new Callback<ResponseFav>() {
                @Override
                public void onResponse(Call<ResponseFav> call, Response<ResponseFav> response) {
                    if (response.isSuccessful()) {
                        ResponseFav responseFav = response.body();
//                        Common.jwt = responseFav.getResult();
                        Log.e("response : ", String.valueOf(response));

                    }
                    IsFav = true;
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ProductDetails.this);
                    prefs.edit().putBoolean("IsFav", true).commit();
                    prefs.edit().putString("product_id",productID ).commit();


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