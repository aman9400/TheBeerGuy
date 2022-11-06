package com.example.thebeerguy.DashBoard.Home.SubCategory.FilterAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thebeerguy.DashBoard.Home.SubCategory.FilterResponse.Option;
import com.example.thebeerguy.DashBoard.Home.SubCategory.PriceInterface;
import com.example.thebeerguy.R;

public class FilterPriceRangeAdapter extends RecyclerView.Adapter<FilterPriceRangeAdapter.MyviewHolder>{

    Context context;
    Option filter_priceRange;
    PriceInterface priceInterface;

    public FilterPriceRangeAdapter(Context context, Option filter_priceRange, PriceInterface priceInterface) {
        this.context = context;
        this.filter_priceRange = filter_priceRange;
        this.priceInterface = priceInterface;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new FilterPriceRangeAdapter.MyviewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_filter_options,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {

        holder.filter_TV_text.setText( filter_priceRange.getVals().get(position));
        holder.filter_radiobtn.setOnClickListener(v -> {
            priceInterface.price_range(filter_priceRange.getVals().get(position));
        });

    }

    @Override
    public int getItemCount() {
        return filter_priceRange.getVals().size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {

        TextView filter_TV_text;
        RadioButton filter_radiobtn;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            filter_TV_text = itemView.findViewById(R.id.filter_TV_text);
            filter_radiobtn = itemView.findViewById(R.id.filter_radioBtn);


        }
    }
}
