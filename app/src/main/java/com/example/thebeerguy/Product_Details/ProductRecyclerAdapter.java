package com.example.thebeerguy.Product_Details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thebeerguy.DashBoard.Home.Adapters.WhatsHotAdapter;
import com.example.thebeerguy.DashBoard.ResponseJson.homeResponse.ResponseHome;
import com.example.thebeerguy.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.MyViewHolder> {

    Context context;
    List<ResponseHome> ProductRecycler;

    public ProductRecyclerAdapter(Context context, List<ResponseHome> productRecycler) {
        this.context = context;
        ProductRecycler = productRecycler;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ProductRecyclerAdapter.MyViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.sample_recycler2, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ResponseHome responseHome = ProductRecycler.get(position);
        Picasso.get().load(responseHome.getImage()).into(holder.recycler2_text_image);
        holder.recycler2_text_name.setText(responseHome.getLabel());
        holder.recycler2_text_price.setText("$" + responseHome.getMinPrice()+ " - "+ "$" + responseHome.getMaxPrice());

        holder.recycler2_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView recycler2_text_image;
        TextView recycler2_text_name, recycler2_text_price;
        CardView recycler2_cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


             recycler2_text_image = itemView.findViewById(R.id.recycler2_text_image);
             recycler2_text_name = itemView.findViewById(R.id.recycler2_text_name);
            recycler2_text_price = itemView.findViewById(R.id.recycler2_text_price);
            recycler2_cardView = itemView.findViewById(R.id.recycler2_cardView);
        }
    }
}
