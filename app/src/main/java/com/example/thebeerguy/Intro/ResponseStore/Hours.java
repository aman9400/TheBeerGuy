
package com.example.thebeerguy.Intro.ResponseStore;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hours {

    @SerializedName("Fri")
    @Expose
    private List<String> fri = null;
    @SerializedName("Mon")
    @Expose
    private List<String> mon = null;
    @SerializedName("Sat")
    @Expose
    private List<String> sat = null;
    @SerializedName("Sun")
    @Expose
    private List<String> sun = null;
    @SerializedName("Thu")
    @Expose
    private List<String> thu = null;
    @SerializedName("Tue")
    @Expose
    private List<String> tue = null;
    @SerializedName("Wed")
    @Expose
    private List<String> wed = null;

    public List<String> getFri() {
        return fri;
    }

    public void setFri(List<String> fri) {
        this.fri = fri;
    }

    public List<String> getMon() {
        return mon;
    }

    public void setMon(List<String> mon) {
        this.mon = mon;
    }

    public List<String> getSat() {
        return sat;
    }

    public void setSat(List<String> sat) {
        this.sat = sat;
    }

    public List<String> getSun() {
        return sun;
    }

    public void setSun(List<String> sun) {
        this.sun = sun;
    }

    public List<String> getThu() {
        return thu;
    }

    public void setThu(List<String> thu) {
        this.thu = thu;
    }

    public List<String> getTue() {
        return tue;
    }

    public void setTue(List<String> tue) {
        this.tue = tue;
    }

    public List<String> getWed() {
        return wed;
    }

    public void setWed(List<String> wed) {
        this.wed = wed;
    }

}
