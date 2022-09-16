
package com.example.thebeerguy.DashBoard.Home.CheckOut.ShopingCartOptionResponse;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class _20220917 {

    @SerializedName("morning")
    @Expose
    private Morning__1 morning;
    @SerializedName("early_afternoon")
    @Expose
    private EarlyAfternoon__1 earlyAfternoon;
    @SerializedName("late_afternoon")
    @Expose
    private LateAfternoon__1 lateAfternoon;
    @SerializedName("evening")
    @Expose
    private Evening__1 evening;

    public Morning__1 getMorning() {
        return morning;
    }

    public void setMorning(Morning__1 morning) {
        this.morning = morning;
    }

    public EarlyAfternoon__1 getEarlyAfternoon() {
        return earlyAfternoon;
    }

    public void setEarlyAfternoon(EarlyAfternoon__1 earlyAfternoon) {
        this.earlyAfternoon = earlyAfternoon;
    }

    public LateAfternoon__1 getLateAfternoon() {
        return lateAfternoon;
    }

    public void setLateAfternoon(LateAfternoon__1 lateAfternoon) {
        this.lateAfternoon = lateAfternoon;
    }

    public Evening__1 getEvening() {
        return evening;
    }

    public void setEvening(Evening__1 evening) {
        this.evening = evening;
    }

}
