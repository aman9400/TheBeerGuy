package com.example.thebeerguy.DashBoard;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.Apis.APIClient;
import com.example.Apis.APIInterface;
import com.example.Databse.MyDatabase;
import com.example.Databse.Store;
import com.example.Profile.PaymentMethod;
import com.example.common.Common;
import com.example.common.CommonMethod;
import com.example.thebeerguy.DashBoard.Home.CheckOut.Checkout;
import com.example.thebeerguy.DashBoard.Home.CheckOut.GuestCheckout;
import com.example.thebeerguy.DashBoard.Home.PaymentResponse.ResponsePayment;
import com.example.thebeerguy.Intro.LandingScreen;
import com.example.thebeerguy.Product_Details.AddToCartResponse.ResponseAddToCart;
import com.example.thebeerguy.Product_Details.ProductDetails;
import com.example.thebeerguy.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.thebeerguy.Intro.LandingScreen.Address;

public class ReviewCart extends AppCompatActivity implements ReviewCartClick {

    Button review_button_checkout;
    private CheckBox review_gift_checkBox;
    APIInterface apiInterface;
    Store[] stores;
    TextView subTotal, deliveryCharge, taxAndCharges, discount_amount, GrandTotal;
    TextView addr_addr;
    private RecyclerView cart_products_recycler;
    private SwipeRefreshLayout swipeRefreshLayout;
    ReviewCartAdapter reviewCartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_cart);

        getSupportActionBar().hide();

        cart_products_recycler = findViewById(R.id.cart_products_recycler);

        findIds();
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);


        apiInterface = APIClient.getClient().create(APIInterface.class);
//        APIClient.

        stores = MyDatabase.getDatabase(ReviewCart.this).patientDAO().totalStoreData();


        if(Common.responseAddToCart != null) {
           int x =  MyDatabase.getDatabase(ReviewCart.this).patientDAO().getW() ;
            subTotal.setText("" + x);
            if (Common.responseAddToCart.getTotalAmount() != null) {
                deliveryCharge.setText("$" + Common.responseAddToCart.getDeliveryFee());
                taxAndCharges.setText("$" + Common.responseAddToCart.getHstAmount());
                GrandTotal.setText("$" + Common.responseAddToCart.getTotalAmount());
            }else {
                deliveryCharge.setText("$" + "0");
                taxAndCharges.setText("$" + "0");
                GrandTotal.setText("$" + "0");
            }

        }

         reviewCartAdapter = new ReviewCartAdapter(this, stores, this);
        cart_products_recycler.setHasFixedSize(true);
        cart_products_recycler.setLayoutManager(new LinearLayoutManager(this));
        cart_products_recycler.setAdapter(reviewCartAdapter);


        review_button_checkout.setOnClickListener(v -> paymentApi());

        swipeRefreshLayout.setOnRefreshListener(()->{
            reviewCartAdapter.notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);

        });
    }

    private void findIds() {

        //subTotal, deliveryCharge, taxAndCharges, discount_amount, GrandTotal;

        review_button_checkout = findViewById(R.id.review_button_checkout);
        subTotal = findViewById(R.id.subTotal);
        deliveryCharge = findViewById(R.id.deliveryCharge);
        taxAndCharges = findViewById(R.id.taxAndCharges);
        GrandTotal = findViewById(R.id.GrandTotal);
        review_gift_checkBox = findViewById(R.id.review_gift_checkBox);

    }

    private void paymentApi() {

        boolean networkCheck = CommonMethod.isNetworkAvailable(this);
        if (networkCheck) {

            Map<String, String> map = new HashMap<>();
            map.put(Common.Apikey_text, Common.Apikey_value);
            map.put("ext_purchase_id", "");
            map.put("ext_shopping_cart_id", ""+Common.shoppingId);
            map.put("ext_customer_id", ""+Common.Customer_ID);
            map.put("ext_location_id", "2315");
            map.put("address", Address);
            map.put("name", "");
            map.put("phone", "416-555-1234");
            map.put("products", "productID");


            Call<ResponsePayment> call1 = apiInterface.payment(map);

            call1.enqueue(new Callback<ResponsePayment>() {
                @Override
                public void onResponse(Call<ResponsePayment> call, Response<ResponsePayment> response) {
                    if (response.isSuccessful()) {
                        ResponsePayment responseSignup = response.body();
//                        Common.jwt = responseSignup.getJwt();
                        Log.e("response : ", String.valueOf(response));
//                        Toast.makeText(ReviewCart.this, "Added", Toast.LENGTH_SHORT).show();


                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ReviewCart.this);
                        boolean Islogin = prefs.getBoolean("Islogin", false);

                        if (Islogin){

                            startActivity(new Intent(ReviewCart.this, Checkout.class));
                        }else{
                            startActivity(new Intent(ReviewCart.this, GuestCheckout.class));
                        }

                    }

                }

                @Override
                public void onFailure(Call<ResponsePayment> call, Throwable t) {
                    Toast.makeText(ReviewCart.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(ReviewCart.this, "Please Check your internet.", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void increase(int position, TextView amount, TextView quant) {

        int quantity = MyDatabase.getDatabase(this).patientDAO().getQuatity(stores[position].getProductID());
        MyDatabase.getDatabase(this).patientDAO().updateTable(quantity + 1, stores[position].getProductID());

        quant.setText(""+ quantity +1);
        int carNumber = MyDatabase.getDatabase(this).patientDAO().getCartNumber();
        MyDatabase.getDatabase(this).patientDAO().setCartNumber(carNumber + 1);

        Store[] stores1 = MyDatabase.getDatabase(ReviewCart.this).patientDAO().totalStoreData();
        reviewCartAdapter.setStores(stores1);

        reviewCartAdapter.notifyDataSetChanged();
        addToCartApi(stores[position].getProductID());

    }

    @Override
    public void decrease(int position, TextView amount, TextView quanti) {
        int quantity = MyDatabase.getDatabase(this).patientDAO().getQuatity(stores[position].getProductID());
        if(quantity >= 2){
            MyDatabase.getDatabase(this).patientDAO().updateTable(quantity - 1, stores[position].getProductID());
           quanti.setText(""+ (quantity - 1));
                int carNumber = MyDatabase.getDatabase(this).patientDAO().getCartNumber();
                MyDatabase.getDatabase(this).patientDAO().setCartNumber(carNumber - 1);


            Store[] stores1 = MyDatabase.getDatabase(ReviewCart.this).patientDAO().totalStoreData();
            reviewCartAdapter.setStores(stores1);

            reviewCartAdapter.notifyDataSetChanged();

            addToCartApi(stores[position].getProductID());  // call api
        }else {
            if( quantity == 1){
                MyDatabase.getDatabase(this).patientDAO().deleteData(stores[position].getProductID());
               quanti.setText("0");


                Store[] stores1 = MyDatabase.getDatabase(ReviewCart.this).patientDAO().totalStoreData();
                reviewCartAdapter.setStores(stores1);

                reviewCartAdapter.notifyDataSetChanged();

                addToCartApi(stores[position].getProductID());  // call api
            }
        }
    }

    private void addToCartApi(int productID) {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Store[] newStore = MyDatabase.getDatabase(this).patientDAO().totalStoreData();
        JSONArray jsonArray = new JSONArray();


        for (int i = 0; i < 1 ; i++) {
            try {
                JSONObject jsonObject1 = new JSONObject();
//                jsonObject1.put("product_id","60519");
//                jsonObject1.put("product_id",newStore[i].getProductID());
                jsonObject1.put("package_id","118059");
//                jsonObject1.put("package_id",newStore[i].getPackageID());
                Log.e("test packageid list - : " , ""+newStore[i].getPackageID());
                jsonObject1.put("price","27.95");
//                jsonObject1.put("price",newStore[i].getProductPrice());
                jsonObject1.put("quantity", "3");
//                jsonObject1.put("quantity",newStore[i].getQuantity());

                jsonArray.put(jsonObject1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        boolean networkCheck = CommonMethod.isNetworkAvailable(this);
        if (networkCheck) {
            SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(this);
            String nameLoggedIn = prefs1.getString("LoginName", "Guest");

            Long shopping_id = System.currentTimeMillis();

            Map<String, String> map = new HashMap<>();
            map.put(Common.Apikey_text, Common.Apikey_value);
            map.put("ext_shopping_cart_id", "12345689");
//            map.put("ext_shopping_cart_id", ""+ shopping_id);
            Common.shoppingId = shopping_id;
            map.put("ext_customer_id", ""+Common.Customer_ID);
            map.put("ext_location_id", ""+Common.locationID);   // must be from store api
            map.put("address", "123 Test St, Toronto, ON, M8Z4G2");
//            map.put("address", Address);
            map.put("name", "Aman");
//            map.put("name", nameLoggedIn);
            map.put("phone", "416-555-1234");
            map.put("products", String.valueOf(jsonArray));


            Call<ResponseAddToCart> call1 = apiInterface.addToCart(map);

            call1.enqueue(new Callback<ResponseAddToCart>() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onResponse(Call<ResponseAddToCart> call, Response<ResponseAddToCart> response) {
                    progressDialog.dismiss();
                    if (response.isSuccessful()) {
                        ResponseAddToCart responseAddToCart = response.body();

                        Common.responseAddToCart = responseAddToCart;

//                        Common.jwt = responseSignup.getJwt();
                        Log.e("test-amount ", String.valueOf(response.body().getTotalAmount()));

//                        startActivity(new Intent(ProductDetails.this, ReviewCart.class));

                        int x =  MyDatabase.getDatabase(ReviewCart.this).patientDAO().getW() ;
                        subTotal.setText("" + x);
                        deliveryCharge.setText("$" + responseAddToCart.getDeliveryFee());
                        taxAndCharges.setText("$" + responseAddToCart.getHstAmount());
                        GrandTotal.setText("$" + responseAddToCart.getTotalAmount());
                    }

                }

                @Override
                public void onFailure(Call<ResponseAddToCart> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(ReviewCart.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(ReviewCart.this, "Please Check your internet.", Toast.LENGTH_SHORT).show();
        }

    }
}