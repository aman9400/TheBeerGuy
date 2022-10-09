package com.example.Profile;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.Apis.APIClient;
import com.example.Apis.APIInterface;
import com.example.Databse.MyDatabase;
import com.example.Databse.Store;
import com.example.common.Common;
import com.example.common.CommonMethod;
import com.example.thebeerguy.DashBoard.DashBoard;
import com.example.thebeerguy.DashBoard.Home.CheckOut.GuestCheckout;
import com.example.thebeerguy.DashBoard.Home.CheckOut.PaymentReq;
import com.example.thebeerguy.DashBoard.Home.CheckOut.ReviewCartModel;
import com.example.thebeerguy.DashBoard.ResponseJson.ProductReq;
import com.example.thebeerguy.OrderComplete;
import com.example.thebeerguy.Product_Details.AddToCartResponse.ResponseAddToCart;
import com.example.thebeerguy.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentMethod extends AppCompatActivity {

    ImageView payment_ImV_backBtn;
    RadioButton payment_COD_radioBtn, payment_card_radioBtn;
    Button payment_button_complete;
    List<ProductReq> list = new ArrayList<>();
    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        getSupportActionBar().hide();

        apiInterface = APIClient.getClient().create(APIInterface.class);

        findId();

        payment_button_complete.setOnClickListener(v -> {
            apiPayment();

        });

        payment_ImV_backBtn.setOnClickListener(v -> PaymentMethod.super.onBackPressed());


    }

    private void findId() {

        payment_button_complete = findViewById(R.id.payment_button_complete);
        payment_ImV_backBtn = findViewById(R.id.payment_ImV_backBtn);
        payment_COD_radioBtn = findViewById(R.id.payment_COD_radioBtn);
        payment_card_radioBtn = findViewById(R.id.payment_card_radioBtn);

    }

    private void apiPayment() {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Store[] newStore = MyDatabase.getDatabase(this).patientDAO().totalStoreData();
        JSONArray jsonArray = new JSONArray();

        list.clear();
        for (int i = 0; i < newStore.length; i++) {
            try {
                ProductReq productReq = new ProductReq();
                productReq.setProductId("" + newStore[i].getProductID());
                productReq.setPrice(newStore[i].getProductPrice());
                productReq.setPackageId("" + newStore[i].getPackageID());
                productReq.setQuantity("" + newStore[i].getQuantity());

                list.add(productReq);
//                jsonArray.put(jsonObject1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        boolean networkCheck = CommonMethod.isNetworkAvailable(this);
        if (networkCheck) {
            SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(this);
            String nameLoggedIn = prefs1.getString("LoginName", "Guest");

            Long shopping_id = System.currentTimeMillis();

            PaymentReq reviewModel = new PaymentReq();
            reviewModel.setApiKey("codewraps-app-dev");
            reviewModel.setExtShoppingCartId(12345689);
            reviewModel.setExtCustomerId(12345678);
            reviewModel.setExtLocationId(1000);
//            reviewModel.setAddress("123 Test St, Toronto, ON, M8Z4G2");
//            reviewModel.setName("Aman");
//            reviewModel.setBuzzer("sas");
//            reviewModel.setEmail("email");
//            reviewModel.setIs_business("No");
//            reviewModel.setIs_hotel("NO");
//            reviewModel.setPhone("416-555-1234");
            reviewModel.setProducts(list);


            Call<ResponseAddToCart> call1 = apiInterface.payment_done(reviewModel);

            call1.enqueue(new Callback<ResponseAddToCart>() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onResponse(Call<ResponseAddToCart> call, Response<ResponseAddToCart> response) {
                    progressDialog.dismiss();
                    if (response.isSuccessful()) {

                        try {
                            ResponseAddToCart responseAddToCart = response.body();
                            Toast.makeText(PaymentMethod.this, "Payment Done", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(PaymentMethod.this, OrderComplete.class);
                            startActivity(intent);

                            Common.responseAddToCart = responseAddToCart;
                            if (responseAddToCart.getResult().equalsIgnoreCase("error")) {
//                                Toast.makeText(GuestCheckout.this, "invalid", Toast.LENGTH_SHORT).show();
                            } else {

                                Log.e("test-amount ", String.valueOf(response.body().getProductAmount()));

//                        startActivity(new Intent(ProductDetails.this, ReviewCart.class));
                                Intent intenwt = new Intent(PaymentMethod.this, OrderComplete.class);
                                startActivity(intenwt);

                            }

//
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(PaymentMethod.this, response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseAddToCart> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(PaymentMethod.this, "Something went wrong" + "  :  "+ t.getMessage() , Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(PaymentMethod.this, "Please Check your internet.", Toast.LENGTH_SHORT).show();
        }

    }

}