
package com.example.thebeerguy.Product_Details.AddToCartResponse;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResponseAddToCart {

    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("shopping_cart_id")
    @Expose
    private Integer shoppingCartId;
    @SerializedName("product_amount")
    @Expose
    private Float productAmount;
    @SerializedName("drv_tip_amount")
    @Expose
    private String drvTipAmount;
    @SerializedName("delivery_fee")
    @Expose
    private Double deliveryFee;
    @SerializedName("hst_amount")
    @Expose
    private Double hstAmount;
    @SerializedName("total_amount")
    @Expose
    private Float totalAmount;
    @SerializedName("delivery_eta")
    @Expose
    private String deliveryEta;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Integer shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public Float getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Float productAmount) {
        this.productAmount = productAmount;
    }

    public String getDrvTipAmount() {
        return drvTipAmount;
    }

    public void setDrvTipAmount(String drvTipAmount) {
        this.drvTipAmount = drvTipAmount;
    }

    public Double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Double getHstAmount() {
        return hstAmount;
    }

    public void setHstAmount(Double hstAmount) {
        this.hstAmount = hstAmount;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDeliveryEta() {
        return deliveryEta;
    }

    public void setDeliveryEta(String deliveryEta) {
        this.deliveryEta = deliveryEta;
    }

}
