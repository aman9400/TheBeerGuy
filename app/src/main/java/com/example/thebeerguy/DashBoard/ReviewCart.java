package com.example.thebeerguy.DashBoard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.thebeerguy.DashBoard.Home.PaymentResponse.ResponsePayment;
import com.example.thebeerguy.Intro.LandingScreen;
import com.example.thebeerguy.R;

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
            subTotal.setText("" + Common.responseAddToCart.getResult());
            deliveryCharge.setText("$" + Common.responseAddToCart.getDeliveryFee());
            taxAndCharges.setText("$" + Common.responseAddToCart.getHstAmount());
            GrandTotal.setText("$" + Common.responseAddToCart.getTotalAmount());



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
            map.put("ext_purchase_id", "123456779");
            map.put("ext_shopping_cart_id", "12345689");
            map.put("ext_customer_id", "12345678");
            map.put("ext_location_id", "2315");
            map.put("address", Address);
            map.put("name", "Aman");
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

                        startActivity(new Intent(ReviewCart.this, Checkout.class));
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

    }
//        holder.tv_quantity_res.setText(""+ (quantity + 1));
////            int carNumber = MyDatabase.getDatabase(context).patientDAO().getCartNumber();
////            MyDatabase.getDatabase(context).patientDAO().setCartNumber(carNumber + 1);
//    }

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
        }else {
            if( quantity == 1){
                MyDatabase.getDatabase(this).patientDAO().deleteData(stores[position].getProductID());
               quanti.setText("0");


                Store[] stores1 = MyDatabase.getDatabase(ReviewCart.this).patientDAO().totalStoreData();
                reviewCartAdapter.setStores(stores1);

                reviewCartAdapter.notifyDataSetChanged();
//                    int carNumber = MyDatabase.getDatabase(context).patientDAO().getCartNumber();
//                    MyDatabase.getDatabase(context).patientDAO().setCartNumber(0);
            }
        }
    }
}