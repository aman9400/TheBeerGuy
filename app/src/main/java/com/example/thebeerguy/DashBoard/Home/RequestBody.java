package com.example.thebeerguy.DashBoard.Home;

public class RequestBody {
    String api_key;
    int type_id;
    int isTen;

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getIsTen() {
        return isTen;
    }

    public void setIsTen(int isTen) {
        this.isTen = isTen;
    }
}
