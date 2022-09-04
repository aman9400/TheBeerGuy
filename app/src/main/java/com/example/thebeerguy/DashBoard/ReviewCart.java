package com.example.thebeerguy.DashBoard;

import static com.example.thebeerguy.Intro.LandingScreen.Address;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.Apis.APIClient;
import com.example.Apis.APIInterface;
import com.example.Profile.PaymentMethod;
import com.example.common.Common;
import com.example.common.CommonMethod;
import com.example.thebeerguy.DashBoard.Home.PaymentResponse.ResponsePayment;
import com.example.thebeerguy.Product_Details.AddToCartResponse.ResponseAddToCart;
import com.example.thebeerguy.Product_Details.ProductDetails;
import com.example.thebeerguy.R;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewCart extends AppCompatActivity {

    Button place_order;
    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_cart);

        getSupportActionBar().hide();

        findIds();

        apiInterface = APIClient.getClient().create(APIInterface.class);
//        APIClient.

        place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                paymentApi();
            }
        });
    }

    private void findIds() {

        place_order = findViewById(R.id.place_order);
    }

    private void paymentApi() {

        boolean networkCheck = CommonMethod.isNetworkAvailable(this);
        if (networkCheck) {

            Map<String, String> map = new HashMap<>();
            map.put(Common.Apikey_text, Common.Apikey_value);
            map.put("ext_purchase_id","123456779" );
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
                        Toast.makeText(ReviewCart.this, "Added", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(ReviewCart.this, PaymentMethod.class));
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
}