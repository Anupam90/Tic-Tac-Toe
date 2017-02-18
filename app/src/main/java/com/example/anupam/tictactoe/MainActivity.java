package com.example.anupam.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0 = yellow    1 = red
    int activePlayer = 0;

    //2 means unplayed
    int[] gamestate = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view)
    {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gamestate[tappedCounter] == 2)
        {

            gamestate[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);

            if(activePlayer == 0)
            {
                counter.setImageResource(R.drawable.yellow);

                activePlayer = 1;
            }
            else
            {
                counter.setImageResource(R.drawable.red);

                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for(int[] winningPosition : winningPositions)
            {
                if(gamestate[winningPosition[0]] == gamestate[winningPosition[1]] &&
                    gamestate[winningPosition[1]] == gamestate[winningPosition[2]] &&
                        gamestate[winningPosition[0]] !=2)
                {

                    //someone has won

                    String winner = "Red";

                    if(gamestate[winningPosition[0]] == 0)
                    {
                        winner = "yellow";
                    }
                    TextView textViewWinnerMessage = (TextView) findViewById(R.id.textViewWinnerMessage);
                    textViewWinnerMessage.setText(winner + " has won!");

                    LinearLayout playAgainLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
                    gridLayout.setVisibility(view.INVISIBLE);
                    playAgainLayout.setVisibility(view.VISIBLE);

                }

                //game is draw
                else
                {
                    boolean gameIsOver = true ;
                    for(int counterState : gamestate)
                    {
                        if(counterState == 2)
                        {
                            gameIsOver = false;
                        }
                    }
                    if(gameIsOver)
                    {
                        TextView textViewWinnerMessage = (TextView) findViewById(R.id.textViewWinnerMessage);
                        textViewWinnerMessage.setText(" It's a draw!");

                        LinearLayout playAgainLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
                        gridLayout.setVisibility(view.INVISIBLE);
                        playAgainLayout.setVisibility(view.VISIBLE);
                    }
                }
            }
        }

    }

    public void playAgain(View view)
    {
        LinearLayout playAgainLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
        playAgainLayout.setVisibility(view.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        gridLayout.setVisibility(view.VISIBLE);

        activePlayer = 0;

        for(int i = 0; i<gamestate.length ; i++)
        {
            gamestate[i] = 2;
        }
        for(int i = 0; i < gridLayout.getChildCount(); i++)
        {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
