package com.infinium.scorekeeper_rasheen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class SettingsActivity extends AppCompatActivity {


    CheckBox cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Settings");

        cb = findViewById(R.id.checkBox);

        cb.setChecked(sharedPreferences.getBoolean("save",false));

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(!b){
                    SharedPreferences.Editor shEditor = sharedPreferences.edit();
                    shEditor.clear();
                    shEditor.commit();
                }

                SharedPreferences.Editor shEditor = sharedPreferences.edit();

                shEditor.putBoolean("save",b);

                shEditor.commit();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}