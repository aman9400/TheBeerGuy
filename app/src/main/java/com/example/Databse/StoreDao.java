package com.example.Databse;

import androidx.room.Dao;
import androidx.room.Query;

import com.google.android.gms.tasks.Task;
@Dao
public interface StoreDao {



   /*get all data from database*/

    @Query("SELECT * FROM user" )
    Store[] totalStoreData();

    //    /*insert data into database*/
    @Query("INSERT INTO user ( productID, quantity, productName, image, productPrice) VALUES (:productID, :quantity, :name, :image, :price)" )
    long insertIntoTable( int productID, int quantity, String name, String image, String price);


    /*get all data from database*/
    @Query("SELECT COUNT(id) FROM cartnumber" )
    int getCartNumber();

    /*get user data from database*/
    @Query("INSERT INTO cartnumber ( cart_number) VALUES (:number)" )
    long setCartNumber(int number);

//    @Query("SELECT * FROM price" )
//    ProductPrice[] getPriceData();
//
//    //    /*insert data into database*/
//    @Query("INSERT INTO price ( result, shoppingCartId, productAmount, drvTipAmount, deliveryFee, hstAmount, totalAmount, deliveryEta) VALUES (:result, :shoppingCartId, :productAmount, :drvTipAmount, :deliveryFee, :hstAmount, :totalAmount, :deliveryEta)" )
//    long savePriceData(  String result, String shoppingCartId, String productAmount,  String drvTipAmount, String deliveryFee, String hstAmount,  String totalAmount, String deliveryEta);

//
//    /*insert data into database*/
//    @Query("INSERT INTO user ( name, email, phone, password) VALUES (:name, :email, :phone,  :password)" )
//    long insertIntoTable( String name, String email, String phone,  String password );

    /*insert data into database*/
//    @Query("INSERT INTO task (task) VALUES (:task)" )
//    long insertIntoTaskTable(String task);
//
//    /*insert data into database*/
//    @Query("INSERT INTO task (task) VALUES (:task)" )
//    long deleteIntoTaskTable(String task);
//
//
//    /*delete entry from database*/
//    @Query("DELETE FROM task WHERE id =:position" )
//    int deleteData(int position);



}
