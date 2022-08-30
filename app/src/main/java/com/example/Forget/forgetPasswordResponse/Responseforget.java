
package com.example.Forget.forgetPasswordResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Responseforget {

    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("errors")
    @Expose
    private List<String> errors = null;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

}