package com.example.Databse;

import androidx.room.Dao;
import androidx.room.Query;

import com.google.android.gms.tasks.Task;
@Dao
public interface StoreDao {



   /*get all data from database*/

    @Query("SELECT * FROM user" )
    Store[] totalStoreData();

    /*get all data from database*/
    @Query("SELECT COUNT(id) FROM cartnumber" )
    int getCartNumber();

    /*get user data from database*/
    @Query("INSERT INTO cartnumber ( cart_number) VALUES (:number)" )
    long setCartNumber(int number);
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
