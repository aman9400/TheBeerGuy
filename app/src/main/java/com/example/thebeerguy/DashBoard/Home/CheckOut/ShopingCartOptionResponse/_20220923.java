
package com.example.thebeerguy.DashBoard.Home.CheckOut.ShopingCartOptionResponse;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class _20220923 {

    @SerializedName("morning")
    @Expose
    private Morning__7 morning;
    @SerializedName("early_afternoon")
    @Expose
    private EarlyAfternoon__7 earlyAfternoon;
    @SerializedName("late_afternoon")
    @Expose
    private LateAfternoon__7 lateAfternoon;
    @SerializedName("evening")
    @Expose
    private Evening__7 evening;

    public Morning__7 getMorning() {
        return morning;
    }

    public void setMorning(Morning__7 morning) {
        this.morning = morning;
    }

    public EarlyAfternoon__7 getEarlyAfternoon() {
        return earlyAfternoon;
    }

    public void setEarlyAfternoon(EarlyAfternoon__7 earlyAfternoon) {
        this.earlyAfternoon = earlyAfternoon;
    }

    public LateAfternoon__7 getLateAfternoon() {
        return lateAfternoon;
    }

    public void setLateAfternoon(LateAfternoon__7 lateAfternoon) {
        this.lateAfternoon = lateAfternoon;
    }

    public Evening__7 getEvening() {
        return evening;
    }

    public void setEvening(Evening__7 evening) {
        this.evening = evening;
    }

}
