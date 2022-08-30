package com.example.thebeerguy.DashBoard.Home.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thebeerguy.DashBoard.Home.SubCategory.SubCategory;
import com.example.thebeerguy.DashBoard.ResponseJson.homeResponse.ResponseHome;
import com.example.thebeerguy.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LiquorAdapter extends RecyclerView.Adapter<LiquorAdapter.MyViewholder>{

    Context context;
    List<ResponseHome> liquorList;

    public LiquorAdapter(Context context, List<ResponseHome> liquorList) {
        this.context = context;
        this.liquorList = liquorList;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LiquorAdapter.MyViewholder(LayoutInflater.from(context).inflate(R.layout.sample_recycler2, parent,false));
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {

        ResponseHome responseHome = liquorList.get(position);
        Picasso.get().load(responseHome.getImage()).into(holder.recycler_text_image);
        holder.recycler_text_name.setText(responseHome.getLabel());
        holder.recycler_text_price.setText(responseHome.getMinPrice()+ " - "+ responseHome.getMaxPrice());
        holder.recycler_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SubCategory.class);
                intent.putExtra("category_id", responseHome.getProductId());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return liquorList.size();
    }

    public static class MyViewholder extends RecyclerView.ViewHolder {

        ImageView recycler_text_image;
        CardView recycler_cardView;
        TextView recycler_text_name, recycler_text_price;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);

            recycler_text_image = itemView.findViewById(R.id.recycler2_text_image);
            recycler_text_name = itemView.findViewById(R.id.recycler2_text_name);
            recycler_text_price = itemView.findViewById(R.id.recycler2_text_price);
            recycler_cardView = itemView.findViewById(R.id.recycler2_cardView);
        }
    }
}
