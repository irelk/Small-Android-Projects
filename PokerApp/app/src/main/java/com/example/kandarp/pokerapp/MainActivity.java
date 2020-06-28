package com.example.kandarp.pokerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> deck = new ArrayList<String>();
    ArrayList<String> player1 = new ArrayList<String>();
    ArrayList<String> player2 = new ArrayList<String>();
    String p1Cards = "";
    String p2Cards = "";
    Button deal, compare;
    TextView player1Cards, player2Cards;
    TextView player1Result, player2Result;
    TextView pokerResult;
    ImageView imagep1c1, imagep1c2, imagep1c3, imagep1c4, imagep1c5;
    ImageView imagep2c1, imagep2c2, imagep2c3, imagep2c4, imagep2c5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        deal = (Button) findViewById(R.id.dealButton);
        compare = (Button) findViewById(R.id.compareButton);
        imagep1c1 = (ImageView) findViewById(R.id.imagep1c1);
        imagep1c2 = (ImageView) findViewById(R.id.imagep1c2);
        imagep1c3 = (ImageView) findViewById(R.id.imagep1c3);
        imagep1c4 = (ImageView) findViewById(R.id.imagep1c4);
        imagep1c5 = (ImageView) findViewById(R.id.imagep1c5);
        imagep2c1 = (ImageView) findViewById(R.id.imagep2c1);
        imagep2c2 = (ImageView) findViewById(R.id.imagep2c2);
        imagep2c3 = (ImageView) findViewById(R.id.imagep2c3);
        imagep2c4 = (ImageView) findViewById(R.id.imagep2c4);
        imagep2c5 = (ImageView) findViewById(R.id.imagep2c5);
        player1Cards = (TextView) findViewById(R.id.player1Cards);
        player2Cards = (TextView) findViewById(R.id.player2Cards);
        player1Result = (TextView) findViewById(R.id.player1Result);
        player2Result = (TextView) findViewById(R.id.player2Result);
        pokerResult = (TextView) findViewById(R.id.pokerResult);
        compare.setEnabled(false);

        createDeck();

        deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player1.clear();
                player2.clear();
                p1Cards = "";
                p2Cards ="";
                player1Cards.setText("");
                player2Cards.setText("");

                Collections.shuffle(deck);

                player1.add(deck.get(0));
                player1.add(deck.get(1));
                player1.add(deck.get(2));
                player1.add(deck.get(3));
                player1.add(deck.get(4));

                for (int i =0; i<player1.size(); i++){
                    p1Cards += player1.get(i) +"\n";
                }
                player1Cards.setText(p1Cards);
                int d1 = getResources().getIdentifier(removeSpace(player1.get(0)).toLowerCase(), "drawable", getPackageName());
                imagep1c1.setImageResource(d1);
                int d2 = getResources().getIdentifier(removeSpace(player1.get(1)).toLowerCase(), "drawable", getPackageName());
                imagep1c2.setImageResource(d2);
                int d3 = getResources().getIdentifier(removeSpace(player1.get(2)).toLowerCase(), "drawable", getPackageName());
                imagep1c3.setImageResource(d3);
                int d4 = getResources().getIdentifier(removeSpace(player1.get(3)).toLowerCase(), "drawable", getPackageName());
                imagep1c4.setImageResource(d4);
                int d5 = getResources().getIdentifier(removeSpace(player1.get(4)).toLowerCase(), "drawable", getPackageName());
                imagep1c5.setImageResource(d5);
                player2.add(deck.get(5));
                player2.add(deck.get(6));
                player2.add(deck.get(7));
                player2.add(deck.get(8));
                player2.add(deck.get(9));
                for (int i =0; i<player1.size(); i++){
                    p2Cards += player2.get(i) +"\n";
                }
                player2Cards.setText(p2Cards);

                int d6 = getResources().getIdentifier(removeSpace(player2.get(0)).toLowerCase(), "drawable", getPackageName());
                imagep2c1.setImageResource(d6);
                int d7 = getResources().getIdentifier(removeSpace(player2.get(1)).toLowerCase(), "drawable", getPackageName());
                imagep2c2.setImageResource(d7);
                int d8 = getResources().getIdentifier(removeSpace(player2.get(2)).toLowerCase(), "drawable", getPackageName());
                imagep2c3.setImageResource(d8);
                int d9 = getResources().getIdentifier(removeSpace(player2.get(3)).toLowerCase(), "drawable", getPackageName());
                imagep2c4.setImageResource(d9);
                int d10 = getResources().getIdentifier(removeSpace(player2.get(4)).toLowerCase(), "drawable", getPackageName());
                imagep2c5.setImageResource(d10);
                deal.setEnabled(false);
                compare.setEnabled(true);

            }
        });
        compare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compareHand(player1,player2);

                //disable compare button
                compare.setEnabled(false);
                deal.setEnabled(true);
            }
        });

    }

    public void compareHand(ArrayList<String> player1, ArrayList<String> player2){
        int[] f1 = new int[5];
        String[] s1 = new String[5];
        String[] card1 = player1.get(0).split(" of ");
        f1[0] = getFaceNumber(card1[0]);
        s1[0] = card1[1];
        String[] card2 = player1.get(1).split(" of ");
        f1[1] = getFaceNumber(card2[0]);
        s1[1]  = card1[1];
        String[] card3 = player1.get(2).split(" of ");
        f1[2] = getFaceNumber(card3[0]);
        s1[2]  = card1[1];
        String[] card4 = player1.get(3).split(" of ");
        f1[3] = getFaceNumber(card4[0]);
        s1[3]  = card1[1];
        String[] card5 = player1.get(4).split(" of ");
        f1[4] = getFaceNumber(card5[0]);
        s1[4]  = card1[1];
        sortArray(f1);
        String result1 = getHand(f1,s1);
        player1Result.setText(result1);
        int[] f2 = new int[5];
        String[] s2 = new String[5];
        String[] card6 = player2.get(0).split(" of ");
        f2[0] = getFaceNumber(card1[0]);
        s2[0] = card1[1];
        String[] card7 = player2.get(1).split(" of ");
        f2[1] = getFaceNumber(card2[0]);
        s2[1]  = card1[1];
        String[] card8 = player2.get(2).split(" of ");
        f2[2] = getFaceNumber(card3[0]);
        s2[2]  = card1[1];
        String[] card9 = player2.get(3).split(" of ");
        f2[3] = getFaceNumber(card4[0]);
        s2[3]  = card1[1];
        String[] card10 = player2.get(4).split(" of ");
        f2[4] = getFaceNumber(card5[0]);
        s2[4]  = card1[1];
        sortArray(f2);
        String result2 = getHand(f1,s1);
        player2Result.setText(result2);

    }

    public String getHand(int f[], String s[]){
        String result = "";
        if(s[0].equals(s[1]) && s[1].equals(s[2]) && s[2].equals(s[3]) && s[3].equals(s[4])){
            if (f[4] == 1){
                if (f[0] == (f[1]+1) && f[0] == (f[2]+2) && f[0] == (f[3]+3) && f[0] == (f[4]+4) ){
                    result = "Straight Flush";
                }else if(f[0] == 13 && f[1] == 12 && f[2] == 11 && f[3] == 10 && f[4] == 1){
                    result = "Royal Flush";
                }
            }else if ( f[0] == (f[1]+1) && f[0] == (f[2]+2) && f[0] == (f[3]+3) && f[0] == (f[4]+4) ){
                result ="Straight Flush";
            }else{
                result = "Flush";
            }
        }else if (f[4] == 1){
            if (f[0] == (f[1]+1) && f[0] == (f[2]+2) && f[0] == (f[3]+3) && f[0] == (f[4]+4) ){
                result = "Ace High Straight";
            }else if(f[0] == 13 && f[1] == 12 && f[2] == 11 && f[3] == 10 && f[4] == 1){
                result = "Five High Straight";
            }
        }else if ( f[0] == (f[1]+1) && f[0] == (f[2]+2) && f[0] == (f[3]+3) && f[0] == (f[4]+4) ){
            result ="Straight";
        }
        int pair = checkPair(f);
        if (pair == 1){
            result = "One Pair";
        }else if (pair == 2){
            result = "Two Pair";
        }else if (pair == 3){
            result = "Three of a Kind";
        }else if (pair == 4){
            result = "Full House";
        }else if (pair == 6){
            result = "Four of a Kind";
        }else {
            result = "No Pair";
        }

        return result;
    }


    public int getFaceNumber(String face){
        int n =0;
        switch (face){
            case "Ace": n=1;
                break;
            case "Deuce": n=2;
                break;
            case "Three": n=3;
                break;
            case "Four": n=4;
                break;
            case "Five": n=5;
                break;
            case "Six": n=6;
                break;
            case "Seven": n=7;
                break;
            case "Eight": n=8;
                break;
            case "Nine": n=9;
                break;
            case "Ten": n=10;
                break;
            case "Jack": n=11;
                break;
            case "Queen": n=12;
                break;
            case "King": n=13;
                break;
        }

        return n;
    }


    public int checkPair(int f[]){
        int pair = 0;

        for (int i = 0; i < f.length; i++){
            for (int j = i+1; j< f.length; j++){
                if (f[i] < f[j]){
                    pair = pair +1 ;
                }
            }
        }

        return pair;
    }

    static String removeSpace(String str)
    {
        str = str.replaceAll("\\s","");
        return str;
    }

    public void sortArray(int arr[]){
        int temp;
        for (int i = 0; i < arr.length; i++){
            for (int j = i+1; j< arr.length; j++){
                if (arr[i] < arr[j]){
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }


    public void createDeck(){
        ArrayList<String> face = new ArrayList<String>(Arrays.asList(
                "Ace", "Deuce", "Three", "Four", "Five", "Six",
                "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"
        ));
        ArrayList<String> suits = new ArrayList<String>(Arrays.asList(
                "Hearts", "Diamonds", "Clubs", "Spades"
        ));
        //get deck of cards
        for(int i=0; i < 52 ; i++){
            deck.add(face.get(i%13) + " of " + suits.get(i/13));
        }
    }
}
