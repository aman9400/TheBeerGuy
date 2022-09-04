package com.example.Profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.thebeerguy.DashBoard.ResponseJson.homeResponse.ResponseHome;
import com.example.thebeerguy.R;

import java.util.ArrayList;
import java.util.List;

public class ManageAddress extends AppCompatActivity {

    Button manageAddress_button_newAddress;
    RecyclerView manageAddress_recyclerView;

    private List<AddressModel> addressModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_address);

        getSupportActionBar().hide();


        manageAddress_button_newAddress = findViewById(R.id.manageAddress_button_newAddress);
        manageAddress_recyclerView = findViewById(R.id.manageAddress_recyclerView);


        addressModelList.add(new AddressModel(R.drawable.home_account, R.drawable.edit, R.drawable.delete,"Home","J326 Dakshinpuri new delhi 110062  j Block dakshinpuri ambedkar nagar sec 5 , Near Kali building school"));
        addressModelList.add(new AddressModel(R.drawable.work, R.drawable.edit, R.drawable.delete,"Home","J326 Dakshinpuri new delhi 110062  j Block dakshinpuri ambedkar nagar sec 5 , Near Kali building school"));
        addressModelList.add(new AddressModel(R.drawable.other, R.drawable.edit, R.drawable.delete,"Home","J326 Dakshinpuri new delhi 110062  j Block dakshinpuri ambedkar nagar sec 5 , Near Kali building school"));


        AddressAdapter addressAdapter = new AddressAdapter(this, addressModelList);
        manageAddress_recyclerView.setHasFixedSize(true);
        manageAddress_recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        manageAddress_recyclerView.setAdapter(addressAdapter);


        manageAddress_button_newAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageAddress.this, AddAddress.class);
                startActivity(intent);
            }
        });

    }
}