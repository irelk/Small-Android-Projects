package com.example.kandarp.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// REFERENCE Tutorial : https://www.youtube.com/watch?v=apDL78MFR3o 
// ID : 300283635

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];
    private boolean play1 = true;
    private int roundCount;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result_txt);

        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                String btnID = "btn_" + i + j;
                int resId = getResources().getIdentifier(btnID,"id",getPackageName());
                buttons[i][j] = findViewById(resId);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button newGame = findViewById(R.id.btn_newgame);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reset();

            }
        });

    }

    @Override
    public void onClick(View v) {
        // If button contains empty string
        if(!((Button) v).getText().toString().equals("")){
            return;
        }
        if(play1){
            ((Button)v).setText("X");
        }else {
            ((Button)v).setText("O");
        }

        roundCount++;

        if(winCheck()){
            if(play1){
                play1wins();
            }
            else {
                play2wins();
            }
        }else if(roundCount == 9)
        {
            drawGame();
        }else {
            play1 = !play1;
        }
    }

    private  boolean winCheck(){
        String[][] fields = new String[3][3];

        for (int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {

                fields[i][j] = buttons[i][j].getText().toString();

            }
        }

        // Compare next fields to each other
        // First field can't be empty
        // Go through columns
        for(int i=0;i<3;i++){
            if(fields[i][0].equals(fields[i][1])
                    && fields[i][0].equals(fields[i][2])
                    && !fields[i][0].equals("")){

                return true;
            }
        }

        // Go through rows
        for(int i=0;i<3;i++){
            if(fields[0][i].equals(fields[1][i])
                    && fields[0][i].equals(fields[2][i])
                    && !fields[0][i].equals("")){
                return true;
            }
        }

        // Compare Diagonals - 2 Dimensional Array

        if(fields[0][0].equals(fields[1][1])
                && fields[0][0].equals(fields[2][2])
                && !fields[0][0].equals("")){
            return true;
        }

        if(fields[0][2].equals(fields[1][1])
                && fields[0][2].equals(fields[2][0])
                && !fields[0][2].equals("")){
            return true;
        }

            return false;
    }

    private void play1wins(){

        result.setText("Player X Wins");
        reset();
    }
    private void play2wins(){
        result.setText("Player O Wins");
        //reset();
    }
    private void drawGame(){
        result.setText("It's a Draw");
        //reset();
    }

    private void reset(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                buttons[i][j].setText("");
            }
        }
       // result.setText("");
        roundCount =0 ;
        play1 =true;
    }
}
