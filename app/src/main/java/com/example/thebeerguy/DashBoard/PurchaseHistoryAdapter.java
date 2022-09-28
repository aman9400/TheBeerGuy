package com.example.thebeerguy.DashBoard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thebeerguy.DashBoard.Home.Adapters.BeerAdapter;
import com.example.thebeerguy.DashBoard.PurchaseHistoryResponse.ResponsePurchaseHistory;
import com.example.thebeerguy.DashBoard.ResponseJson.homeResponse.ResponseHome;
import com.example.thebeerguy.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PurchaseHistoryAdapter extends RecyclerView.Adapter<PurchaseHistoryAdapter.MyviewHolder> {

    Context context;
    List<ResponsePurchaseHistory> purchaseHistories;

    public PurchaseHistoryAdapter(Context context, List<ResponsePurchaseHistory> purchaseHistories) {
        this.context = context;
        this.purchaseHistories = purchaseHistories;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new PurchaseHistoryAdapter.MyviewHolder(LayoutInflater.from(context).inflate(R.layout.purchase_history_recycler,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {

        ResponsePurchaseHistory responsePurchaseHistory = purchaseHistories.get(position);

//        Picasso.get().load(responsePurchaseHistory.getImage()).into(holder.recycler_text_image);
//        holder.recycler_text_name.setText(responsePurchaseHistory.getLabel());

    }

    @Override
    public int getItemCount() {
        return purchaseHistories.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {

        TextView orderId_tv, date_orderHistory, amountRe, order_address, reOrderBtn, view_reorder_btn;
        ImageView imageOrderHistory;



        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            orderId_tv = itemView.findViewById(R.id.orderId_tv);
            date_orderHistory = itemView.findViewById(R.id.date_orderHistory);
            amountRe = itemView.findViewById(R.id.amountRe);
            order_address = itemView.findViewById(R.id.order_address);
            imageOrderHistory = itemView.findViewById(R.id.imageOrderHistory);
            reOrderBtn = itemView.findViewById(R.id.reOrderBtn);
            view_reorder_btn = itemView.findViewById(R.id.view_reorder_btn);

        }
    }
}
