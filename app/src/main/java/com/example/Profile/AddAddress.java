package com.example.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Apis.APIClient;
import com.example.Apis.APIInterface;
import com.example.Profile.AddAddressResponse.ResponseAddAddress;
import com.example.Signup.SignUp;
import com.example.Signup.responseSignup.ResponseSignup;
import com.example.common.Common;
import com.example.common.CommonMethod;
import com.example.login.Login;
import com.example.thebeerguy.R;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAddress extends AppCompatActivity {

   private EditText addAddress_ET_firstname,addAddress_ET_phonenumber,addAddress_ET_deliveryAdd, addAddress_ET_apt, addAddress_ET_buzzer,
            addAddress_ET_city, addAddress_ET_note;

   private CheckBox addAddress_CheckBox_work, addAddress_CheckBox_home, addAddress_CheckBox_other;
   private Button addAddress_Button_save;

    APIInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        getSupportActionBar().hide();

        findAddressIds();

        apiInterface = APIClient.getClient().create(APIInterface.class);

        addAddress_Button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addAddressAPI();
//                startActivity(new Intent(AddAddress.this, EditProfile.class));

            }
        });

    }

    private void findAddressIds() {
        addAddress_ET_firstname = findViewById(R.id.addAddress_ET_firstname);
        addAddress_ET_phonenumber = findViewById(R.id.addAddress_ET_phonenumber);
        addAddress_ET_deliveryAdd = findViewById(R.id.addAddress_ET_deliveryAdd);
        addAddress_ET_apt = findViewById(R.id.addAddress_ET_apt);
        addAddress_ET_buzzer = findViewById(R.id.addAddress_ET_buzzer);
        addAddress_ET_city = findViewById(R.id.addAddress_ET_city);
        addAddress_ET_note = findViewById(R.id.addAddress_ET_note);
        addAddress_CheckBox_work = findViewById(R.id.addAddress_CheckBox_work);
        addAddress_CheckBox_home = findViewById(R.id.addAddress_CheckBox_home);
        addAddress_CheckBox_other = findViewById(R.id.addAddress_CheckBox_other);
        addAddress_Button_save = findViewById(R.id.addAddress_Button_save);
    }

    private void addAddressAPI() {

        boolean networkCheck = CommonMethod.isNetworkAvailable(this);
        if(networkCheck){
            if(addAddress_ET_deliveryAdd.getText().toString().isEmpty()){
                Toast.makeText(this,"Enter Address", Toast.LENGTH_SHORT).show();

            }else if (addAddress_ET_buzzer.getText().toString().isEmpty()){
                Toast.makeText(this, "Please enter Buzzer code", Toast.LENGTH_SHORT).show();

            }

            else{
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(AddAddress.this);
                String email = prefs.getString("email","");

                Map<String, String> map = new HashMap<>();
                map.put(Common.Apikey_text, Common.Apikey_value);
                map.put("ext_customer_id", ""+Common.Customer_ID);
                map.put("ext_location_id", ""+Common.locationID);
                map.put("email", email);
                map.put("name", addAddress_ET_firstname.getText().toString().trim());
                map.put("phone", addAddress_ET_phonenumber.getText().toString().trim());
                map.put("address", addAddress_ET_deliveryAdd.getText().toString().trim());
                map.put("addr2", addAddress_ET_apt.getText().toString().trim());
                map.put("buzzer", addAddress_ET_buzzer.getText().toString().trim());
                map.put("location_extra", "");
                map.put("cell", addAddress_ET_city.getText().toString().trim());
                map.put("intersection", addAddress_ET_note.getText().toString().trim());


                Call<ResponseAddAddress> call1 = apiInterface.addAddress(map, Common.jwt );

                call1.enqueue(new Callback<ResponseAddAddress>() {
                    @Override
                    public void onResponse(Call<ResponseAddAddress> call, Response<ResponseAddAddress> response) {
                        if(response.isSuccessful()){
                            ResponseAddAddress responseSignup = response.body();
//                            Common.jwt = responseSignup.getJwt();
                            startActivity(new Intent(AddAddress.this, EditProfile.class));
                            Log.e("response : " , String.valueOf(response));
//                            Toast.makeText(SignUp.this, "Signup Successful", Toast.LENGTH_SHORT).show();

//                            startActivity(new Intent(SignUp.this, Login.class));

                        }else{
                            Toast.makeText(AddAddress.this, "Unauthorized, tolen expire, please login again", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseAddAddress> call, Throwable t) {
                        Toast.makeText(AddAddress.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }else {
            Toast.makeText(this, "Please Check your internet.", Toast.LENGTH_SHORT).show();
        }


    }


}