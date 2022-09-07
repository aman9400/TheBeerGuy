package com.example.Databse;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "price")
public class ProductPrice {

    @PrimaryKey()
    private int id;

    @ColumnInfo(name = "result")
    private String result;

    @ColumnInfo(name = "shoppingCartId")
    private String shoppingCartId;

    @ColumnInfo(name = "productAmount")
    private String productAmount;

    @ColumnInfo(name = "drvTipAmount")
    private String drvTipAmount;

    @ColumnInfo(name = "deliveryFee")
    private String deliveryFee;

    @ColumnInfo(name = "hstAmount")
    private String hstAmount;

    @ColumnInfo(name = "totalAmount")
    private String totalAmount;

    @ColumnInfo(name = "deliveryEta")
    private String deliveryEta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(String shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public String getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(String productAmount) {
        this.productAmount = productAmount;
    }

    public String getDrvTipAmount() {
        return drvTipAmount;
    }

    public void setDrvTipAmount(String drvTipAmount) {
        this.drvTipAmount = drvTipAmount;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getHstAmount() {
        return hstAmount;
    }

    public void setHstAmount(String hstAmount) {
        this.hstAmount = hstAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDeliveryEta() {
        return deliveryEta;
    }

    public void setDeliveryEta(String deliveryEta) {
        this.deliveryEta = deliveryEta;
    }
}
