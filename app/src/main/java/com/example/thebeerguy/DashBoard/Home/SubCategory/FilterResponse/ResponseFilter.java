
package com.example.thebeerguy.DashBoard.Home.SubCategory.FilterResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResponseFilter {

    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("output")
    @Expose
    private Output output;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Output getOutput() {
        return output;
    }

    public void setOutput(Output output) {
        this.output = output;
    }

}
