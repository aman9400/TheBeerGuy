package com.example.thebeerguy.DashBoard.Home.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thebeerguy.DashBoard.ResponseJson.homeResponse.ResponseHome;
import com.example.thebeerguy.Product_Details.ProductDetails;
import com.example.thebeerguy.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OnSaleAdapter extends RecyclerView.Adapter<OnSaleAdapter.MyViewholder> {

    Context context;
    List<ResponseHome> onSalelist;

    public OnSaleAdapter(Context context, List<ResponseHome> onSalelist) {
        this.context = context;
        this.onSalelist = onSalelist;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new OnSaleAdapter.MyViewholder(LayoutInflater.from(context).inflate(R.layout.sale_recycler, parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {


        ResponseHome responseHome = onSalelist.get(position);
        Picasso.get().load(responseHome.getImage()).into(holder.recycler_text_image);
        holder.recycler_text_name.setText(responseHome.getLabel());
        holder.recycler_text_price.setText("$"+responseHome.getMinPrice()+ " - "+ "$"+responseHome.getMaxPrice());
        holder.recycler_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, ""+responseHome.getProductId(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ProductDetails.class);
                intent.putExtra("productID", ""+responseHome.getProductId());
                intent.putExtra("name", responseHome.getLabel());
                intent.putExtra("type", responseHome.getType());
                intent.putExtra("cat", responseHome.getCategory());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return onSalelist.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        ImageView recycler_text_image;
        CardView recycler_cardView;
        TextView recycler_text_name, recycler_text_price, recycler_text1, recycler_text2;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);

            recycler_text_image = itemView.findViewById(R.id.sale_recycler_text_image);
            recycler_text_name = itemView.findViewById(R.id.sale_recycler_text_name);
            recycler_text_price = itemView.findViewById(R.id.sale_recycler_text_price);
            recycler_text1 = itemView.findViewById(R.id.sale_recycler_text1);
            recycler_cardView = itemView.findViewById(R.id.sale_recycler_cardView);
        }
        }

}
