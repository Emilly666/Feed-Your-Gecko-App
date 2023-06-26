package com.example.feed_your_gecko_app.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.feed_your_gecko_app.database.daos.Dao_Reptile;
import com.example.feed_your_gecko_app.database.daos.Dao_UserReptile;
import com.example.feed_your_gecko_app.database.tables.Reptile;
import com.example.feed_your_gecko_app.database.tables.Task;
import com.example.feed_your_gecko_app.database.tables.UserReptile;


@Database(entities = { Reptile.class, Task.class, UserReptile.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public abstract Dao_Reptile dao_reptile();
    public abstract Dao_UserReptile dao_userReptile();

    public static AppDatabase getDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "REPTILES_DATABASE.db")
                    .allowMainThreadQueries()
                    //.createFromAsset("REPTILES_DATABASE.db")
                    .build();
        }
        return INSTANCE;
    }
}
