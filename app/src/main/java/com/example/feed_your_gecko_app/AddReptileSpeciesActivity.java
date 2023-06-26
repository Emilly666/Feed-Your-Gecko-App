package com.example.feed_your_gecko_app;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.feed_your_gecko_app.database.AppDatabase;
import com.example.feed_your_gecko_app.database.tables.Reptile;
import com.example.feedyourgeckoapp.R;
import com.google.android.material.slider.RangeSlider;
import com.google.android.material.slider.Slider;

import java.util.List;

public class AddReptileSpeciesActivity extends AppCompatActivity {

    EditText speciesName, vitaminsType, feedingType, subsoil;
    Toolbar toolbar;
    NumberPicker feedingPicker, vitaminsPicker;
    RangeSlider temperatureSliderDay, temperatureSliderNight;
    Slider humiditySlider;
    TextView temperatureFromDay, temperatureToDay, temperatureFromNight, temperatureToNight, humidityLabel;
    Button addPlantSpeciesButton;

    Context context;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reptile);

        context = getApplicationContext();
        db = AppDatabase.getDatabase(context);

        speciesName = findViewById(R.id.speciesName);
        toolbar = findViewById(R.id.toolbar);
        temperatureSliderDay = findViewById(R.id.temperatureSliderDay);
        temperatureSliderNight = findViewById(R.id.temperatureSliderNight);
        temperatureFromDay = findViewById(R.id.temperatureFromDay);
        temperatureToDay = findViewById(R.id.temperatureToDay);
        temperatureFromNight = findViewById(R.id.temperatureFromNight);
        temperatureToNight = findViewById(R.id.temperatureToNight);
        feedingPicker = findViewById(R.id.waterFrequencyPicker);
        vitaminsPicker = findViewById(R.id.feedingFrequencyPicker);
        addPlantSpeciesButton = findViewById(R.id.addReptileSpeciesButton);
        humidityLabel = findViewById(R.id.humidityLabel);
        humiditySlider = findViewById(R.id.humiditySlider);
        feedingType = findViewById(R.id.feedingType);
        vitaminsType = findViewById(R.id.vitaminsType);
        subsoil = findViewById(R.id.subsoil);

        //setup toolbar
        toolbar.setTitle(R.string.add_new_species);
        toolbar.setNavigationIcon(R.drawable.fyglogo);
        toolbar.setTitle(R.string.add_new_species);
        setSupportActionBar(toolbar);

        //get entered species name from popup
        Bundle b = getIntent().getExtras();
        if (b != null) {
            speciesName.setText(b.getString("speciesName"));
        }

        //setup temperature slider day
        temperatureSliderDay.setValues(0f,50f);
        temperatureSliderDay.setTrackActiveTintList(ColorStateList.valueOf(getResources().getColor(R.color.orange_darker)));
        temperatureSliderDay.setThumbTintList(ColorStateList.valueOf(getResources().getColor(R.color.orange_darker)));
        temperatureSliderDay.setHaloTintList(ColorStateList.valueOf(getResources().getColor(R.color.orange_lighter)));
        temperatureSliderDay.setTickVisible(false);
        temperatureFromDay.setText( String.format(getResources().getString(R.string.temp), 0) );
        temperatureToDay.setText( String.format(getResources().getString(R.string.temp), 50) );
        temperatureSliderDay.addOnChangeListener((slider, value, fromUser) -> {
            temperatureFromDay.setText( String.format(getResources().getString(R.string.temp), Math.round(slider.getValues().get(0))) );
            temperatureToDay.setText( String.format(getResources().getString(R.string.temp), Math.round(slider.getValues().get(1))) );
        });

        //setup temperature slider night
        temperatureSliderNight.setValues(0f,50f);
        temperatureSliderNight.setTrackActiveTintList(ColorStateList.valueOf(getResources().getColor(R.color.orange_darker)));
        temperatureSliderNight.setThumbTintList(ColorStateList.valueOf(getResources().getColor(R.color.orange_darker)));
        temperatureSliderNight.setHaloTintList(ColorStateList.valueOf(getResources().getColor(R.color.orange_lighter)));
        temperatureSliderNight.setTickVisible(false);
        temperatureFromNight.setText( String.format(getResources().getString(R.string.temp), 0) );
        temperatureToNight.setText( String.format(getResources().getString(R.string.temp), 50) );
        temperatureSliderNight.addOnChangeListener((slider, value, fromUser) -> {
            temperatureFromNight.setText( String.format(getResources().getString(R.string.temp), Math.round(slider.getValues().get(0))) );
            temperatureToNight.setText( String.format(getResources().getString(R.string.temp), Math.round(slider.getValues().get(1))) );
        });

        //setup humidity slider
        humidityLabel.setText( String.format(getResources().getString(R.string.preferable_humidity), 50) );
        humiditySlider.addOnChangeListener((slider, value, fromUser) -> {
            humidityLabel.setText( String.format(getResources().getString(R.string.preferable_humidity), Math.round(slider.getValue())) );
        });

        //initialize wateringPicker
        feedingPicker.setMinValue(1);
        feedingPicker.setMaxValue(100);

        //initialize fertilizingPicker
        vitaminsPicker.setMinValue(1);
        vitaminsPicker.setMaxValue(100);
    }

    public void addPlantSpeciesButtonClicked(View view) {
        String str = speciesName.getText().toString();
        String str2 = feedingType.getText().toString();
        String str3 = vitaminsType.getText().toString();
        String str4 = subsoil.getText().toString();
        if ( str.isEmpty() ){
            Toast.makeText(context, "Enter species name!", Toast.LENGTH_SHORT).show();
            return;
        }
        if ( (str.startsWith(" ") || str.endsWith(" ")) ){
            Toast.makeText(context, "Incorrect species name!", Toast.LENGTH_SHORT).show();
            return;
        }
        if ( str2.isEmpty() ){
            Toast.makeText(context, "Enter food type!", Toast.LENGTH_SHORT).show();
            return;
        }
        if ( (str2.startsWith(" ") || str2.endsWith(" ")) ){
            Toast.makeText(context, "Incorrect food type!", Toast.LENGTH_SHORT).show();
            return;
        }
        if ( str3.isEmpty() ){
            Toast.makeText(context, "Enter vitamins type!", Toast.LENGTH_SHORT).show();
            return;
        }
        if ( (str3.startsWith(" ") || str3.endsWith(" ")) ){
            Toast.makeText(context, "Incorrect vitamins type!", Toast.LENGTH_SHORT).show();
            return;
        }
        if ( str4.isEmpty() ){
            Toast.makeText(context, "Enter subsoil description!", Toast.LENGTH_SHORT).show();
            return;
        }
        if ( (str4.startsWith(" ") || str4.endsWith(" ")) ){
            Toast.makeText(context, "Incorrect subsoil description!", Toast.LENGTH_SHORT).show();
            return;
        }
        List<String> plantList =  db.dao_reptile().getAllReptilesNames();
        plantList.replaceAll(String::toLowerCase);
        if( plantList.contains(str.toLowerCase()) ){
            Toast.makeText(context, "That reptile species already exist in database!", Toast.LENGTH_SHORT).show();
            return;
        }

        Reptile newReptile = new Reptile(
                    speciesName.getText().toString(),
                    Math.round(temperatureSliderDay.getValues().get(0)),
                    Math.round(temperatureSliderDay.getValues().get(1)),
                    Math.round(temperatureSliderNight.getValues().get(0)),
                    Math.round(temperatureSliderNight.getValues().get(1)),
                    Math.round(humiditySlider.getValue()),
                    feedingPicker.getValue(),
                    vitaminsPicker.getValue(),
                    feedingType.getText().toString(),
                    vitaminsType.getText().toString(),
                    subsoil.getText().toString()
                );
        db.dao_reptile().insertReptile(newReptile);
        this.finish();
    }
}