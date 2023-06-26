package com.example.feed_your_gecko_app.database.tables;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reptiles")
public class Reptile {

    @PrimaryKey(autoGenerate = true)
    public long reptile_id;

    public String reptileName;
    public int temperatureFromDay;
    public int temperatureToDay;
    public int temperatureFromNight;
    public int temperatureToNight;
    public int humidity;
    public int feedingFrequency;
    public String feedingType;
    public int vitaminsFrequency;
    public String vitaminsType;
    public String subsoil;

    public Reptile( String reptileName, int temperatureFromDay, int temperatureToDay, int temperatureFromNight, int temperatureToNight, int humidity, int feedingFrequency, int vitaminsFrequency, String feedingType, String vitaminsType, String subsoil){
        this.reptileName = reptileName;
        this.temperatureFromDay = temperatureFromDay;
        this.temperatureToDay = temperatureToDay;
        this.humidity = humidity;
        this.feedingFrequency = feedingFrequency;
        this.vitaminsFrequency = vitaminsFrequency;
        this.feedingType = feedingType;
        this.vitaminsType = vitaminsType;
        this.subsoil = subsoil;
        this.temperatureFromNight = temperatureFromNight;
        this.temperatureToNight = temperatureToNight;
    }
}
