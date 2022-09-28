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
import com.example.thebeerguy.DashBoard.Home.categoryResponse.ResponseCategory;
import com.example.thebeerguy.DashBoard.ResponseJson.homeResponse.ResponseHome;
import com.example.thebeerguy.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    List<ResponseCategory> categoryList;
    Context context;
    private String typeID;

    public CategoryAdapter(List<ResponseCategory> categoryList, Context context, String typeID) {
        this.categoryList = categoryList;
        this.context = context;
        this.typeID = typeID;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new CategoryAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.category_list, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ResponseCategory responseCategory = categoryList.get(position);
        holder.lable_text.setText(responseCategory.getLabel());
        holder.card_home.setOnClickListener(v->{
            Intent intent = new Intent(context, SubCategory.class);
            intent.putExtra("subCatId", ""+responseCategory.getId());
            intent.putExtra("typeID", typeID);
            intent.putExtra("name", responseCategory.getLabel());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView lable_text;
        ImageView category_image;
        CardView card_home;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            lable_text = itemView.findViewById(R.id.lable_text);
            category_image = itemView.findViewById(R.id.category_image);
            card_home = itemView.findViewById(R.id.card_home);

        }
    }
}
