
package com.example.thebeerguy.DashBoard.PurchaseHistoryResponse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResponsePurchaseHistory {

    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("purchases")
    @Expose
    private List<Object> purchases = null;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Object> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Object> purchases) {
        this.purchases = purchases;
    }

}
