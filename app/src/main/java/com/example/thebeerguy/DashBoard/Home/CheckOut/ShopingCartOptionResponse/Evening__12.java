
package com.example.thebeerguy.DashBoard.Home.CheckOut.ShopingCartOptionResponse;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Evening__12 {

    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("is_busy")
    @Expose
    private Object isBusy;
    @SerializedName("time_min")
    @Expose
    private String timeMin;
    @SerializedName("time_max")
    @Expose
    private String timeMax;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getIsBusy() {
        return isBusy;
    }

    public void setIsBusy(Object isBusy) {
        this.isBusy = isBusy;
    }

    public String getTimeMin() {
        return timeMin;
    }

    public void setTimeMin(String timeMin) {
        this.timeMin = timeMin;
    }

    public String getTimeMax() {
        return timeMax;
    }

    public void setTimeMax(String timeMax) {
        this.timeMax = timeMax;
    }

}
