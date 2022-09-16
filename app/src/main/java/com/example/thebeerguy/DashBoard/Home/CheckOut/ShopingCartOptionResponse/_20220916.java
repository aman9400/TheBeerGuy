
package com.example.thebeerguy.DashBoard.Home.CheckOut.ShopingCartOptionResponse;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class _20220916 {

    @SerializedName("morning")
    @Expose
    private Morning morning;
    @SerializedName("early_afternoon")
    @Expose
    private EarlyAfternoon earlyAfternoon;
    @SerializedName("late_afternoon")
    @Expose
    private LateAfternoon lateAfternoon;
    @SerializedName("evening")
    @Expose
    private Evening evening;

    public Morning getMorning() {
        return morning;
    }

    public void setMorning(Morning morning) {
        this.morning = morning;
    }

    public EarlyAfternoon getEarlyAfternoon() {
        return earlyAfternoon;
    }

    public void setEarlyAfternoon(EarlyAfternoon earlyAfternoon) {
        this.earlyAfternoon = earlyAfternoon;
    }

    public LateAfternoon getLateAfternoon() {
        return lateAfternoon;
    }

    public void setLateAfternoon(LateAfternoon lateAfternoon) {
        this.lateAfternoon = lateAfternoon;
    }

    public Evening getEvening() {
        return evening;
    }

    public void setEvening(Evening evening) {
        this.evening = evening;
    }

}
