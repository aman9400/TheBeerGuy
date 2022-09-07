package com.example.thebeerguy.DashBoard.Home.CheckOut;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thebeerguy.Product_Details.ProductDetailsDialogAdapter;
import com.example.thebeerguy.Product_Details.ProductDetailsResponse.Package;
import com.example.thebeerguy.R;

import java.util.List;

public class CheckoutDialogAdapter extends RecyclerView.Adapter<CheckoutDialogAdapter.myViewHolder>{

    Context context;
    List<CheckOutModel> checkOutModelList;

    public CheckoutDialogAdapter(Context context, List<CheckOutModel> checkOutModelList) {
        this.context = context;
        this.checkOutModelList = checkOutModelList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new CheckoutDialogAdapter.myViewHolder(LayoutInflater.from(context).inflate(R.layout.checkout_dialog_recycler,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        CheckOutModel checkOutModel = checkOutModelList.get(position);

        holder.checkout_dialog_recycler_textView.setText(checkOutModel.getTiming());

    }

    @Override
    public int getItemCount() {
        return checkOutModelList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        RadioButton checkout_dialog_recycler_radiobtn;
        TextView checkout_dialog_recycler_textView;
        ConstraintLayout checkout_package_dialog;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            checkout_dialog_recycler_radiobtn = itemView.findViewById(R.id.checkout_dialog_recycler_radiobtn);
            checkout_dialog_recycler_textView = itemView.findViewById(R.id.checkout_dialog_recycler_textView);
            checkout_package_dialog = itemView.findViewById(R.id.checkout_package_dialog);

        }
    }
}