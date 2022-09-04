
package com.example.thebeerguy.Intro.ResponseStore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseStore {

    @SerializedName("tbg_store_location_id")
    @Expose
    private Integer tbgStoreLocationId;
    @SerializedName("store_location_id")
    @Expose
    private Integer storeLocationId;
    @SerializedName("store_abbrev")
    @Expose
    private String storeAbbrev;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("ext_id")
    @Expose
    private String extId;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("addr1")
    @Expose
    private String addr1;
    @SerializedName("zip")
    @Expose
    private String zip;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("hours")
    @Expose
    private Hours hours;

    public Integer getTbgStoreLocationId() {
        return tbgStoreLocationId;
    }

    public void setTbgStoreLocationId(Integer tbgStoreLocationId) {
        this.tbgStoreLocationId = tbgStoreLocationId;
    }

    public Integer getStoreLocationId() {
        return storeLocationId;
    }

    public void setStoreLocationId(Integer storeLocationId) {
        this.storeLocationId = storeLocationId;
    }

    public String getStoreAbbrev() {
        return storeAbbrev;
    }

    public void setStoreAbbrev(String storeAbbrev) {
        this.storeAbbrev = storeAbbrev;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Hours getHours() {
        return hours;
    }

    public void setHours(Hours hours) {
        this.hours = hours;
    }

}
