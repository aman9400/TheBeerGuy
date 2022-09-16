
package com.example.thebeerguy.DashBoard.Home.CheckOut.ShopingCartOptionResponse;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class _20220929 {

    @SerializedName("morning")
    @Expose
    private Morning__13 morning;
    @SerializedName("early_afternoon")
    @Expose
    private EarlyAfternoon__13 earlyAfternoon;
    @SerializedName("late_afternoon")
    @Expose
    private LateAfternoon__13 lateAfternoon;
    @SerializedName("evening")
    @Expose
    private Evening__13 evening;

    public Morning__13 getMorning() {
        return morning;
    }

    public void setMorning(Morning__13 morning) {
        this.morning = morning;
    }

    public EarlyAfternoon__13 getEarlyAfternoon() {
        return earlyAfternoon;
    }

    public void setEarlyAfternoon(EarlyAfternoon__13 earlyAfternoon) {
        this.earlyAfternoon = earlyAfternoon;
    }

    public LateAfternoon__13 getLateAfternoon() {
        return lateAfternoon;
    }

    public void setLateAfternoon(LateAfternoon__13 lateAfternoon) {
        this.lateAfternoon = lateAfternoon;
    }

    public Evening__13 getEvening() {
        return evening;
    }

    public void setEvening(Evening__13 evening) {
        this.evening = evening;
    }

}
