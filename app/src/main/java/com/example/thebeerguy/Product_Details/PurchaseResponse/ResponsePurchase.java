
package com.example.thebeerguy.Product_Details.PurchaseResponse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResponsePurchase {

    @SerializedName("api_key")
    @Expose
    private String apiKey;
    @SerializedName("ext_purchase_id")
    @Expose
    private Integer extPurchaseId;
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
    private List<Product> products = null;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Integer getExtPurchaseId() {
        return extPurchaseId;
    }

    public void setExtPurchaseId(Integer extPurchaseId) {
        this.extPurchaseId = extPurchaseId;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
