package com.infinium.scorekeeper_rasheen;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Layout;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    private int scoreBy = 1;
    private int lakersCurrentScore = 0;
    private int thundersCurrentScore = 0;

    private int lakersShooting = 0;
    private int thundersShooting = 0;
    private int lakersFreeThrows = 0;
    private int thundersFreeThrows = 0;
    private int lakersTwoPointer = 0;
    private int thundersTwoPointer = 0;
    private int lakersThreePointer = 0;
    int thundersThreePointer = 0;
    int lakersFouls = 0;
    int thundersFouls = 0;
    private TextView lakersScoreAmount;
    private TextView thundersScoreAmount;
    private TextView thundersScore;
    private TextView lakersScore;

    private TextView lakersShootingLabel;
    private TextView lakersTwoPointerAmount;
    private TextView lakersThreePointerAmount;
    private TextView lakersFreeThrowAmount;
    private TextView lakersFoulAmount;

    private TextView thundersShootingLabel;
    private TextView thundersTwoPointerAmount;
    private TextView thundersThreePointerAmount ;
    private TextView thundersFreeThrowAmount ;
    private TextView thunderFoulAmount;
    RadioGroup pointGroup;

    RadioButton button1;
    RadioButton button2;
    RadioButton button3;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CardView card = findViewById(R.id.card);
        pointGroup = findViewById(R.id.point_group);

        Button lakersAddButton = findViewById(R.id.lakers_add_btn);
        Button lakersMinusButton = findViewById(R.id.lakers_minus_btn);
        Button thundersAddButton = findViewById(R.id.thunder_add_btn);
        Button thundersMinusButton = findViewById(R.id.thunder_minus_btn);

        lakersScoreAmount = findViewById(R.id.lakers_score_amount_btn);
        thundersScoreAmount = findViewById(R.id.thunder_score_amount_btn);
        thundersScore =  findViewById(R.id.thunders_score);
        lakersScore =  findViewById(R.id.laker_score);

        lakersShootingLabel =  findViewById(R.id.shooting_lakers);
        lakersTwoPointerAmount =  findViewById(R.id.two_pointers_lakers);
        lakersThreePointerAmount =  findViewById(R.id.three_pointers_lakers);
        lakersFreeThrowAmount =  findViewById(R.id.free_throws_lakers);
        lakersFoulAmount =  findViewById(R.id.fouls_lakers);

        thundersShootingLabel =  findViewById(R.id.shooting_thunders);
        thundersTwoPointerAmount =  findViewById(R.id.two_pointers_thunder);
        thundersThreePointerAmount =  findViewById(R.id.three_pointers_thunder);
        thundersFreeThrowAmount =  findViewById(R.id.free_throws_thunder);
        thunderFoulAmount =  findViewById(R.id.fouls_thunder);

        button1 = findViewById(R.id.one_points_rd);
        button2 = findViewById(R.id.two_points_rd);
        button3 = findViewById(R.id.three_points_rd);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            makeCustomOutline(card);
        }

        pointGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                setScoreBy(id,lakersScoreAmount,thundersScoreAmount);
            }
        });

        lakersAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lakersCurrentScore += scoreBy;
                lakersScore.setText(String.valueOf(lakersCurrentScore));
                setScoringMethod("Lakers");
                lakersShootingLabel.setText(String.valueOf(lakersShooting));
                lakersTwoPointerAmount.setText(String.valueOf(lakersTwoPointer));
                lakersThreePointerAmount.setText(String.valueOf(lakersThreePointer));
                lakersFreeThrowAmount.setText(String.valueOf(lakersFreeThrows));
            }
        });

        thundersAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thundersCurrentScore += scoreBy;
                thundersScore.setText(String.valueOf(thundersCurrentScore));
                setScoringMethod("Thunders");
                thundersShootingLabel.setText(String.valueOf(thundersShooting));
                thundersTwoPointerAmount.setText(String.valueOf(thundersTwoPointer));
                thundersThreePointerAmount.setText(String.valueOf(thundersThreePointer));
                thundersFreeThrowAmount.setText(String.valueOf(thundersFreeThrows));
            }
        });


        lakersMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lakersCurrentScore - scoreBy >= 0){
                    lakersCurrentScore -= scoreBy;
                    lakersFouls++;
                    lakersFoulAmount.setText(String.valueOf(lakersFouls));
                    lakersScore.setText(String.valueOf(lakersCurrentScore));
                    removeScoringMethod("Lakers");
                    lakersShootingLabel.setText(String.valueOf(lakersShooting));
                    lakersTwoPointerAmount.setText(String.valueOf(lakersTwoPointer));
                    lakersThreePointerAmount.setText(String.valueOf(lakersThreePointer));
                    lakersFreeThrowAmount.setText(String.valueOf(lakersFreeThrows));
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Unable to reduce score below 0", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });


        thundersMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(thundersCurrentScore - scoreBy >= 0){
                    thundersCurrentScore -= scoreBy;
                    thundersFouls++;
                    thunderFoulAmount.setText(String.valueOf(thundersFouls));
                    thundersScore.setText(String.valueOf(thundersCurrentScore));
                    removeScoringMethod("Thunders");
                    thundersShootingLabel.setText(String.valueOf(thundersShooting));
                    thundersTwoPointerAmount.setText(String.valueOf(thundersTwoPointer));
                    thundersThreePointerAmount.setText(String.valueOf(thundersThreePointer));
                    thundersFreeThrowAmount.setText(String.valueOf(thundersFreeThrows));
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Unable to reduce score below 0", Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        });


        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar);

        ImageView aboutImage = getSupportActionBar().getCustomView().findViewById(R.id.info);
        aboutImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,About.class));
            }
        });


        ImageView settingImage = getSupportActionBar().getCustomView().findViewById(R.id.settings);
        settingImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SettingsActivity.class));
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void makeCustomOutline(CardView card){

        card.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, -50, view.getWidth(), (view.getHeight()), (float) 50);
            }
        });
        card.setClipToOutline(true);
    }

    @Override
    protected void onPause() {
        super.onPause();


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor shEditor = sharedPreferences.edit();

        if(sharedPreferences.getBoolean("save",false)){
            shEditor.putInt("lakersShooting",lakersShooting);
            shEditor.putInt("lakersFreethrows", lakersFreeThrows);
            shEditor.putInt("lakersTwo", lakersTwoPointer);
            shEditor.putInt("lakersThree", lakersThreePointer);
            shEditor.putInt("lakersFoul", lakersFouls);
            shEditor.putInt("lakerPoints", lakersCurrentScore);



            shEditor.putInt("thunderShooting",thundersShooting);
            shEditor.putInt("thunderFreethrows", thundersFreeThrows);
            shEditor.putInt("thunderTwo", thundersTwoPointer);
            shEditor.putInt("thunderThree", thundersThreePointer);
            shEditor.putInt("thunderFoul", thundersFouls);
            shEditor.putInt("thunderPoints", thundersCurrentScore);

            shEditor.putInt("checkedRadio",pointGroup.getCheckedRadioButtonId());

            shEditor.commit();
        }else{
            shEditor.clear();
            shEditor.commit();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);


        if(sharedPreferences.getBoolean("save",false)) {
            lakersShooting = sharedPreferences.getInt("lakersShooting", 0);
            lakersFreeThrows = sharedPreferences.getInt("lakersFreethrows", 0);
            lakersTwoPointer = sharedPreferences.getInt("lakersTwo", 0);
            lakersThreePointer = sharedPreferences.getInt("lakersThree", 0);
            lakersFouls = sharedPreferences.getInt("lakersFoul", 0);
            lakersCurrentScore =  sharedPreferences.getInt("lakerPoints", 0);

            thundersShooting = sharedPreferences.getInt("thunderShooting", 0);
            thundersFreeThrows = sharedPreferences.getInt("thunderFreethrows", 0);
            thundersTwoPointer = sharedPreferences.getInt("thunderTwo", 0);
            thundersThreePointer = sharedPreferences.getInt("thunderThree", 0);
            thundersFouls = sharedPreferences.getInt("thunderFoul", 0);
            thundersCurrentScore =  sharedPreferences.getInt("thunderPoints", 0);

            lakersShootingLabel.setText(String.valueOf(lakersShooting));
            lakersTwoPointerAmount.setText(String.valueOf(lakersTwoPointer));
            lakersThreePointerAmount.setText(String.valueOf(lakersThreePointer));
            lakersFreeThrowAmount.setText(String.valueOf(lakersFreeThrows));
            lakersFoulAmount.setText(String.valueOf(lakersFouls));
            lakersScore.setText(String.valueOf(lakersCurrentScore));

            thundersShootingLabel.setText(String.valueOf(thundersShooting));
            thundersTwoPointerAmount.setText(String.valueOf(thundersTwoPointer));
            thundersThreePointerAmount.setText(String.valueOf(thundersThreePointer));
            thundersFreeThrowAmount.setText(String.valueOf(thundersFreeThrows));
            thunderFoulAmount.setText(String.valueOf(thundersFouls));
            thundersScore.setText(String.valueOf(thundersCurrentScore));


            int id =  sharedPreferences.getInt("checkedRadio",pointGroup.getCheckedRadioButtonId());


            switch (id) {
                case R.id.one_points_rd:
                    scoreBy = 1;
                    button1.setChecked(true);
                    break;
                case R.id.two_points_rd:
                    scoreBy = 2;
                    button2.setChecked(true);
                    break;
                case R.id.three_points_rd:
                    scoreBy = 3;
                    button3.setChecked(true);
                    break;
            }

        }

    }

    public void setScoreBy(int id, TextView lakers, TextView thunders){
        switch (id) {
            case R.id.one_points_rd:
                scoreBy = 1;
                lakers.setText(String.valueOf(scoreBy));
                thunders.setText(String.valueOf(scoreBy));
                break;
            case R.id.two_points_rd:
                scoreBy = 2;
                lakers.setText(String.valueOf(scoreBy));
                thunders.setText(String.valueOf(scoreBy));
                break;
            case R.id.three_points_rd:
                scoreBy = 3;
                lakers.setText(String.valueOf(scoreBy));
                thunders.setText(String.valueOf(scoreBy));
                break;
        }
    }

    public void setScoringMethod(String team){
        switch (scoreBy){
            case 1:
                if(team.equals("Lakers")){
                    lakersFreeThrows++;
                    lakersShooting++;
                }else{
                    thundersFreeThrows++;
                    thundersShooting++;
                }
                break;
            case 2:
                if(team.equals("Lakers")){
                    lakersTwoPointer++;
                    lakersShooting++;
                }else{
                    thundersTwoPointer++;
                    thundersShooting++;
                }
                break;
            case 3:
                if(team.equals("Lakers")){
                    lakersThreePointer++;
                    lakersShooting++;
                }else{
                    thundersThreePointer++;
                    thundersShooting++;
                }
                break;
        }
    }


    public void removeScoringMethod(String team){
        switch (scoreBy){
            case 1:
                if(team.equals("Lakers")){
                    lakersFreeThrows--;
                    lakersShooting--;
                }else{
                    thundersFreeThrows--;
                    thundersShooting--;
                }
                break;
            case 2:
                if(team.equals("Lakers")){
                    lakersTwoPointer--;
                    lakersShooting--;
                }else{
                    thundersTwoPointer--;
                    thundersShooting--;
                }
                break;
            case 3:
                if(team.equals("Lakers")){
                    lakersThreePointer--;
                    lakersShooting--;
                }else{
                    thundersThreePointer--;
                    thundersShooting--;
                }
                break;
        }
    }
}