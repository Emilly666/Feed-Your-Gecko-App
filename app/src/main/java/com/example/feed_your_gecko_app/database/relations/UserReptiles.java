package com.example.feed_your_gecko_app.database.relations;

import androidx.room.Embedded;

import com.example.feed_your_gecko_app.database.tables.Reptile;
import com.example.feed_your_gecko_app.database.tables.UserReptile;

public class UserReptiles {

    @Embedded
    public UserReptile userReptile;
    @Embedded
    public Reptile reptile;
}
