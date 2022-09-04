
package com.example.Profile.AddAddressResponse;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResponseAddAddress {

    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("location_id")
    @Expose
    private Integer locationId;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

}
