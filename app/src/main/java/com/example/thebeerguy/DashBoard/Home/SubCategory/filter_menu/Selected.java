
package com.example.thebeerguy.DashBoard.Home.SubCategory.filter_menu;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Selected {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("sub_category")
    @Expose
    private String subCategory;
    @SerializedName("origin")
    @Expose
    private String origin;
    @SerializedName("region")
    @Expose
    private String region;

    @SerializedName("is_vqa")
    @Expose
    private String is_vqa;

    @SerializedName("is_kosher")
    @Expose
    private String is_kosher;

    @SerializedName("is_vintages")
    @Expose
    private String is_vintages;

 @SerializedName("price_range")
    @Expose
    private String price_range;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getIs_vqa() {
        return is_vqa;
    }

    public void setIs_vqa(String is_vqa) {
        this.is_vqa = is_vqa;
    }

    public String getIs_kosher() {
        return is_kosher;
    }

    public void setIs_kosher(String is_kosher) {
        this.is_kosher = is_kosher;
    }

    public String getIs_vintages() {
        return is_vintages;
    }

    public void setIs_vintages(String is_vintages) {
        this.is_vintages = is_vintages;
    }

    public String getPrice_range() {
        return price_range;
    }

    public void setPrice_range(String price_range) {
        this.price_range = price_range;
    }
}
