
package com.example.thebeerguy.DashBoard.ResponseJson.searchResponse;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResponseSearch {

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
