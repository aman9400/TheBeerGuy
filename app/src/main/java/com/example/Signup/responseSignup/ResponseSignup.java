
package com.example.Signup.responseSignup;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResponseSignup {

    @SerializedName("jwt")
    @Expose
    private String jwt;
    @SerializedName("ref")
    @Expose
    private String ref;
    @SerializedName("data")
    @Expose
    private Data data;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
