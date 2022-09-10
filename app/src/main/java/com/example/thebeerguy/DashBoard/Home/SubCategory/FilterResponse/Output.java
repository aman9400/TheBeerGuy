
package com.example.thebeerguy.DashBoard.Home.SubCategory.FilterResponse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Output {

    @SerializedName("selected")
    @Expose
    private Selected selected;
    @SerializedName("options")
    @Expose
    private List<Option> options = null;

    public Selected getSelected() {
        return selected;
    }

    public void setSelected(Selected selected) {
        this.selected = selected;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

}
