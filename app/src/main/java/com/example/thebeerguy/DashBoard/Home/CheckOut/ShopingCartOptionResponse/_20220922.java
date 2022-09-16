
package com.example.thebeerguy.DashBoard.Home.CheckOut.ShopingCartOptionResponse;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class _20220922 {

    @SerializedName("morning")
    @Expose
    private Morning__6 morning;
    @SerializedName("early_afternoon")
    @Expose
    private EarlyAfternoon__6 earlyAfternoon;
    @SerializedName("late_afternoon")
    @Expose
    private LateAfternoon__6 lateAfternoon;
    @SerializedName("evening")
    @Expose
    private Evening__6 evening;

    public Morning__6 getMorning() {
        return morning;
    }

    public void setMorning(Morning__6 morning) {
        this.morning = morning;
    }

    public EarlyAfternoon__6 getEarlyAfternoon() {
        return earlyAfternoon;
    }

    public void setEarlyAfternoon(EarlyAfternoon__6 earlyAfternoon) {
        this.earlyAfternoon = earlyAfternoon;
    }

    public LateAfternoon__6 getLateAfternoon() {
        return lateAfternoon;
    }

    public void setLateAfternoon(LateAfternoon__6 lateAfternoon) {
        this.lateAfternoon = lateAfternoon;
    }

    public Evening__6 getEvening() {
        return evening;
    }

    public void setEvening(Evening__6 evening) {
        this.evening = evening;
    }

}
