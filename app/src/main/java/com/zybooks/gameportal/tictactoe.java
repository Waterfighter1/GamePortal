package com.zybooks.gameportal;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class tictactoe extends views {

    MediaPlayer mediaPlayer;
    int[] spots_id = new int[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe2);

        // Defining Views
        TextView p1Name = (android.widget.TextView) findViewById(R.id.p1_displayName);
        TextView p2Name = (TextView) findViewById(R.id.p2_displayName);
        TextView currentTurnDisplay = (TextView) findViewById(R.id.currentTurn);
        Button reset = (Button) findViewById(R.id.reset);

        // Data Holding View
        TextView winner = (TextView) findViewById(R.id.winner);
        TextView turn = (TextView) findViewById(R.id.turn);

        // Set Some Views To Invisible
        winner.setVisibility(View.GONE);
        turn.setVisibility(View.GONE);
        reset.setVisibility(View.GONE);

        winner.setText("none");
        turn.setText("x");


        // Defining Shared Preferences Things
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        // Getting Names
        String p1 = pref.getString("p1_name", "Player 1");
        String p2 = pref.getString("p2_name", "Player 2");

       // Setting Texts
        currentTurnDisplay.setText(p1 + getResources().getString(R.string.player_turn) + " (X)");
        p1Name.setText(p1);
        p2Name.setText(p2);

        // Defining The Spot's Views and Their ID's ( To Check For Wins, Etc. )
        ImageView[] spots = new ImageView[9];
        spots_id = new int[9];

        spots[0] = (ImageView) findViewById(R.id.spot1);
        spots[1] = (ImageView) findViewById(R.id.spot2);
        spots[2] = (ImageView) findViewById(R.id.spot3);
        spots[3] = (ImageView) findViewById(R.id.spot4);
        spots[4] = (ImageView) findViewById(R.id.spot5);
        spots[5] = (ImageView) findViewById(R.id.spot6);
        spots[6] = (ImageView) findViewById(R.id.spot7);
        spots[7] = (ImageView) findViewById(R.id.spot8);
        spots[8] = (ImageView) findViewById(R.id.spot9);

        // For Each Of The Spots
        for (int i = 0; i < spots.length; i++) {
            // Rename it to make my life easier
            ImageView spot = spots[i];

            // Set the Spot ID To The Parallel
            spots_id[i] = spot.getId();

            // Go Ahead And Restart It ( Destroy The Previous Game )
            // Originally Was Going To Make a "Save"
            pref.edit().remove(String.valueOf(spots_id[i])).commit();

            // Setting The Images To Empty As Default
            spot.setImageResource(R.drawable.empty);

            // Clicky Boi
            spot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // Shared Preference Stuff
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                    SharedPreferences.Editor editor = pref.edit();

                    // Find The Winner View
                    TextView winner = (TextView) findViewById(R.id.winner);
                    TextView currentTurnDisplay = (TextView) findViewById(R.id.currentTurn);

                    // Checking That The Clicked View Isn't Occupied And A Winner Hasn't Been Decided
                    if (pref.getString(String.valueOf(view.getId()), null) == null && winner.getText() != "x" && winner.getText() != "o") {

                        // Creating An Audio Click Noise ( Not the best noise, best I could make )
                        mediaPlayer = MediaPlayer.create(tictactoe.this,R.raw.click);
                        mediaPlayer.start();

                        // If The Turn Is X ( 1 )
                        if (turn.getText() == "x") {

                            // Set The Image To X and The Turn To O
                            spot.setImageResource(R.drawable.x);
                            turn.setText("o");

                            // Set the ID to having X
                            editor.putString(String.valueOf(view.getId()), "x");
                            editor.commit();

                            // Set The Turn Text
                            String p2 = pref.getString("p2_name", "Player 2");
                            currentTurnDisplay.setText(p2 + getResources().getString(R.string.player_turn) + " (O)");

                        }
                        // If The Turn Is O ( 2 )
                        else {
                            // Set The Image To O and the Next Turn to X
                            spot.setImageResource(R.drawable.o);
                            turn.setText("x");

                            editor.putString(String.valueOf(view.getId()), "o");
                            editor.commit();

                            // Set The Turn Text
                            String p1 = pref.getString("p1_name", "Player 1");
                            currentTurnDisplay.setText(p1 + getResources().getString(R.string.player_turn) + " (X)");
                        }


                        // CHECK FOR WIN PROCESSING

                        // HORIZONTAL
                        // 1 2 3
                        if ((pref.getString(String.valueOf(spots_id[0]), "1") == pref.getString(String.valueOf(spots_id[1]), "2")) && (pref.getString(String.valueOf(spots_id[1]), "2") == pref.getString(String.valueOf(spots_id[2]), "3"))) {
                            winner.setText(pref.getString(String.valueOf(spots_id[0]) , "none"));
                        }
                        // 4 5 6
                        else if ((pref.getString(String.valueOf(spots_id[3]), "4") == pref.getString(String.valueOf(spots_id[4]), "5")) && (pref.getString(String.valueOf(spots_id[4]), "5") == pref.getString(String.valueOf(spots_id[5]), "6"))) {
                            winner.setText(pref.getString(String.valueOf(spots_id[3]) , "none"));
                        }
                        // 7 8 9
                        else if ((pref.getString(String.valueOf(spots_id[6]), "7") == pref.getString(String.valueOf(spots_id[7]), "8")) && (pref.getString(String.valueOf(spots_id[7]), "8") == pref.getString(String.valueOf(spots_id[8]), "9"))) {
                            winner.setText(pref.getString(String.valueOf(spots_id[6]) , "none"));
                        }

                        // VERTICLE
                        // 1 4 7
                        else if ((pref.getString(String.valueOf(spots_id[0]), "1") == pref.getString(String.valueOf(spots_id[3]), "4")) && (pref.getString(String.valueOf(spots_id[3]), "4") == pref.getString(String.valueOf(spots_id[6]), "7"))) {
                            winner.setText(pref.getString(String.valueOf(spots_id[0]) , "none"));
                        }
                        // 2 5 8
                        else if ((pref.getString(String.valueOf(spots_id[1]), "2") == pref.getString(String.valueOf(spots_id[4]), "5")) && (pref.getString(String.valueOf(spots_id[4]), "5") == pref.getString(String.valueOf(spots_id[7]), "8"))) {
                            winner.setText(pref.getString(String.valueOf(spots_id[1]) , "none"));
                        }
                        // 3 6 9
                        else if ((pref.getString(String.valueOf(spots_id[2]), "3") == pref.getString(String.valueOf(spots_id[5]), "6")) && (pref.getString(String.valueOf(spots_id[5]), "6") == pref.getString(String.valueOf(spots_id[8]), "9"))) {
                            winner.setText(pref.getString(String.valueOf(spots_id[2]) , "none"));
                        }

                        // DIAGONALS
                        // 1 5 9
                        else if ((pref.getString(String.valueOf(spots_id[0]), "1") == pref.getString(String.valueOf(spots_id[4]), "5")) && (pref.getString(String.valueOf(spots_id[4]), "5") == pref.getString(String.valueOf(spots_id[8]), "9"))) {
                            winner.setText(pref.getString(String.valueOf(spots_id[0]) , "none"));
                        }
                        // 3 5 7
                        else if ((pref.getString(String.valueOf(spots_id[2]), "3") == pref.getString(String.valueOf(spots_id[5]), "6")) && (pref.getString(String.valueOf(spots_id[5]), "6") == pref.getString(String.valueOf(spots_id[6]), "7"))) {
                            winner.setText(pref.getString(String.valueOf(spots_id[2]) , "none"));
                        }


                        // CHECK FOR CAT
                        if ((pref.getString(String.valueOf(spots_id[0]), "N") != "N") && (pref.getString(String.valueOf(spots_id[1]), "N") != "N") && (pref.getString(String.valueOf(spots_id[2]), "N") != "N") && (pref.getString(String.valueOf(spots_id[3]), "N") != "N") && (pref.getString(String.valueOf(spots_id[4]), "N") != "N") && (pref.getString(String.valueOf(spots_id[5]), "N") != "N") && (pref.getString(String.valueOf(spots_id[6]), "N") != "N") && (pref.getString(String.valueOf(spots_id[7]), "N") != "N") && (pref.getString(String.valueOf(spots_id[8]), "N") != "N")){
                            winner.setText("c");
                        }


                        // If the winner is X
                        if (winner.getText() == "x") {
                            currentTurnDisplay.setText(pref.getString("p1_name", "Player 1") + " wins!");
                            Button reset = (Button) findViewById(R.id.reset);
                            reset.setVisibility(View.VISIBLE);
                        }
                        // If the winner is O
                        else if (winner.getText() == "o") {
                            currentTurnDisplay.setText(pref.getString("p2_name", "Player 2") + " wins!");
                            Button reset = (Button) findViewById(R.id.reset);
                            reset.setVisibility(View.VISIBLE);
                        }
                        else if (winner.getText() == "c") {
                            currentTurnDisplay.setText("Cat's Game!");
                            Button reset = (Button) findViewById(R.id.reset);
                            reset.setVisibility(View.VISIBLE);
                        }

                    } // End of Checking If No Winner and Space Not Occupied
                } // End of On Click
            }); // End of Outer On Click Listener

            reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Data Holding Views
                    TextView winner = (TextView) findViewById(R.id.winner);
                    TextView turn = (TextView) findViewById(R.id.turn);
                    Button reset = (Button) findViewById(R.id.reset);
                    TextView currentTurnDisplay = (TextView) findViewById(R.id.currentTurn);

                    // Set Some Views To Invisible
                    winner.setVisibility(View.GONE);
                    turn.setVisibility(View.GONE);
                    reset.setVisibility(View.GONE);

                    // Getting First Players Name
                    String p1 = pref.getString("p1_name", "Player 1");

                    // Reset THe Title
                    currentTurnDisplay.setText(p1 + getResources().getString(R.string.player_turn) + " (X)");

                    // Reset Data Views
                    winner.setText("none");
                    turn.setText("x");

                    // Defining Shared Preferences Things
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                    SharedPreferences.Editor editor = pref.edit();

                    // Defining The Spot's Views and Their ID's ( To Check For Wins, Etc. )
                    ImageView[] spots = new ImageView[9];
                    spots_id = new int[9];


                    // Make The Array
                    spots[0] = (ImageView) findViewById(R.id.spot1);
                    spots[1] = (ImageView) findViewById(R.id.spot2);
                    spots[2] = (ImageView) findViewById(R.id.spot3);
                    spots[3] = (ImageView) findViewById(R.id.spot4);
                    spots[4] = (ImageView) findViewById(R.id.spot5);
                    spots[5] = (ImageView) findViewById(R.id.spot6);
                    spots[6] = (ImageView) findViewById(R.id.spot7);
                    spots[7] = (ImageView) findViewById(R.id.spot8);
                    spots[8] = (ImageView) findViewById(R.id.spot9);

                    // For Each Of The Spots Reset The Stuff
                    for (int i = 0; i < spots.length; i++) {
                        // Rename it to make my life easier
                        ImageView spot = spots[i];

                        // Set the Spot ID To The Parallel
                        spots_id[i] = spot.getId();

                        // Go Ahead And Restart It ( Destroy The Previous Game )
                        pref.edit().remove(String.valueOf(spots_id[i])).commit();

                        // Setting The Images To Empty As Default
                        spot.setImageResource(R.drawable.empty);
                    }
                }
            });

        } // For Statement
    } // End of On Create

    // On destroy release the media player as to not take up memory
    @Override
    protected void onDestroy() {

        super.onDestroy();
        try {
            mediaPlayer.release();
        }
        catch (Exception e) {
            // Just need to make sure it wont error out for not existing
        }
    }
}