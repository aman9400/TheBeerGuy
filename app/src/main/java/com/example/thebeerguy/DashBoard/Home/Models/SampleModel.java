package com.example.thebeerguy.DashBoard.Home.Models;

public class SampleModel {


   private int image;
   private String name;
    private String  price;
   private String id;

    public SampleModel(int image, String name, String price, String id) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
