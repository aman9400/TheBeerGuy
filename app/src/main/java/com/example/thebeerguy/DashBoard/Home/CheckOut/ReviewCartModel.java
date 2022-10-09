package com.example.thebeerguy.DashBoard.Home.CheckOut;

import com.example.thebeerguy.DashBoard.ResponseJson.ProductReq;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewCartModel {
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

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("buzzer")
    @Expose
    private String buzzer;

    @SerializedName("is_business")
    @Expose
    private String is_business;

    @SerializedName("is_hotel")
    @Expose
    private String is_hotel;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("schedule_date")
    @Expose
    private String schedule_date;

    @SerializedName("schedule_time")
    @Expose
    private String schedule_time;

    @SerializedName("gift_card_note")
    @Expose
    private String gift_card_note;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("phone")
    @Expose
    private String phone;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBuzzer() {
        return buzzer;
    }

    public void setBuzzer(String buzzer) {
        this.buzzer = buzzer;
    }

    public String getIs_business() {
        return is_business;
    }

    public void setIs_business(String is_business) {
        this.is_business = is_business;
    }

    public String getIs_hotel() {
        return is_hotel;
    }

    public void setIs_hotel(String is_hotel) {
        this.is_hotel = is_hotel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchedule_date() {
        return schedule_date;
    }

    public void setSchedule_date(String schedule_date) {
        this.schedule_date = schedule_date;
    }

    public String getSchedule_time() {
        return schedule_time;
    }

    public void setSchedule_time(String schedule_time) {
        this.schedule_time = schedule_time;
    }

    public String getGift_card_note() {
        return gift_card_note;
    }

    public void setGift_card_note(String gift_card_note) {
        this.gift_card_note = gift_card_note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<ProductReq> getProducts() {
        return products;
    }

    public void setProducts(List<ProductReq> products) {
        this.products = products;
    }
}
