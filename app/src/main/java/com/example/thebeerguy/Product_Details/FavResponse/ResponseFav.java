
package com.example.thebeerguy.Product_Details.FavResponse;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseFav {

    @SerializedName("result")
    @Expose
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
