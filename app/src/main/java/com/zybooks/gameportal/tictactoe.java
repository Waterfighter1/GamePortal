package com.zybooks.gameportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class tictactoe extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe2);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        TextView p1Name = (android.widget.TextView) findViewById(R.id.p1_displayName);
        TextView p2Name = (TextView) findViewById(R.id.p2_displayName);

        TextView turn = (TextView) findViewById(R.id.turn);
        turn.setVisibility(View.GONE);
        turn.setText("x");

        String p1 = pref.getString("p1_name", "Player 1");
        String p2 = pref.getString("p2_name", "Player 2");

        TextView currentTurnDisplay = (TextView) findViewById(R.id.currentTurn);
        currentTurnDisplay.setText(p1 + getResources().getString(R.string.player_turn) + " (X)");

        p1Name.setText(p1);
        p2Name.setText(p2);

        ImageView[] spots = new ImageView[9];
        int[] spots_id = new int[9];

        spots[0] = (ImageView) findViewById(R.id.spot1);
        spots[1] = (ImageView) findViewById(R.id.spot2);
        spots[2] = (ImageView) findViewById(R.id.spot3);
        spots[3] = (ImageView) findViewById(R.id.spot4);
        spots[4] = (ImageView) findViewById(R.id.spot5);
        spots[5] = (ImageView) findViewById(R.id.spot6);
        spots[6] = (ImageView) findViewById(R.id.spot7);
        spots[7] = (ImageView) findViewById(R.id.spot8);
        spots[8] = (ImageView) findViewById(R.id.spot9);

        for (int i = 0; i < spots.length; i++) {
            ImageView spot = spots[i];

            spots_id[i] = spot.getId();

            pref.edit().remove(String.valueOf(spots_id[i])).commit();

            spot.setImageResource(R.drawable.empty);
            spot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                    SharedPreferences.Editor editor = pref.edit();

                    if (pref.getString(String.valueOf(view.getId()), null) == null) {

                        if (turn.getText() == "x") {
                            spot.setImageResource(R.drawable.x);
                            turn.setText("o");
                            editor.putString(String.valueOf(view.getId()), "x");

                            String p2 = pref.getString("p2_name", "Player 2");

                            TextView currentTurnDisplay = (TextView) findViewById(R.id.currentTurn);
                            currentTurnDisplay.setText(p2 + getResources().getString(R.string.player_turn) + " (O)");

                        } else {
                            spot.setImageResource(R.drawable.o);
                            turn.setText("x");
                            editor.putString(String.valueOf(view.getId()), "o");

                            String p1 = pref.getString("p1_name", "Player 1");

                            TextView currentTurnDisplay = (TextView) findViewById(R.id.currentTurn);
                            currentTurnDisplay.setText(p1 + getResources().getString(R.string.player_turn) + " (X)");
                        }

                        editor.commit();
                    }
                }
            });
        }
    }
}