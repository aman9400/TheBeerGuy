
package com.example.thebeerguy.DashBoard.Home.CheckOut.ShopingCartOptionResponse;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class _20220921 {

    @SerializedName("morning")
    @Expose
    private Morning__5 morning;
    @SerializedName("early_afternoon")
    @Expose
    private EarlyAfternoon__5 earlyAfternoon;
    @SerializedName("late_afternoon")
    @Expose
    private LateAfternoon__5 lateAfternoon;
    @SerializedName("evening")
    @Expose
    private Evening__5 evening;

    public Morning__5 getMorning() {
        return morning;
    }

    public void setMorning(Morning__5 morning) {
        this.morning = morning;
    }

    public EarlyAfternoon__5 getEarlyAfternoon() {
        return earlyAfternoon;
    }

    public void setEarlyAfternoon(EarlyAfternoon__5 earlyAfternoon) {
        this.earlyAfternoon = earlyAfternoon;
    }

    public LateAfternoon__5 getLateAfternoon() {
        return lateAfternoon;
    }

    public void setLateAfternoon(LateAfternoon__5 lateAfternoon) {
        this.lateAfternoon = lateAfternoon;
    }

    public Evening__5 getEvening() {
        return evening;
    }

    public void setEvening(Evening__5 evening) {
        this.evening = evening;
    }

}
