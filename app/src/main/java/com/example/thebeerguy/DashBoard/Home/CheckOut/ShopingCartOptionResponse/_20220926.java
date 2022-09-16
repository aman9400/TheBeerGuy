
package com.example.thebeerguy.DashBoard.Home.CheckOut.ShopingCartOptionResponse;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class _20220926 {

    @SerializedName("morning")
    @Expose
    private Morning__10 morning;
    @SerializedName("early_afternoon")
    @Expose
    private EarlyAfternoon__10 earlyAfternoon;
    @SerializedName("late_afternoon")
    @Expose
    private LateAfternoon__10 lateAfternoon;
    @SerializedName("evening")
    @Expose
    private Evening__10 evening;

    public Morning__10 getMorning() {
        return morning;
    }

    public void setMorning(Morning__10 morning) {
        this.morning = morning;
    }

    public EarlyAfternoon__10 getEarlyAfternoon() {
        return earlyAfternoon;
    }

    public void setEarlyAfternoon(EarlyAfternoon__10 earlyAfternoon) {
        this.earlyAfternoon = earlyAfternoon;
    }

    public LateAfternoon__10 getLateAfternoon() {
        return lateAfternoon;
    }

    public void setLateAfternoon(LateAfternoon__10 lateAfternoon) {
        this.lateAfternoon = lateAfternoon;
    }

    public Evening__10 getEvening() {
        return evening;
    }

    public void setEvening(Evening__10 evening) {
        this.evening = evening;
    }

}
