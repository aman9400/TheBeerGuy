
package com.example.thebeerguy.DashBoard.Home.SubCategory.FilterResponse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Output {

    @SerializedName("selected")
    @Expose
    private List<Object> selected = null;
    @SerializedName("options")
    @Expose
    private List<Option> options = null;

    public List<Object> getSelected() {
        return selected;
    }

    public void setSelected(List<Object> selected) {
        this.selected = selected;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

}
