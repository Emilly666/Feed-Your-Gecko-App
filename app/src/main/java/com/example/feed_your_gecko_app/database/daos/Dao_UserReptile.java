package com.example.feed_your_gecko_app.database.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.feed_your_gecko_app.database.relations.UserReptiles;
import com.example.feed_your_gecko_app.database.tables.UserReptile;

import java.util.List;

@Dao
public interface Dao_UserReptile {
    @Insert
    void insertUserReptile(UserReptile userReptile);
    @Delete
    void deleteUserReptile(UserReptile userReptile);

    @Query("DELETE FROM userReptiles WHERE userReptile_id = :id")
    void deleteUserReptile(int id);

    @Query( "SELECT ur.*, r.* " +
            "FROM userReptiles ur " +
            "INNER JOIN reptiles r ON ur.myReptile_id = r.reptile_id")
    List<UserReptiles> getAllUserReptiles();
    @Query("SELECT * FROM userReptiles WHERE userReptile_id = :id")
    UserReptile getUserReptile(int id);

    @Query("SELECT ur.*, r.* " +
            "FROM userReptiles ur " +
            "INNER JOIN reptiles r ON ur.myReptile_id = r.reptile_id " +
            "ORDER BY ur.userReptile_id DESC " +
            "LIMIT 1")
    UserReptile getLatestUserReptile();
}

