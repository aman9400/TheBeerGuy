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
import com.example.thebeerguy.DashBoard.Home.SubCategory.OriginInterface;
import com.example.thebeerguy.R;

public class FitlerOrginAdapter extends RecyclerView.Adapter<FitlerOrginAdapter.MyviewHolder>{

    Context context;
    Option filter_origin;
    OriginInterface originInterface;

    public FitlerOrginAdapter(Context context, Option filter_origin, OriginInterface originInterface) {
        this.context = context;
        this.filter_origin = filter_origin;
        this.originInterface = originInterface;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new FitlerOrginAdapter.MyviewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_filter_options,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {

        holder.filter_TV_text.setText( filter_origin.getVals().get(position));
        holder.filter_radiobtn.setOnClickListener(v -> {
            originInterface.origin_range( filter_origin.getVals().get(position));
        });

    }

    @Override
    public int getItemCount() {
        return filter_origin.getVals().size();
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
