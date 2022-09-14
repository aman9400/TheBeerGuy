package com.example.thebeerguy.DashBoard.Home.SubCategory;

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
import com.example.thebeerguy.Product_Details.ProductDetails;
import com.example.thebeerguy.Product_Details.productListResponse.ResponseProductList;
import com.example.thebeerguy.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SubCategoryAdapter extends BaseAdapter {

    private List<ResponseProductList> subCategoryList;
    private Context context;
    private String type_id;
    private String cate;
    private List<Result> list;
    private String getList;

    public SubCategoryAdapter(List<ResponseProductList> subCategoryList, Context context, String type_id, String cate, String getList) {
        this.subCategoryList = subCategoryList;
        this.context = context;
        this.type_id = type_id;
        this.cate = cate;
        this.getList = getList;
    }
    public SubCategoryAdapter(Context context, String type, String category, List<Result> list, String str){
        this.cate = category;
        this.context = context;
        this.list = list;
        this.type_id = type;
        this.getList = str;
    }


    @Override
    public int getCount() {
        if (getList.equalsIgnoreCase("search")){
            return list.size();
        }else {
            return subCategoryList.size();
        }

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
        TextView recycler2_text_price = convertView.findViewById(R.id.recycler2_text_price);
        CardView recycler2_cardView = convertView.findViewById(R.id.recycler2_cardView);

        if(getList.equalsIgnoreCase("search")){

            Result responseProductDetail = list.get(position);

            Picasso.get().load(responseProductDetail.getImage()).into(recycler2_text_image);
            recycler2_text_name.setText(responseProductDetail.getLabel());
            recycler2_text_price.setText(responseProductDetail.getMinPrice() + " - " + responseProductDetail.getMaxPrice());
            recycler2_cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ProductDetails.class);
                    intent.putExtra("productID", ""+responseProductDetail.getProductId());
                    intent.putExtra("name", ""+responseProductDetail.getLabel());
                    intent.putExtra("type", ""+type_id);
                    intent.putExtra("cat", ""+cate);
                    context.startActivity(intent);
                }
            });
        }else {

            ResponseProductList responseProductDetail = subCategoryList.get(position);

            Picasso.get().load(responseProductDetail.getImage()).into(recycler2_text_image);
            recycler2_text_name.setText(responseProductDetail.getLabel());
            recycler2_text_price.setText(responseProductDetail.getMinPrice() + " - " + responseProductDetail.getMaxPrice());
            recycler2_cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ProductDetails.class);
                    intent.putExtra("productID", ""+responseProductDetail.getProductId());
                    intent.putExtra("name", ""+responseProductDetail.getLabel());
                    intent.putExtra("type", ""+type_id);
                    intent.putExtra("cat", ""+cate);
                    context.startActivity(intent);
                }
            });
        }





        return convertView;
    }
}
