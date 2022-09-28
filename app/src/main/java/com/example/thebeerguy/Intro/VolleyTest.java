package com.example.thebeerguy.Intro;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.Databse.MyDatabase;
import com.example.Databse.Store;
import com.example.common.Common;
import com.example.thebeerguy.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;



public class VolleyTest extends AppCompatActivity {


    String url = "http://sandbox.tbg.api.tweak.tbg.delivery/shopping_cart/update/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_test);

//        checkoutPayment();
        try {
            payment();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void payment() throws JSONException {
        Map<String, String> params = new HashMap<>();

        params.put("api_key", "codewraps-app-dev");
        params.put("ext_shopping_cart_id", "12345689");
        params.put("ext_customer_id", "12345678");
        params.put("ext_location_id", "1000");
        params.put("address", "123 Test St, Toronto, ON, M8Z4G2");
        params.put("name", "Aman");
        params.put("phone", "416-555-1234");


        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("product_id", 60519);
        jsonObject.put("package_id", 118059);
        jsonObject.put("price", 27.95);
        jsonObject.put("quantity", 120);

        jsonArray.put(jsonObject);

        params.put("products", ""+ jsonArray);


        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                response -> {

                    try {

                        Toast.makeText(this, ""+response, Toast.LENGTH_SHORT).show();
                        Log.e("Response", response.toString());


                    }catch (Exception e) {
                        e.printStackTrace();
                    }

                }, error -> {
            Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show();
        });
        queue.add(req);

    }

    private void checkoutPayment() {



        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        Log.e("test", jsonObject.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    Log.e("test", error.toString());
                    Toast.makeText(this, ""+ error.toString(), Toast.LENGTH_SHORT).show();
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Store[] newStore = MyDatabase.getDatabase(VolleyTest.this).patientDAO().totalStoreData();
                JSONArray jsonArray = new JSONArray();


                for (int i = 0; i < 1 ; i++) {
                    try {
                        JSONObject jsonObject1 = new JSONObject();
//                jsonObject1.put("product_id","60519");
                        jsonObject1.put("product_id", "60519");
//                jsonObject1.put("package_id","118059");
                        jsonObject1.put("package_id","118059");
                        Log.e("test packageid list - : ", "" + newStore[i].getPackageID());
//                jsonObject1.put("price","27.95");
                        jsonObject1.put("price", "27.95");
//                jsonObject1.put("quantity", "8");
                        jsonObject1.put("quantity", "120");

                        jsonArray.put(jsonObject1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                Map<String, String> map = new HashMap<>();
                map.put(Common.Apikey_text, Common.Apikey_value);
                map.put("ext_shopping_cart_id", "12345689");
//            map.put("ext_shopping_cart_id", ""+ shopping_id);
//                Common.shoppingId = shopping_id;
                map.put("ext_customer_id", "12345678");
                map.put("ext_location_id", "1000");   // must be from store api
                map.put("address", "123 Test St, Toronto, ON, M8Z4G2");
//            map.put("address", Address);
                map.put("name", "Aman");
//            map.put("name", nameLoggedIn);
                map.put("phone", "416-555-1234");
                map.put("products", String.valueOf(jsonArray));
                return map;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}