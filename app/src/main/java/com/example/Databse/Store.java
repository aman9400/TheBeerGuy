package com.example.Databse;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// user table
@Entity(tableName = "user")
public class Store {
    @PrimaryKey()
    private int id;

    @ColumnInfo(name = "productID")
    private int productID;

    @ColumnInfo(name = "quantity")
    private int quantity;

    @ColumnInfo(name = "productName")
    private String productName;

    @ColumnInfo(name = "image")
    private String image;

    @ColumnInfo(name = "productPrice")
    private String productPrice;


    @ColumnInfo(name = "packageID")
    private int packageID;

    @ColumnInfo(name = "packageName")
    private String packageName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public int getPackageID() {
        return packageID;
    }

    public void setPackageID(int packageID) {
        this.packageID = packageID;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
