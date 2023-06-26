package com.example.feed_your_gecko_app;

import android.content.Context;
import android.content.SharedPreferences;

import java.time.LocalTime;
import java.time.temporal.ChronoField;

public class SharedPreferencesManager {
    private static final String APP_PREFS = "AppPrefsFile";
    private static final Long notificationsTime = LocalTime.of(10,0).getLong(ChronoField.SECOND_OF_DAY);
    private static final boolean notificationsFeed = true;
    private static final boolean notificationsVitamins = true;

    private SharedPreferences sp;
    private static SharedPreferencesManager instance;

    private SharedPreferencesManager(Context context) {
        sp = context.getApplicationContext().getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
    }
    public static synchronized SharedPreferencesManager getInstance(Context context){
        if(instance == null)
            instance = new SharedPreferencesManager(context);

        return instance;
    }

    public void setNotificationsTime(LocalTime time) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong("notificationsTime", time.getLong(ChronoField.SECOND_OF_DAY));
        editor.apply();
    }
    public LocalTime getNotificationsTime() {
        return LocalTime.ofSecondOfDay(sp.getLong("notificationsTime", LocalTime.of(10,0).getLong(ChronoField.SECOND_OF_DAY)));
    }

    public void setNotificationsFeed(boolean notificationsFeed) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("notificationsFeed", notificationsFeed);
        editor.apply();
    }
    public boolean getNotificationsFeed() {
        return sp.getBoolean("notificationsFeed", true);
    }

    public void setNotificationsVitamins(boolean notificationsVitamins) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("notificationsVitamins", notificationsVitamins);
        editor.apply();
    }
    public boolean getNotificationsVitamins() {
        return sp.getBoolean("notificationsVitamins", true);
    }
}