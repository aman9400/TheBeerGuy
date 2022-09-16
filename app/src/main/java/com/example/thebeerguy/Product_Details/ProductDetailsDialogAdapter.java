package com.example.thebeerguy.Product_Details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thebeerguy.Product_Details.ProductDetailsResponse.Package;
import com.example.thebeerguy.R;

import java.util.List;

public class ProductDetailsDialogAdapter extends RecyclerView.Adapter<ProductDetailsDialogAdapter.MyviewoHolder> {

    Context context;
    List<Package> pakageList;
    GetProductPackageId getProductPackageId;
    boolean isCheck = false;

    public ProductDetailsDialogAdapter(Context context, List<Package> pakageList, GetProductPackageId getProductPackageId) {
        this.context = context;
        this.pakageList = pakageList;
        this.getProductPackageId = getProductPackageId;
    }

    @NonNull
    @Override
    public MyviewoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ProductDetailsDialogAdapter.MyviewoHolder(LayoutInflater.from(context).inflate(R.layout.product_detail_dialog_recycler,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyviewoHolder holder, int position) {

        Package aPackage = pakageList.get(position);
        holder.Product_dialog_recycler_textView.setText
                (aPackage.getQuantity() + "x" + aPackage.getSize() +" "+ aPackage.getContainer() + " for " + aPackage.getPrice());

        holder.cl_package_dialog.setOnClickListener(v->{
            getProductPackageId.getId(aPackage.getPackageId(), aPackage.getQuantity() + "x" + aPackage.getSize() +" "+ aPackage.getContainer() + " for " + aPackage.getPrice(), " " + aPackage.getPrice());
            holder.Product_dialog_recycler_radiobtn.setChecked(true);

        });

        holder.Product_dialog_recycler_radiobtn.setOnClickListener(v->{
            getProductPackageId.getId(aPackage.getPackageId(), aPackage.getQuantity() + "x" + aPackage.getSize() +" "+ aPackage.getContainer() + " for " + aPackage.getPrice(), " " + aPackage.getPrice());
            holder.Product_dialog_recycler_radiobtn.setChecked(true);
        });

    }

    @Override
    public int getItemCount() {
        return pakageList.size();
    }

    public class MyviewoHolder extends RecyclerView.ViewHolder {

        RadioButton Product_dialog_recycler_radiobtn;
        TextView Product_dialog_recycler_textView;
        ConstraintLayout cl_package_dialog;

        public MyviewoHolder(@NonNull View itemView) {
            super(itemView);

            Product_dialog_recycler_radiobtn = itemView.findViewById(R.id.checkout_dialog_recycler_radiobtn);
            Product_dialog_recycler_textView = itemView.findViewById(R.id.checkout_dialog_recycler_textView);
            cl_package_dialog = itemView.findViewById(R.id.checkout_package_dialog);

        }

    }
}
