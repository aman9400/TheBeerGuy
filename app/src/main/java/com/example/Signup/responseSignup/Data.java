
package com.example.Signup.responseSignup;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("customer")
    @Expose
    private Customer customer;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("nearby_store_location_ids")
    @Expose
    private List<Integer> nearbyStoreLocationIds = null;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Integer> getNearbyStoreLocationIds() {
        return nearbyStoreLocationIds;
    }

    public void setNearbyStoreLocationIds(List<Integer> nearbyStoreLocationIds) {
        this.nearbyStoreLocationIds = nearbyStoreLocationIds;
    }

}
