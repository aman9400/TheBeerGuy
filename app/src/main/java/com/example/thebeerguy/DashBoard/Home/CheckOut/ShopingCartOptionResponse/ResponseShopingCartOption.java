
package com.example.thebeerguy.DashBoard.Home.CheckOut.ShopingCartOptionResponse;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResponseShopingCartOption {

    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("schedule_options")
    @Expose
    private ScheduleOptions scheduleOptions;
    @SerializedName("payment_methods")
    @Expose
    private PaymentMethods paymentMethods;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ScheduleOptions getScheduleOptions() {
        return scheduleOptions;
    }

    public void setScheduleOptions(ScheduleOptions scheduleOptions) {
        this.scheduleOptions = scheduleOptions;
    }

    public PaymentMethods getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(PaymentMethods paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

}
