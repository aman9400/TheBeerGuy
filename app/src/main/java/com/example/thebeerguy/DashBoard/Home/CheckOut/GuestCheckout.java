package com.example.thebeerguy.DashBoard.Home.CheckOut;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Apis.APIClient;
import com.example.Apis.APIInterface;
import com.example.Databse.MyDatabase;
import com.example.Databse.Store;
import com.example.Profile.PaymentMethod;
import com.example.common.Common;
import com.example.common.CommonMethod;
import com.example.thebeerguy.DashBoard.ResponseJson.ProductReq;
import com.example.thebeerguy.DashBoard.ReviewCart;
import com.example.thebeerguy.DashBoard.ReviewModel;
import com.example.thebeerguy.Intro.LandingScreen;
import com.example.thebeerguy.Product_Details.AddToCartResponse.ResponseAddToCart;
import com.example.thebeerguy.R;
import com.example.thebeerguy.TermsConditions;

import org.json.JSONArray;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuestCheckout extends AppCompatActivity implements SelectedDates {

    EditText Guest_name, guest_phone, guest_email, guest_address, guest_apt, guest_buzzer, guest_extraInfo,
            checkOut_TV_extraInfo, guestCheck_editText_pass;
    APIInterface apiInterface;

    CheckBox guest_business_radioBtn, guest_hotel_radioBtn, checkBoxBtn_TC;
    ImageView guest_checkout_ImV_backBtn;

    TextView guestCheckOut_TV_days, guestCheckOut_TV_time, guestCheckOut_TC_tV;

    Button guestCheckout_button;

    List<String> dateList = new ArrayList<>();
    List<String> timeList = new ArrayList<>();

    String selectedDate;
    String selectedTime;
    List<ProductReq> list = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_checkout);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        getSupportActionBar().hide();

        GuestfindIds();
        getlastTenDaysDates();

        timeList.add("Morning (10AM - 12AM)");
        timeList.add("AfterNoon (12PM - 2PM)");
        timeList.add("AfterNoon (2PM - 4PM)");
        timeList.add("Evening (4PM - 6PM)");
        timeList.add("Night (9PM - 11PM)");

        guestCheckout_button.setOnClickListener(v -> {
            if(!checkBoxBtn_TC.isChecked()){
                Toast.makeText(this, "Please check our Terms and Conditions.", Toast.LENGTH_SHORT).show();
            }
            else if(guest_address.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please fill your address", Toast.LENGTH_SHORT).show();
            }else if(guest_phone.getText().toString().isEmpty()){
                Toast.makeText(this, "Please fill your phone number", Toast.LENGTH_SHORT).show();
            }else if(Guest_name.getText().toString().isEmpty()){
                Toast.makeText(this, "Please fill your Name", Toast.LENGTH_SHORT).show();
            }
                else
            {
               apiCheckout();
            }

        });

        guestCheckOut_TC_tV.setOnClickListener(v -> {
            Intent intent = new Intent(GuestCheckout.this, TermsConditions.class);
            startActivity(intent);
        });

        guest_address.setText(LandingScreen.Address);

        guest_checkout_ImV_backBtn.setOnClickListener(v -> GuestCheckout.super.onBackPressed());

        guestCheckOut_TV_days.setOnClickListener(v -> {
            GuestDilogTimingsDate();
        });
        guestCheckOut_TV_time.setOnClickListener(v -> {
            GuestDilogTimingsShift();
        });




    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void getlastTenDaysDates() {
        List<String> days = new ArrayList<> ();
        LocalDate now = LocalDate.now();

        for (LocalDate d = now.minusDays(5); !d.isAfter(now); d = d.plusDays(1)) {
            days.add(d.toString());
        }

        for (String x : days) {
            dateList.add(x);
        }
    }

    private void apiCheckout() {

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

            ReviewCartModel reviewModel = new ReviewCartModel();
            reviewModel.setApiKey("codewraps-app-dev");
            reviewModel.setExtShoppingCartId(12345689);
            reviewModel.setExtCustomerId(12345678);
            reviewModel.setExtLocationId(1000);
            reviewModel.setAddress("123 Test St, Toronto, ON, M8Z4G2");
            reviewModel.setName("Aman");
            reviewModel.setBuzzer("sas");
            reviewModel.setEmail("email");
            reviewModel.setGift_card_note(checkOut_TV_extraInfo.getText().toString());
            reviewModel.setSchedule_date(selectedDate);
            reviewModel.setSchedule_time(selectedTime);
            reviewModel.setIs_business("No");
            reviewModel.setIs_hotel("NO");
            reviewModel.setPhone("416-555-1234");
            reviewModel.setProducts(list);


            Call<ResponseAddToCart> call1 = apiInterface.checkout(reviewModel);

            call1.enqueue(new Callback<ResponseAddToCart>() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onResponse(Call<ResponseAddToCart> call, Response<ResponseAddToCart> response) {
                    progressDialog.dismiss();
                    if (response.isSuccessful()) {

                        try {
                            ResponseAddToCart responseAddToCart = response.body();
                            Intent intent = new Intent(GuestCheckout.this, PaymentMethod.class);
                            startActivity(intent);

                            Common.responseAddToCart = responseAddToCart;
                            if (responseAddToCart.getResult().equalsIgnoreCase("error")) {
//                                Toast.makeText(GuestCheckout.this, "invalid", Toast.LENGTH_SHORT).show();
                            } else {

                                Log.e("test-amount ", String.valueOf(response.body().getProductAmount()));

//                        startActivity(new Intent(ProductDetails.this, ReviewCart.class));
                                Intent intenwt = new Intent(GuestCheckout.this, PaymentMethod.class);
                                startActivity(intenwt);

                            }

//
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(GuestCheckout.this, response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseAddToCart> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(GuestCheckout.this, "Something went wrong" + "  :  "+ t.getMessage() , Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(GuestCheckout.this, "Please Check your internet.", Toast.LENGTH_SHORT).show();
        }

    }

    private void GuestDilogTimingsDate() {

        Dialog dialog = new Dialog(GuestCheckout.this);
        dialog.setContentView(R.layout.checkout_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.getWindow().setWindowAnimations(R.style.AnimationForDialog);

        TextView checkoutDialog_TV_cancle = dialog.findViewById(R.id.checkoutDialog_TV_cancle);
        TextView checkoutDialog_TV_ok = dialog.findViewById(R.id.checkoutDialog_TV_ok);
        RecyclerView checkoutDialog_recyclerView = dialog.findViewById(R.id.checkoutDialog_recyclerView);

        CheckoutDialogAdapter checkoutDialogAdapter = new CheckoutDialogAdapter(GuestCheckout.this,
                dateList, this, "date");
        checkoutDialog_recyclerView.setLayoutManager(new LinearLayoutManager(GuestCheckout.this, LinearLayoutManager.VERTICAL, false));
        checkoutDialog_recyclerView.setAdapter(checkoutDialogAdapter);

        checkoutDialog_TV_cancle.setOnClickListener(v1 -> dialog.cancel());

        checkoutDialog_TV_ok.setOnClickListener(v -> {
            guestCheckOut_TV_days.setText(selectedDate);
            dialog.dismiss();
        });

        dialog.show();

    }

    private void GuestfindIds() {

        Guest_name = findViewById(R.id.Guest_name);
        guest_phone = findViewById(R.id.guest_phone);
        guest_email = findViewById(R.id.guest_email);
        guest_address = findViewById(R.id.guest_address);
        guest_apt = findViewById(R.id.guest_apt);
        guest_buzzer = findViewById(R.id.guest_buzzer);
        guest_extraInfo = findViewById(R.id.guest_extraInfo);
        checkOut_TV_extraInfo = findViewById(R.id.checkOut_TV_extraInfo);
        guestCheck_editText_pass = findViewById(R.id.guestCheck_editText_pass);
        guest_business_radioBtn = findViewById(R.id.guest_business_radioBtn);
        guest_hotel_radioBtn = findViewById(R.id.guest_hotel_radioBtn);
        guest_checkout_ImV_backBtn = findViewById(R.id.guest_checkout_ImV_backBtn);
        guestCheckOut_TV_time = findViewById(R.id.guestCheckOut_TV_time);
        guestCheckOut_TV_days = findViewById(R.id.guestCheckOut_TV_days);
        guestCheckOut_TC_tV = findViewById(R.id.guestCheckOut_TC_tV);
        guestCheckout_button = findViewById(R.id.guestCheckout_button);

        checkBoxBtn_TC = findViewById(R.id.checkBoxBtn_TC);
    }

    private void GuestDilogTimingsShift() {

        Dialog dialog = new Dialog(GuestCheckout.this);
        dialog.setContentView(R.layout.checkout_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.getWindow().setWindowAnimations(R.style.AnimationForDialog);

        TextView checkoutDialog_TV_cancle = dialog.findViewById(R.id.checkoutDialog_TV_cancle);
        TextView checkoutDialog_TV_ok = dialog.findViewById(R.id.checkoutDialog_TV_ok);
        RecyclerView checkoutDialog_recyclerView = dialog.findViewById(R.id.checkoutDialog_recyclerView);

        CheckoutDialogAdapter checkoutDialogAdapter = new CheckoutDialogAdapter(GuestCheckout.this,
                timeList, this, "time");
        checkoutDialog_recyclerView.setLayoutManager(new LinearLayoutManager(GuestCheckout.this, LinearLayoutManager.VERTICAL, false));
        checkoutDialog_recyclerView.setAdapter(checkoutDialogAdapter);

        checkoutDialog_TV_cancle.setOnClickListener(v1 -> dialog.cancel());

        checkoutDialog_TV_ok.setOnClickListener(v -> {
            guestCheckOut_TV_time.setText(selectedTime);
            dialog.dismiss();
        });

        dialog.show();

    }

    @Override
    public void getSelectedDate(String date, String msg)
    {
        if(msg.equalsIgnoreCase("date")){
            selectedDate = date;
        }else {
            selectedTime = date;
        }

    }
}