package com.example.thebeerguy.DashBoard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.thebeerguy.DashBoard.Home.ResponseSearch.Result;
import com.example.thebeerguy.DashBoard.ResponseJson.homeResponse.ResponseHome;
import com.example.thebeerguy.Product_Details.ProductDetails;
import com.example.thebeerguy.Product_Details.productListResponse.ResponseProductList;
import com.example.thebeerguy.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavAdapter extends BaseAdapter {


    private Context context;
    private List<ResponseHome> list;

    public FavAdapter(Context context, List<ResponseHome> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.sample_recycler2, parent, false);

        ImageView recycler2_text_image = convertView.findViewById(R.id.recycler2_text_image);
        TextView recycler2_text_name = convertView.findViewById(R.id.recycler2_text_name);
        TextView recycler2_text_price = convertView.findViewById(R.id.recycler2_text_price);
        CardView recycler2_cardView = convertView.findViewById(R.id.recycler2_cardView);

        ResponseHome responseProductDetail = list.get(position);

        Picasso.get().load(responseProductDetail.getImage()).into(recycler2_text_image);
        recycler2_text_name.setText(responseProductDetail.getLabel());
        recycler2_text_price.setText(responseProductDetail.getCommonPrice());
        recycler2_cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetails.class);
            intent.putExtra("productID", ""+responseProductDetail.getProductId());
            intent.putExtra("name", ""+responseProductDetail.getLabel());
            intent.putExtra("type", ""+responseProductDetail.getType());
            intent.putExtra("cat", ""+responseProductDetail.getCategory());
            context.startActivity(intent);
        });
        return convertView;
    }
}
