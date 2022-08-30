
package com.example.thebeerguy.DashBoard.Home.categoryResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResponseCategory {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("label")
    @Expose
    private String label;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
