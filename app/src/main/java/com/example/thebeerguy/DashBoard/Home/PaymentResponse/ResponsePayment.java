
package com.example.thebeerguy.DashBoard.Home.PaymentResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResponsePayment {

    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("purchase_id")
    @Expose
    private Integer purchaseId;
    @SerializedName("product_amount")
    @Expose
    private String productAmount;
    @SerializedName("coupon_amount")
    @Expose
    private String couponAmount;
    @SerializedName("promo_amount")
    @Expose
    private String promoAmount;
    @SerializedName("drv_tip_amount")
    @Expose
    private String drvTipAmount;
    @SerializedName("delivery_fee")
    @Expose
    private String deliveryFee;
    @SerializedName("hst_amount")
    @Expose
    private String hstAmount;
    @SerializedName("ccard_processing_fee")
    @Expose
    private String ccardProcessingFee;
    @SerializedName("total_amount")
    @Expose
    private String totalAmount;
    @SerializedName("delivery_eta")
    @Expose
    private String deliveryEta;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(String productAmount) {
        this.productAmount = productAmount;
    }

    public String getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(String couponAmount) {
        this.couponAmount = couponAmount;
    }

    public String getPromoAmount() {
        return promoAmount;
    }

    public void setPromoAmount(String promoAmount) {
        this.promoAmount = promoAmount;
    }

    public String getDrvTipAmount() {
        return drvTipAmount;
    }

    public void setDrvTipAmount(String drvTipAmount) {
        this.drvTipAmount = drvTipAmount;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getHstAmount() {
        return hstAmount;
    }

    public void setHstAmount(String hstAmount) {
        this.hstAmount = hstAmount;
    }

    public String getCcardProcessingFee() {
        return ccardProcessingFee;
    }

    public void setCcardProcessingFee(String ccardProcessingFee) {
        this.ccardProcessingFee = ccardProcessingFee;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDeliveryEta() {
        return deliveryEta;
    }

    public void setDeliveryEta(String deliveryEta) {
        this.deliveryEta = deliveryEta;
    }

}
