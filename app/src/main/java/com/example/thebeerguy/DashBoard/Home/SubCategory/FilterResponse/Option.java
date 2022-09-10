
package com.example.thebeerguy.DashBoard.Home.SubCategory.FilterResponse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Option {

    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("var")
    @Expose
    private String var;
    @SerializedName("vals")
    @Expose
    private List<String> vals = null;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public List<String> getVals() {
        return vals;
    }

    public void setVals(List<String> vals) {
        this.vals = vals;
    }

}
