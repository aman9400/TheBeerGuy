
package com.example.thebeerguy.DashBoard.Home.ResponseSearch;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("tbg_product_id")
    @Expose
    private Integer tbgProductId;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("packages")
    @Expose
    private List<Package> packages = null;
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
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("tasting_note")
    @Expose
    private Boolean tastingNote;
    @SerializedName("serving_suggestion")
    @Expose
    private Boolean servingSuggestion;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("brewer")
    @Expose
    private String brewer;
    @SerializedName("price_per_ml")
    @Expose
    private String pricePerMl;
    @SerializedName("alcohol_content")
    @Expose
    private String alcoholContent;
    @SerializedName("sugar_content")
    @Expose
    private String sugarContent;
    @SerializedName("sweetness_descr")
    @Expose
    private String sweetnessDescr;
    @SerializedName("vintage_year")
    @Expose
    private String vintageYear;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("is_kosher")
    @Expose
    private Integer isKosher;
    @SerializedName("is_vqa")
    @Expose
    private Integer isVqa;
    @SerializedName("is_vintages")
    @Expose
    private Integer isVintages;
    @SerializedName("is_onsale")
    @Expose
    private Integer isOnsale;
    @SerializedName("max_reward_point_bonus")
    @Expose
    private Integer maxRewardPointBonus;
    @SerializedName("is_recent")
    @Expose
    private Integer isRecent;
    @SerializedName("is_popular")
    @Expose
    private Integer isPopular;
    @SerializedName("popularity")
    @Expose
    private Integer popularity;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("min_price")
    @Expose
    private String minPrice;
    @SerializedName("max_price")
    @Expose
    private String maxPrice;
    @SerializedName("common_price")
    @Expose
    private String commonPrice;
    @SerializedName("common_package_id")
    @Expose
    private Integer commonPackageId;
    @SerializedName("create_date")
    @Expose
    private String createDate;
    @SerializedName("has_rated")
    @Expose
    private Boolean hasRated;
    @SerializedName("has_faved")
    @Expose
    private Boolean hasFaved;

    public Integer getTbgProductId() {
        return tbgProductId;
    }

    public void setTbgProductId(Integer tbgProductId) {
        this.tbgProductId = tbgProductId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Package> getPackages() {
        return packages;
    }

    public void setPackages(List<Package> packages) {
        this.packages = packages;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getTastingNote() {
        return tastingNote;
    }

    public void setTastingNote(Boolean tastingNote) {
        this.tastingNote = tastingNote;
    }

    public Boolean getServingSuggestion() {
        return servingSuggestion;
    }

    public void setServingSuggestion(Boolean servingSuggestion) {
        this.servingSuggestion = servingSuggestion;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBrewer() {
        return brewer;
    }

    public void setBrewer(String brewer) {
        this.brewer = brewer;
    }

    public String getPricePerMl() {
        return pricePerMl;
    }

    public void setPricePerMl(String pricePerMl) {
        this.pricePerMl = pricePerMl;
    }

    public String getAlcoholContent() {
        return alcoholContent;
    }

    public void setAlcoholContent(String alcoholContent) {
        this.alcoholContent = alcoholContent;
    }

    public String getSugarContent() {
        return sugarContent;
    }

    public void setSugarContent(String sugarContent) {
        this.sugarContent = sugarContent;
    }

    public String getSweetnessDescr() {
        return sweetnessDescr;
    }

    public void setSweetnessDescr(String sweetnessDescr) {
        this.sweetnessDescr = sweetnessDescr;
    }

    public String getVintageYear() {
        return vintageYear;
    }

    public void setVintageYear(String vintageYear) {
        this.vintageYear = vintageYear;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getIsKosher() {
        return isKosher;
    }

    public void setIsKosher(Integer isKosher) {
        this.isKosher = isKosher;
    }

    public Integer getIsVqa() {
        return isVqa;
    }

    public void setIsVqa(Integer isVqa) {
        this.isVqa = isVqa;
    }

    public Integer getIsVintages() {
        return isVintages;
    }

    public void setIsVintages(Integer isVintages) {
        this.isVintages = isVintages;
    }

    public Integer getIsOnsale() {
        return isOnsale;
    }

    public void setIsOnsale(Integer isOnsale) {
        this.isOnsale = isOnsale;
    }

    public Integer getMaxRewardPointBonus() {
        return maxRewardPointBonus;
    }

    public void setMaxRewardPointBonus(Integer maxRewardPointBonus) {
        this.maxRewardPointBonus = maxRewardPointBonus;
    }

    public Integer getIsRecent() {
        return isRecent;
    }

    public void setIsRecent(Integer isRecent) {
        this.isRecent = isRecent;
    }

    public Integer getIsPopular() {
        return isPopular;
    }

    public void setIsPopular(Integer isPopular) {
        this.isPopular = isPopular;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getCommonPrice() {
        return commonPrice;
    }

    public void setCommonPrice(String commonPrice) {
        this.commonPrice = commonPrice;
    }

    public Integer getCommonPackageId() {
        return commonPackageId;
    }

    public void setCommonPackageId(Integer commonPackageId) {
        this.commonPackageId = commonPackageId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Boolean getHasRated() {
        return hasRated;
    }

    public void setHasRated(Boolean hasRated) {
        this.hasRated = hasRated;
    }

    public Boolean getHasFaved() {
        return hasFaved;
    }

    public void setHasFaved(Boolean hasFaved) {
        this.hasFaved = hasFaved;
    }

}
