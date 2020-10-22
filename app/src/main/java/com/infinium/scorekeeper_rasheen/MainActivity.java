package com.infinium.scorekeeper_rasheen;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CardView card = findViewById(R.id.card);
        RadioGroup pointGroup = findViewById(R.id.point_group);

        Button lakersAddButton = findViewById(R.id.lakers_add_btn);
        Button lakersMinusButton = findViewById(R.id.lakers_minus_btn);
        Button thundersAddButton = findViewById(R.id.thunder_add_btn);
        Button thundersMinusButton = findViewById(R.id.thunder_minus_btn);

        final TextView lakersScoreAmount = findViewById(R.id.lakers_score_amount_btn);
        final TextView thundersScoreAmount = findViewById(R.id.thunder_score_amount_btn);
        final TextView thundersScore =  findViewById(R.id.thunders_score);
        final TextView lakersScore =  findViewById(R.id.laker_score);

        final TextView lakersShootingLabel =  findViewById(R.id.shooting_lakers);
        final TextView lakersTwoPointerAmount =  findViewById(R.id.two_pointers_lakers);
        final TextView lakersThreePointerAmount =  findViewById(R.id.three_pointers_lakers);
        final TextView lakersFreeThrowAmount =  findViewById(R.id.free_throws_lakers);
        final TextView lakersFoulAmount =  findViewById(R.id.fouls_lakers);

        final TextView thundersShootingLabel =  findViewById(R.id.shooting_thunders);
        final TextView thundersTwoPointerAmount =  findViewById(R.id.two_pointers_thunder);
        final TextView thundersThreePointerAmount =  findViewById(R.id.three_pointers_thunder);
        final TextView thundersFreeThrowAmount =  findViewById(R.id.free_throws_thunder);
        final TextView thunderFoulAmount =  findViewById(R.id.fouls_thunder);


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