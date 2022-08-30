package com.example.thebeerguy.DashBoard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thebeerguy.R;

public class NavBarAdapter extends BaseAdapter {

    private final Context context;
    private final String[] txt;
    private final int[] icon;

    public NavBarAdapter(Context context, String[] txt, int[] icon) {
        this.context = context;
        this.txt = txt;
        this.icon = icon;
    }

    @Override
    public int getCount() {
        return txt.length;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.custom_item_nav_menu, viewGroup, false);

            viewHolder = new ViewHolder();

            viewHolder.imageView = (ImageView) view.findViewById(R.id.iv_icon);
            viewHolder.textView = (TextView) view.findViewById(R.id.icon_txt);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.textView.setText(txt[i]);
        viewHolder.imageView.setImageResource(icon[i]);


        return view;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}