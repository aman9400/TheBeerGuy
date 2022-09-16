
package com.example.thebeerguy.DashBoard.Home.CheckOut.ShopingCartOptionResponse;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class _20220918 {

    @SerializedName("morning")
    @Expose
    private Morning__2 morning;
    @SerializedName("early_afternoon")
    @Expose
    private EarlyAfternoon__2 earlyAfternoon;
    @SerializedName("late_afternoon")
    @Expose
    private LateAfternoon__2 lateAfternoon;
    @SerializedName("evening")
    @Expose
    private Evening__2 evening;

    public Morning__2 getMorning() {
        return morning;
    }

    public void setMorning(Morning__2 morning) {
        this.morning = morning;
    }

    public EarlyAfternoon__2 getEarlyAfternoon() {
        return earlyAfternoon;
    }

    public void setEarlyAfternoon(EarlyAfternoon__2 earlyAfternoon) {
        this.earlyAfternoon = earlyAfternoon;
    }

    public LateAfternoon__2 getLateAfternoon() {
        return lateAfternoon;
    }

    public void setLateAfternoon(LateAfternoon__2 lateAfternoon) {
        this.lateAfternoon = lateAfternoon;
    }

    public Evening__2 getEvening() {
        return evening;
    }

    public void setEvening(Evening__2 evening) {
        this.evening = evening;
    }

}
