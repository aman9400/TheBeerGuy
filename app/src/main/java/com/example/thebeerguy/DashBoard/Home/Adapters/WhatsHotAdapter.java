package com.example.thebeerguy.DashBoard.Home.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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

public class WhatsHotAdapter extends RecyclerView.Adapter<WhatsHotAdapter.MyViewholder> {

    Context context;
    List<ResponseHome> whatsHotList;

    public WhatsHotAdapter(Context context, List<ResponseHome> whatsHotList) {
        this.context = context;
        this.whatsHotList = whatsHotList;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new WhatsHotAdapter.MyViewholder(LayoutInflater.from(context)
                .inflate(R.layout.sample_recycler1, parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {

        ResponseHome responseHome = whatsHotList.get(position);
        Picasso.get().load(responseHome.getImage()).into(holder.recycler_text_image);
        holder.recycler_text_name.setText(responseHome.getLabel());
        holder.recycler_text_price.setText(responseHome.getMinPrice()+ " - "+ responseHome.getMaxPrice());
        holder.recycler_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, ""+responseHome.getProductId(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, ProductDetails.class);
                intent.putExtra("productID", ""+responseHome.getProductId());
                intent.putExtra("name", responseHome.getLabel());
//                Log.e("test", ""+responseHome.getProductId());
                intent.putExtra("type", responseHome.getType());
                intent.putExtra("cat", responseHome.getCategory());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return whatsHotList.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        ImageView recycler_text_image;
        CardView recycler_cardView;
        TextView recycler_text_name, recycler_text_price, recycler_text1, recycler_text2;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);

            recycler_text_image = itemView.findViewById(R.id.recycler_text_image);
            recycler_text_name = itemView.findViewById(R.id.sale_recycler_text_name);
            recycler_text_price = itemView.findViewById(R.id.sale_recycler_text_price);
            recycler_text1 = itemView.findViewById(R.id.sale_recycler_text1);
            recycler_text2 = itemView.findViewById(R.id.recycler_text2);
            recycler_cardView = itemView.findViewById(R.id.recycler_cardView);
        }
    }
}
