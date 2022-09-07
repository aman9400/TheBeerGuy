package com.example.thebeerguy.DashBoard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Databse.Store;
import com.example.thebeerguy.R;
import com.squareup.picasso.Picasso;

public class ReviewCartAdapter extends RecyclerView.Adapter<ReviewCartAdapter.Myholder> {
    Context context;
    Store[] stores;

    public ReviewCartAdapter(Context context, Store[] stores) {
        this.context = context;
        this.stores = stores;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_cart, parent, false);
        return new ReviewCartAdapter.Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {

        holder.tv_quantity_res.setText(""+stores[position].getQuantity());
        Picasso.get().load(stores[position].getImage()).into(holder.iv_cart_res);
        holder.amount_cart_res.setText(""+stores[position].getProductPrice());
        holder.tv_cart_res.setText(stores[position].getProductName());
    }

    @Override
    public int getItemCount() {
        return stores.length;
    }

    public class Myholder extends RecyclerView.ViewHolder {
        TextView tv_quantity_res, tv_cart_res, amount_cart_res;
        ImageView pos_cart_res, neg_cart_res, iv_cart_res;
        public Myholder(@NonNull View itemView) {
            super(itemView);

            tv_cart_res = itemView.findViewById(R.id.tv_cart_res);
            tv_quantity_res = itemView.findViewById(R.id.tv_quantity_res);
            amount_cart_res = itemView.findViewById(R.id.amount_cart_res);
            pos_cart_res = itemView.findViewById(R.id.pos_cart_res);
            neg_cart_res = itemView.findViewById(R.id.neg_cart_res);
            iv_cart_res = itemView.findViewById(R.id.iv_cart_res);
        }
    }
}
