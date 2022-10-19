package com.example.Databse;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.google.android.gms.tasks.Task;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Store.class, CartNumber.class, ProductPrice.class, RecentProduct.class},
        version = 9, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    public abstract StoreDao patientDAO(); // calling dao function

    private static volatile MyDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    // database creation
    public static MyDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyDatabase.class, "store_db")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
 }
}
