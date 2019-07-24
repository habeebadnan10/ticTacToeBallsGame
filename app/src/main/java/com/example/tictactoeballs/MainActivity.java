package com.example.tictactoeballs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0 is empty , 1 is white and 2 is red
    int player = 1;

    int[] gameStates = { 0, 0, 0, 0, 0, 0, 0, 0, 0};

    int[][] winningPositions = {{0,1,2} , {3,4,5} , {6,7,8} , {0,3,6} , {1,4,7} , {2,5,8} , {0,4,8} , {2,4,6}};

    Boolean inAction = true;

    public void dropIn(View view){

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameStates[tappedCounter] == 0 && inAction) {

            gameStates[tappedCounter] = player;

            counter.setTranslationY(-1500);

            if (player == 1) {

                counter.setImageResource(R.drawable.red);

                player = 2;

            } else {

                counter.setImageResource(R.drawable.white);

                player = 1;

            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(700);

            for (int[] winningPosition : winningPositions) {

                if (gameStates[winningPosition[0]] == gameStates[winningPosition[1]] && gameStates[winningPosition[1]] == gameStates[winningPosition[2]] && gameStates[winningPosition[0]] != 0) {

                    //someone has won
                     inAction = false;
                     String winner = player == 1 ? "White" : "Red";

                     Button restartButtonView = (Button) findViewById(R.id.restartButtonView);

                     TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                     winnerTextView.setText(winner + " has won");

                     restartButtonView.setVisibility(View.VISIBLE);

                     winnerTextView.setVisibility(View.VISIBLE);

                }

            }

        }

    }

    public void restart(View view){

        Log.i("restarted?" ,"yes");

        Button restartButtonView = (Button) findViewById(R.id.restartButtonView);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        restartButtonView.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);

        androidx.gridlayout.widget.GridLayout gridLayout = (androidx.gridlayout.widget.GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView child = (ImageView) gridLayout.getChildAt(i);
            // do stuff with child view

            child.setImageDrawable(null);
        }

        for(int i=0 ; i< gameStates.length ; i++){
            gameStates[i] = 0;
        }

        inAction = true;

        player = 1;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
