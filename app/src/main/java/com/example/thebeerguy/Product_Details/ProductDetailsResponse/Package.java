
package com.example.thebeerguy.Product_Details.ProductDetailsResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Package {

    @SerializedName("tbg_package_id")
    @Expose
    private Integer tbgPackageId;
    @SerializedName("package_id")
    @Expose
    private Integer packageId;
    @SerializedName("store_abbrev")
    @Expose
    private String storeAbbrev;
    @SerializedName("store_product_id")
    @Expose
    private String storeProductId;
    @SerializedName("alt_store_product_id")
    @Expose
    private String altStoreProductId;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("container")
    @Expose
    private String container;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("presale_price")
    @Expose
    private String presalePrice;
    @SerializedName("onsale_until")
    @Expose
    private Object onsaleUntil;

    public Integer getTbgPackageId() {
        return tbgPackageId;
    }

    public void setTbgPackageId(Integer tbgPackageId) {
        this.tbgPackageId = tbgPackageId;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public String getStoreAbbrev() {
        return storeAbbrev;
    }

    public void setStoreAbbrev(String storeAbbrev) {
        this.storeAbbrev = storeAbbrev;
    }

    public String getStoreProductId() {
        return storeProductId;
    }

    public void setStoreProductId(String storeProductId) {
        this.storeProductId = storeProductId;
    }

    public String getAltStoreProductId() {
        return altStoreProductId;
    }

    public void setAltStoreProductId(String altStoreProductId) {
        this.altStoreProductId = altStoreProductId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPresalePrice() {
        return presalePrice;
    }

    public void setPresalePrice(String presalePrice) {
        this.presalePrice = presalePrice;
    }

    public Object getOnsaleUntil() {
        return onsaleUntil;
    }

    public void setOnsaleUntil(Object onsaleUntil) {
        this.onsaleUntil = onsaleUntil;
    }

}
