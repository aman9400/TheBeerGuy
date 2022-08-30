package com.example.Profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thebeerguy.R;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.MyviewHolder> {
    
    Context context;
    List<AddressModel> addressList;

    public AddressAdapter(Context context, List<AddressModel> addressList) {
        this.context = context;
        this.addressList = addressList;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        
        return new AddressAdapter.MyviewHolder(LayoutInflater.from(context).inflate(R.layout.address_recycler,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {

        AddressModel addressModel = addressList.get(position);

        holder.address_textView_text1.setText(addressModel.getText1());
        holder.address_textView_text2.setText(addressModel.getText2());
        holder.address_imageView_image.setImageResource(addressModel.getImage1());
        holder.address_imageView_edit.setImageResource(addressModel.getImage2());
        holder.address_imageView_delete.setImageResource(addressModel.getImage3());


    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        ImageView address_imageView_image, address_imageView_edit, address_imageView_delete;
        TextView address_textView_text1, address_textView_text2;
        
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            address_imageView_image = itemView.findViewById(R.id.address_imageView_image);
            address_imageView_edit = itemView.findViewById(R.id.address_imageView_edit);
            address_imageView_delete = itemView.findViewById(R.id.address_imageView_delete);
            address_textView_text1 = itemView.findViewById(R.id.address_textView_text1);
            address_textView_text2 = itemView.findViewById(R.id.address_textView_text2);
        }
    }
}
