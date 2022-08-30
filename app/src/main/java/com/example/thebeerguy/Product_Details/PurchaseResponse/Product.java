
package com.example.thebeerguy.Product_Details.PurchaseResponse;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Product {

    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("package_id")
    @Expose
    private String packageId;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("quantity")
    @Expose
    private String quantity;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}
