package com.example.kandarp.craps300283635;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> diceImg = new ArrayList<Integer>(Arrays.asList(R.drawable.die1,R.drawable.die2,
            R.drawable.die3, R.drawable.die4, R.drawable.die5, R.drawable.die6));

    TextView points, rollView, results;
    Button play, roll;
    ImageView dice00, dice01, dice10, dice11;
    int rand1 = 0;
    int rand2 = 0;
    int sum1 = 0;
    int sum2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        points = (TextView)findViewById(R.id.pointView);
        rollView = (TextView)findViewById(R.id.rollView);
        results = (TextView)findViewById(R.id.resultView);
        dice00 = (ImageView)findViewById(R.id.dice00);
        dice01 = (ImageView)findViewById(R.id.dice01);
        dice10 = (ImageView)findViewById(R.id.dice10);
        dice11 = (ImageView)findViewById(R.id.dice11);
        play = (Button) findViewById(R.id.playBtn);
        roll = (Button) findViewById(R.id.rollBtn);

        roll.setEnabled(false);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dice10.setImageResource(R.drawable.blank2);
                dice11.setImageResource(R.drawable.blank2);
                rollView.setText("");
                rand1 = new Random().nextInt(6);
                rand2 = new Random().nextInt(6);
                dice00.setImageResource(diceImg.get(rand1));
                dice01.setImageResource(diceImg.get(rand2));
                int sum1 = (rand1+1) + (rand2+1);
                points.setText(Integer.toString(sum1));

                //calculate the result
                switch (sum1){
                    case 7:
                    case 11:
                        results.setText("You Win!!! \n Click PLAY to Play again.");
                        break;
                    case 2:
                    case 3:
                    case 12:
                        results.setText("You loose!!! CRAPS!! \n Click PLAY to play again.");
                        break;
                    case 4:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                        results
                                .setText("You Rolled: "+sum1+"\nRoll Again!!");
                        roll.setEnabled(true);
                        play.setEnabled(false);
                        break;
                }

            }
        });

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rand1 = new Random().nextInt(6);
                rand2 = new Random().nextInt(6);
                dice10.setImageResource(diceImg.get(rand1));
                dice11.setImageResource(diceImg.get(rand2));
                int sum2 = (rand1+1) + (rand2+1);
                rollView.setText(Integer.toString(sum2));
                sum1 = Integer.parseInt(points.getText().toString());

                if (sum2 == sum1){
                    results.setText("You Win!!! \nClick PLAY to Play again.");
                    roll.setEnabled(false);
                    play.setEnabled(true);
                }else if (sum2 == 7){
                    results.setText("You loose!!! CRAPS!! \nClick PLAY to play again.");
                    roll.setEnabled(false);
                    play.setEnabled(true);
                }else {
                    results.setText("You Rolled: "+sum2+"\nKeep Rolling!!");
                }
            }
        });



    }
}
