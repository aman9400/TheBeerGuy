
package com.example.thebeerguy.DashBoard.Home.CheckOut.ShopingCartOptionResponse;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class _20220925 {

    @SerializedName("morning")
    @Expose
    private Morning__9 morning;
    @SerializedName("early_afternoon")
    @Expose
    private EarlyAfternoon__9 earlyAfternoon;
    @SerializedName("late_afternoon")
    @Expose
    private LateAfternoon__9 lateAfternoon;
    @SerializedName("evening")
    @Expose
    private Evening__9 evening;

    public Morning__9 getMorning() {
        return morning;
    }

    public void setMorning(Morning__9 morning) {
        this.morning = morning;
    }

    public EarlyAfternoon__9 getEarlyAfternoon() {
        return earlyAfternoon;
    }

    public void setEarlyAfternoon(EarlyAfternoon__9 earlyAfternoon) {
        this.earlyAfternoon = earlyAfternoon;
    }

    public LateAfternoon__9 getLateAfternoon() {
        return lateAfternoon;
    }

    public void setLateAfternoon(LateAfternoon__9 lateAfternoon) {
        this.lateAfternoon = lateAfternoon;
    }

    public Evening__9 getEvening() {
        return evening;
    }

    public void setEvening(Evening__9 evening) {
        this.evening = evening;
    }

}
