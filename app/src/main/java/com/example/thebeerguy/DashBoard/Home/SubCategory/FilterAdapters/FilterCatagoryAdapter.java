package com.example.thebeerguy.DashBoard.Home.SubCategory.FilterAdapters;

import android.content.Context;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thebeerguy.DashBoard.Home.Adapters.BeerAdapter;
import com.example.thebeerguy.DashBoard.Home.SubCategory.FilterResponse.ResponseFilter;
import com.example.thebeerguy.DashBoard.Home.SubCategory.FilterResponse.Selected;
import com.example.thebeerguy.DashBoard.ResponseJson.homeResponse.ResponseHome;
import com.example.thebeerguy.R;

import java.util.List;

public class FilterCatagoryAdapter extends RecyclerView.Adapter<FilterCatagoryAdapter.MyViewHolder> {

    Context context;
    List<ResponseFilter> filter_cat;

    public FilterCatagoryAdapter(Context context, List<ResponseFilter> filter_cat) {
        this.context = context;
        this.filter_cat = filter_cat;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new FilterCatagoryAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_filter_options,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ResponseFilter filter = filter_cat.get(position);

    }

    @Override
    public int getItemCount() {
        return filter_cat.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView filter_TV_text;
        RadioButton filter_radiobtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            filter_TV_text = itemView.findViewById(R.id.filter_TV_text);
            filter_radiobtn = itemView.findViewById(R.id.filter_radioBtn);

        }
    }
}
