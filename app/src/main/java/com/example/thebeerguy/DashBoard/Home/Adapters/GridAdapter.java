package com.example.thebeerguy.DashBoard.Home.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Databse.RecentProduct;
import com.example.thebeerguy.DashBoard.RecentResponse.ResponseRecent;
import com.example.thebeerguy.DashBoard.ResponseJson.homeResponse.ResponseHome;
import com.example.thebeerguy.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GridAdapter extends BaseAdapter {

    private Context context;
    private List<RecentProduct> list;

    public GridAdapter(Context context, List<RecentProduct> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.sample_recycler1, parent, false);
        }


        ImageView recycler_text_image = convertView.findViewById(R.id.recycler_text_image);
        TextView recycler_text_name = convertView.findViewById(R.id.sale_recycler_text_name);
        TextView recycler_text_price = convertView.findViewById(R.id.sale_recycler_text_price);
        TextView recycler_text1 = convertView.findViewById(R.id.sale_recycler_text1);
        TextView recycler_text2 = convertView.findViewById(R.id.recycler_text2);

        RecentProduct responseHome = list.get(position);


//        Picasso.get().load(responseHome.getImage()).into(recycler_text_image);
        recycler_text_name.setText(""+responseHome.getName());
        recycler_text_price.setText("$" + responseHome.getPrice()/* + " - " + "$" + responseHome.getMaxPrice()*/);





        return convertView;
    }
}
