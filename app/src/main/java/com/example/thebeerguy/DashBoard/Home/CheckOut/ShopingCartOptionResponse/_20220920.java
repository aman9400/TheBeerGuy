
package com.example.thebeerguy.DashBoard.Home.CheckOut.ShopingCartOptionResponse;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class _20220920 {

    @SerializedName("morning")
    @Expose
    private Morning__4 morning;
    @SerializedName("early_afternoon")
    @Expose
    private EarlyAfternoon__4 earlyAfternoon;
    @SerializedName("late_afternoon")
    @Expose
    private LateAfternoon__4 lateAfternoon;
    @SerializedName("evening")
    @Expose
    private Evening__4 evening;

    public Morning__4 getMorning() {
        return morning;
    }

    public void setMorning(Morning__4 morning) {
        this.morning = morning;
    }

    public EarlyAfternoon__4 getEarlyAfternoon() {
        return earlyAfternoon;
    }

    public void setEarlyAfternoon(EarlyAfternoon__4 earlyAfternoon) {
        this.earlyAfternoon = earlyAfternoon;
    }

    public LateAfternoon__4 getLateAfternoon() {
        return lateAfternoon;
    }

    public void setLateAfternoon(LateAfternoon__4 lateAfternoon) {
        this.lateAfternoon = lateAfternoon;
    }

    public Evening__4 getEvening() {
        return evening;
    }

    public void setEvening(Evening__4 evening) {
        this.evening = evening;
    }

}
