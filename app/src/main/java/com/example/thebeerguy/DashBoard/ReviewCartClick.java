package com.example.thebeerguy.DashBoard;

import android.widget.TextView;

public interface ReviewCartClick {
    public void increase(int position, TextView amount, TextView quanti);

    public void decrease(int position, TextView amount, TextView quanti);
}
