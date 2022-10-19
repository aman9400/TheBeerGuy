package com.example.thebeerguy.DashBoard;

import com.example.thebeerguy.DashBoard.ResponseJson.ProductReq;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecentProductRequest {
    @SerializedName("api_key")
    @Expose
    private String apiKey;
    @SerializedName("is_recent")
    @Expose
    private Integer is_recent;

    @SerializedName("products")
    @Expose
    private List<ProductReq> products = null;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Integer getIs_recent() {
        return is_recent;
    }

    public void setIs_recent(Integer is_recent) {
        this.is_recent = is_recent;
    }

    public List<ProductReq> getProducts() {
        return products;
    }

    public void setProducts(List<ProductReq> products) {
        this.products = products;
    }
}
