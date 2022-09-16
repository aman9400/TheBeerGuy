
package com.example.thebeerguy.DashBoard.Home.CheckOut.ShopingCartOptionResponse;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class _20220924 {

    @SerializedName("morning")
    @Expose
    private Morning__8 morning;
    @SerializedName("early_afternoon")
    @Expose
    private EarlyAfternoon__8 earlyAfternoon;
    @SerializedName("late_afternoon")
    @Expose
    private LateAfternoon__8 lateAfternoon;
    @SerializedName("evening")
    @Expose
    private Evening__8 evening;

    public Morning__8 getMorning() {
        return morning;
    }

    public void setMorning(Morning__8 morning) {
        this.morning = morning;
    }

    public EarlyAfternoon__8 getEarlyAfternoon() {
        return earlyAfternoon;
    }

    public void setEarlyAfternoon(EarlyAfternoon__8 earlyAfternoon) {
        this.earlyAfternoon = earlyAfternoon;
    }

    public LateAfternoon__8 getLateAfternoon() {
        return lateAfternoon;
    }

    public void setLateAfternoon(LateAfternoon__8 lateAfternoon) {
        this.lateAfternoon = lateAfternoon;
    }

    public Evening__8 getEvening() {
        return evening;
    }

    public void setEvening(Evening__8 evening) {
        this.evening = evening;
    }

}
