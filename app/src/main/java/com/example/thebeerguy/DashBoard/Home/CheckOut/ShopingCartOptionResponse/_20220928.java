
package com.example.thebeerguy.DashBoard.Home.CheckOut.ShopingCartOptionResponse;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class _20220928 {

    @SerializedName("morning")
    @Expose
    private Morning__12 morning;
    @SerializedName("early_afternoon")
    @Expose
    private EarlyAfternoon__12 earlyAfternoon;
    @SerializedName("late_afternoon")
    @Expose
    private LateAfternoon__12 lateAfternoon;
    @SerializedName("evening")
    @Expose
    private Evening__12 evening;

    public Morning__12 getMorning() {
        return morning;
    }

    public void setMorning(Morning__12 morning) {
        this.morning = morning;
    }

    public EarlyAfternoon__12 getEarlyAfternoon() {
        return earlyAfternoon;
    }

    public void setEarlyAfternoon(EarlyAfternoon__12 earlyAfternoon) {
        this.earlyAfternoon = earlyAfternoon;
    }

    public LateAfternoon__12 getLateAfternoon() {
        return lateAfternoon;
    }

    public void setLateAfternoon(LateAfternoon__12 lateAfternoon) {
        this.lateAfternoon = lateAfternoon;
    }

    public Evening__12 getEvening() {
        return evening;
    }

    public void setEvening(Evening__12 evening) {
        this.evening = evening;
    }

}
