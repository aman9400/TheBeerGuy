package com.example.thebeerguy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.Databse.MyDatabase;
import com.example.Databse.Store;
import com.example.thebeerguy.DashBoard.DashBoard;
import com.example.thebeerguy.DashBoard.ResponseJson.ProductReq;

import org.json.JSONArray;

public class OrderComplete extends AppCompatActivity {

    private Button oderComplete_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_complete);
        getSupportActionBar().hide();

        MyDatabase.getDatabase(this).patientDAO().setCartNumber(0);

        Store[] newStore = MyDatabase.getDatabase(this).patientDAO().totalStoreData();
//        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < newStore.length; i++) {
            MyDatabase.getDatabase(this).patientDAO().deleteData(newStore[i].getProductID());
        }

        oderComplete_button = findViewById(R.id.oderComplete_button);

        oderComplete_button.setOnClickListener(v -> {
            Intent intent = new Intent(OrderComplete.this, DashBoard.class);
            startActivity(intent);
        });
    }
}