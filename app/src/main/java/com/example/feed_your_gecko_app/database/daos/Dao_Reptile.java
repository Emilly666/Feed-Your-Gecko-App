package com.example.feed_your_gecko_app.database.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.feed_your_gecko_app.database.tables.Reptile;

import java.util.List;

@Dao
public interface Dao_Reptile {

    @Insert
    void insertReptile(Reptile reptile);
    @Delete
    void deleteReptile(Reptile reptile);

    @Query("SELECT * FROM reptiles")
    List<Reptile> getAllReptiles();

    @Query("SELECT ReptileName FROM reptiles")
    List<String> getAllReptilesNames();

    @Query("SELECT reptile_id FROM reptiles WHERE reptileName LIKE :name")
    int checkIfReptileExistByName(String name);
}
