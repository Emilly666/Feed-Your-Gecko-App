package com.example.feed_your_gecko_app.database.tables;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public class Task {

    @PrimaryKey(autoGenerate = true)
    public long task_id;
}
