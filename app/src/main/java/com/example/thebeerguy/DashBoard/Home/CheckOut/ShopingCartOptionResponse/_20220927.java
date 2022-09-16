
package com.example.thebeerguy.DashBoard.Home.CheckOut.ShopingCartOptionResponse;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class _20220927 {

    @SerializedName("morning")
    @Expose
    private Morning__11 morning;
    @SerializedName("early_afternoon")
    @Expose
    private EarlyAfternoon__11 earlyAfternoon;
    @SerializedName("late_afternoon")
    @Expose
    private LateAfternoon__11 lateAfternoon;
    @SerializedName("evening")
    @Expose
    private Evening__11 evening;

    public Morning__11 getMorning() {
        return morning;
    }

    public void setMorning(Morning__11 morning) {
        this.morning = morning;
    }

    public EarlyAfternoon__11 getEarlyAfternoon() {
        return earlyAfternoon;
    }

    public void setEarlyAfternoon(EarlyAfternoon__11 earlyAfternoon) {
        this.earlyAfternoon = earlyAfternoon;
    }

    public LateAfternoon__11 getLateAfternoon() {
        return lateAfternoon;
    }

    public void setLateAfternoon(LateAfternoon__11 lateAfternoon) {
        this.lateAfternoon = lateAfternoon;
    }

    public Evening__11 getEvening() {
        return evening;
    }

    public void setEvening(Evening__11 evening) {
        this.evening = evening;
    }

}
