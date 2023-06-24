package com.example.feed_your_gecko_app.database.tables;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "userReptiles",
        foreignKeys = @ForeignKey(entity = Reptile.class, parentColumns = "reptile_id", childColumns = "myReptile_id", onDelete = ForeignKey.CASCADE))
public class UserReptile {

    @PrimaryKey(autoGenerate = true)
    public long userReptile_id;

    public long myReptile_id;
    public String reptileNickname;

    public UserReptile(long myReptile_id, String reptileNickname){
        this.myReptile_id = myReptile_id;
        this.reptileNickname = reptileNickname;
    }
}
