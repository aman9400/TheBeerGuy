
package com.example.thebeerguy.DashBoard.Home.CheckOut.ShopingCartOptionResponse;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class _20220919 {

    @SerializedName("morning")
    @Expose
    private Morning__3 morning;
    @SerializedName("early_afternoon")
    @Expose
    private EarlyAfternoon__3 earlyAfternoon;
    @SerializedName("late_afternoon")
    @Expose
    private LateAfternoon__3 lateAfternoon;
    @SerializedName("evening")
    @Expose
    private Evening__3 evening;

    public Morning__3 getMorning() {
        return morning;
    }

    public void setMorning(Morning__3 morning) {
        this.morning = morning;
    }

    public EarlyAfternoon__3 getEarlyAfternoon() {
        return earlyAfternoon;
    }

    public void setEarlyAfternoon(EarlyAfternoon__3 earlyAfternoon) {
        this.earlyAfternoon = earlyAfternoon;
    }

    public LateAfternoon__3 getLateAfternoon() {
        return lateAfternoon;
    }

    public void setLateAfternoon(LateAfternoon__3 lateAfternoon) {
        this.lateAfternoon = lateAfternoon;
    }

    public Evening__3 getEvening() {
        return evening;
    }

    public void setEvening(Evening__3 evening) {
        this.evening = evening;
    }

}
