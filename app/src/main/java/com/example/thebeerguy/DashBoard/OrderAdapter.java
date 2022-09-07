package com.example.thebeerguy.DashBoard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thebeerguy.DashBoard.Home.Adapters.BeerAdapter;
import com.example.thebeerguy.DashBoard.Home.Adapters.CategoryAdapter;
import com.example.thebeerguy.DashBoard.PurchaseHistoryResponse.ResponsePurchaseHistory;
import com.example.thebeerguy.DashBoard.ResponseJson.homeResponse.ResponseHome;
import com.example.thebeerguy.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {

    List<ResponsePurchaseHistory> purchaseHistories;
    Context context;

    public OrderAdapter(List<ResponsePurchaseHistory> purchaseHistories, Context context) {
        this.purchaseHistories = purchaseHistories;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new OrderAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.purchase_history_recycler,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ResponsePurchaseHistory responsePurchaseHistory = purchaseHistories.get(position);
        holder.view_reorder_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

    }

    @Override
    public int getItemCount() {
        return purchaseHistories.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView orderId_tv,date_orderHistory,order_address;
        Button reOrderBtn, view_reorder_btn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            orderId_tv = itemView.findViewById(R.id.orderId_tv);
            date_orderHistory = itemView.findViewById(R.id.date_orderHistory);
            order_address = itemView.findViewById(R.id.order_address);
            reOrderBtn = itemView.findViewById(R.id.reOrderBtn);
            view_reorder_btn = itemView.findViewById(R.id.view_reorder_btn);
        }


    }
}
