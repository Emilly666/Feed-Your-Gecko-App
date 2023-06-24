package com.example.feed_your_gecko_app.database.tables;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reptiles")
public class Reptile {

    @PrimaryKey(autoGenerate = true)
    public long reptile_id;

    public String reptileName;
    public int temperatureFrom;
    public int temperatureTo;
    public String light;
    public int humidity;
    public int feedingFrequency;
    public int vitaminsFrequency;

    public Reptile( String reptileName, int temperatureFrom, int temperatureTo, String light, int humidity, int feedingFrequency, int vitaminsFrequency){
        this.reptileName = reptileName;
        this.temperatureFrom = temperatureFrom;
        this.temperatureTo = temperatureTo;
        this.light = light;
        this.humidity = humidity;
        this.feedingFrequency = feedingFrequency;
        this.vitaminsFrequency = vitaminsFrequency;
    }
}
