package com.example.thebeerguy.DashBoard.Home.CheckOut;

import com.example.thebeerguy.DashBoard.ResponseJson.ProductReq;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaymentReq {
    @SerializedName("api_key")
    @Expose
    private String apiKey;

    @SerializedName("ext_shopping_cart_id")
    @Expose
    private Integer extShoppingCartId;

    @SerializedName("ext_customer_id")
    @Expose
    private Integer extCustomerId;

    @SerializedName("ext_location_id")
    @Expose
    private Integer extLocationId;

    @SerializedName("products")
    @Expose
    private List<ProductReq> products = null;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Integer getExtShoppingCartId() {
        return extShoppingCartId;
    }

    public void setExtShoppingCartId(Integer extShoppingCartId) {
        this.extShoppingCartId = extShoppingCartId;
    }

    public Integer getExtCustomerId() {
        return extCustomerId;
    }

    public void setExtCustomerId(Integer extCustomerId) {
        this.extCustomerId = extCustomerId;
    }

    public Integer getExtLocationId() {
        return extLocationId;
    }

    public void setExtLocationId(Integer extLocationId) {
        this.extLocationId = extLocationId;
    }

    public List<ProductReq> getProducts() {
        return products;
    }

    public void setProducts(List<ProductReq> products) {
        this.products = products;
    }
}
