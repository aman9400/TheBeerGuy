package com.example.thebeerguy.Product_Details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thebeerguy.DashBoard.ResponseJson.homeResponse.ResponseHome;
import com.example.thebeerguy.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductDetailAdapter extends BaseAdapter {

    private Context context;
    private List<ResponseHome> productDetailsList;

    public ProductDetailAdapter(Context context, List<ResponseHome> productDetailsList) {
        this.context = context;
        this.productDetailsList = productDetailsList;
    }

    @Override
    public int getCount() {
        return 0;
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

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.sample_recycler2, parent, false);
        }


        ImageView recycler2_text_image = convertView.findViewById(R.id.recycler2_text_image);
        TextView recycler2_text_name = convertView.findViewById(R.id.recycler2_text_name);
//        TextView recycler_text_price = convertView.findViewById(R.id.sale_recycler_text_price);
//        TextView recycler_text1 = convertView.findViewById(R.id.sale_recycler_text1);
//        TextView recycler_text2 = convertView.findViewById(R.id.recycler_text2);

        ResponseHome responseHome = productDetailsList.get(position);


        Picasso.get().load(responseHome.getImage()).into(recycler2_text_image);
        recycler2_text_name.setText(responseHome.getLabel());
//        recycler_text_price.setText(responseHome.getMinPrice() + " - " + responseHome.getMaxPrice());





        return convertView;


    }
}
