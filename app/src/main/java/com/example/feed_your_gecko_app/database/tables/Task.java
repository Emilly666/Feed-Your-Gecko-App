package com.example.feed_your_gecko_app.database.tables;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName = "tasks",
        foreignKeys = @ForeignKey(entity = UserReptile.class, parentColumns = "userReptile_id", childColumns = "myUserReptile_id", onDelete = ForeignKey.CASCADE))

public class Task {

    @PrimaryKey(autoGenerate = true)
    public long task_id;

    public long myUserReptile_id;
    public short taskType;

    public String date;

    public Task(long task_id, long myUserReptile_id, short taskType, String date ){
        this.task_id = task_id;
        this.myUserReptile_id = myUserReptile_id;
        this.taskType = taskType;
        this.date = date;
    }
}
