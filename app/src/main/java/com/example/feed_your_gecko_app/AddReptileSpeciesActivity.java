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

    EditText speciesName;
    Toolbar toolbar;
    NumberPicker feedingPicker, vitaminsPicker;
    RangeSlider temperatureSlider;
    Slider lightSlider, humiditySlider;
    TextView temperatureFrom, temperatureTo, humidityLabel;
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
        temperatureSlider = findViewById(R.id.temperatureSlider);
        temperatureFrom = findViewById(R.id.temperatureFrom);
        temperatureTo = findViewById(R.id.temperatureTo);
        feedingPicker = findViewById(R.id.waterFrequencyPicker);
        vitaminsPicker = findViewById(R.id.feedingFrequencyPicker);
        addPlantSpeciesButton = findViewById(R.id.addReptileSpeciesButton);
        lightSlider = findViewById(R.id.lightSlider);
        humidityLabel = findViewById(R.id.humidityLabel);
        humiditySlider = findViewById(R.id.humiditySlider);

        //setup toolbar
        toolbar.setTitle(R.string.add_new_species);
        toolbar.setNavigationIcon(R.drawable.logofyg);
        toolbar.setTitle(R.string.add_new_species);
        setSupportActionBar(toolbar);

        //get entered species name from popup
        Bundle b = getIntent().getExtras();
        if (b != null) {
            speciesName.setText(b.getString("speciesName"));
        }

        //setup temperature slider
        temperatureSlider.setValues(0f,50f);
        temperatureSlider.setTrackActiveTintList(ColorStateList.valueOf(getResources().getColor(R.color.orange_darker)));
        temperatureSlider.setThumbTintList(ColorStateList.valueOf(getResources().getColor(R.color.orange_darker)));
        temperatureSlider.setHaloTintList(ColorStateList.valueOf(getResources().getColor(R.color.orange_lighter)));
        temperatureSlider.setTickVisible(false);
        temperatureFrom.setText( String.format(getResources().getString(R.string.temp), 0) );
        temperatureTo.setText( String.format(getResources().getString(R.string.temp), 50) );
        temperatureSlider.addOnChangeListener((slider, value, fromUser) -> {
            temperatureFrom.setText( String.format(getResources().getString(R.string.temp), Math.round(slider.getValues().get(0))) );
            temperatureTo.setText( String.format(getResources().getString(R.string.temp), Math.round(slider.getValues().get(1))) );
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
        if ( str.isEmpty() ){
            Toast.makeText(context, "Enter species name!", Toast.LENGTH_SHORT).show();
            return;
        }
        if ( (str.startsWith(" ") || str.endsWith(" ")) ){
            Toast.makeText(context, "Incorrect species name!", Toast.LENGTH_SHORT).show();
            return;
        }
        List<String> plantList =  db.dao_reptile().getAllReptilesNames();
        plantList.replaceAll(String::toLowerCase);
        if( plantList.contains(str.toLowerCase()) ){
            Toast.makeText(context, "That plant species already exist in database!", Toast.LENGTH_SHORT).show();
            return;
        }

        String light;
        switch( Math.round(lightSlider.getValue())  ) {
            case 0:
                light = "some light 1";
                break;
            case 1:
                light = "some light 2";
                break;
            case 3:
                light = "some light 4";
                break;
            case 4:
                light = "some light 5";
                break;
            default: //default position of seekBar
                light = "some light 3";
        }

        Reptile newReptile = new Reptile(
                    speciesName.getText().toString(),
                    Math.round(temperatureSlider.getValues().get(0)),
                    Math.round(temperatureSlider.getValues().get(1)),
                    light,
                    Math.round(humiditySlider.getValue()),
                    feedingPicker.getValue(),
                    vitaminsPicker.getValue()
                );
        db.dao_reptile().insertReptile(newReptile);
        this.finish();
    }
}