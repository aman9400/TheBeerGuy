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
import com.example.thebeerguy.R;

public class FilterSubCatAdapter extends RecyclerView.Adapter<FilterSubCatAdapter.MyviewHolder>{

    Context context;
    Option filter_subCat;

    public FilterSubCatAdapter(Context context, Option filter_subCat) {
        this.context = context;
        this.filter_subCat = filter_subCat;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new FilterSubCatAdapter.MyviewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_filter_options,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {

        holder.filter_TV_text.setText( filter_subCat.getVals().get(position));

    }

    @Override
    public int getItemCount() {
        return filter_subCat.getVals().size();
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
