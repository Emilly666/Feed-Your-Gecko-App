package com.example.feed_your_gecko_app.database.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.feed_your_gecko_app.database.tables.Reptile;
import com.example.feed_your_gecko_app.database.tables.Task;

import java.util.List;

@Dao
public interface Dao_Task {

    @Query("SELECT * FROM tasks")
    List<Task> getAllTasks();
}
