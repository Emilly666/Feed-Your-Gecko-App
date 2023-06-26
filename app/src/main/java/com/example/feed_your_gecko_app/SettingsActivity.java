package com.example.feed_your_gecko_app;

import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import com.example.feedyourgeckoapp.R;

import java.time.LocalTime;

public class SettingsActivity extends AppCompatActivity {

    Toolbar toolbar;
    TimePicker notificationsTimePicker;
    SwitchCompat switchFeed, switchVitamins;
    Context context;
    SharedPreferencesManager sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        context = getApplicationContext();
        sp = SharedPreferencesManager.getInstance(context);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.settings);
        toolbar.setNavigationIcon(R.drawable.fyglogo);
        setSupportActionBar(toolbar);

        notificationsTimePicker = findViewById(R.id.notificationsTimePicker);

        if(DateFormat.is24HourFormat(this)){
            notificationsTimePicker.setIs24HourView(true);
        }

        LocalTime notificationsTime = sp.getNotificationsTime();
        notificationsTimePicker.setHour(notificationsTime.getHour());
        notificationsTimePicker.setMinute(notificationsTime.getMinute());

        switchFeed = findViewById(R.id.switchFeed);
        switchVitamins = findViewById(R.id.switchVitamins);

        switchFeed.setChecked(sp.getNotificationsFeed());
        switchVitamins.setChecked(sp.getNotificationsVitamins());
    }
    @Override
    public void onBackPressed() {
        sp.setNotificationsTime(LocalTime.of(notificationsTimePicker.getHour(), notificationsTimePicker.getMinute()));
        sp.setNotificationsFeed(switchFeed.isChecked());
        sp.setNotificationsVitamins(switchVitamins.isChecked());
        finish();
    }
}