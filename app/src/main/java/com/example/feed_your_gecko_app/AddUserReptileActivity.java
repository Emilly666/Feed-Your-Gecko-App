package com.example.feed_your_gecko_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.feed_your_gecko_app.database.AppDatabase;
import com.example.feed_your_gecko_app.database.tables.UserReptile;
import com.example.feedyourgeckoapp.R;
import com.google.android.material.color.MaterialColors;

import java.util.List;

public class AddUserReptileActivity extends AppCompatActivity {

    Context context;
    Toolbar toolbar;
    AppDatabase db;
    AutoCompleteTextView autocomplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_reptile);

        context = getApplicationContext();

        //setup toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.add_new_species);
        toolbar.setNavigationIcon(R.drawable.logofyg);
        toolbar.setTitle(R.string.add_new_reptile);
        setSupportActionBar(toolbar);

        db = AppDatabase.getDatabase(context);

        autocomplete = findViewById(R.id.autoCompleteTextView);
        Button buttonSubmit = findViewById(R.id.buttonSubmit);
        EditText plantNicknameTextView = findViewById(R.id.plantNicknameTextView);
        ImageButton addUserPlantButton = findViewById(R.id.addPlantButton);

        setUpAutocomplete();
        autocomplete.setOnFocusChangeListener((view14, b) -> autocomplete.showDropDown());

        buttonSubmit.setOnClickListener(view12 -> {
            int newPantID = db.dao_reptile().checkIfReptileExistByName(autocomplete.getText().toString());

            if(newPantID==0){//plant does not exist in database
                Toast toast = Toast.makeText(context, "Reptile species not found in database", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
            else {
                String name;
                if(plantNicknameTextView.getText().toString().equals("")){
                    name = autocomplete.getText().toString();
                }else{
                    name = plantNicknameTextView.getText().toString();
                }

                UserReptile userReptile = new UserReptile(newPantID, name);
                db.dao_userReptile().insertUserReptile(userReptile);
                finish();
            }
        });
        addUserPlantButton.setOnClickListener(view -> {
            Intent i = new Intent(context, AddReptileSpeciesActivity.class);
            Bundle b = new Bundle();
            b.putString( "speciesName", autocomplete.getText().toString() );
            i.putExtras(b);

            startActivity(i);
        });

    }
    public void setUpAutocomplete(){
        List<String> listDb = db.dao_reptile().getAllReptilesNames();
        String[] arr = listDb.toArray(new String[0]);
        autocomplete.setThreshold(0);
        AutocompleteArrayAdapter adapter = new AutocompleteArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, arr);
        autocomplete.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpAutocomplete();
    }
}