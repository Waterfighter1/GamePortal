package com.zybooks.gameportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class gameselect extends views {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameselect);

        // Define Views
        ImageView tictac = (ImageView) findViewById(R.id.tictac);
        ImageView pong = (ImageView) findViewById(R.id.pong);
        ImageView connect = (ImageView) findViewById(R.id.connect);

        TextView p1Name = (TextView) findViewById(R.id.p1_display);
        TextView p2Name = (TextView) findViewById(R.id.p2_display);

        // Shared Preference Setup
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        // Get The Names
        String p1 = pref.getString("p1_name", "Player 1"); // Storing string
        String p2 = pref.getString("p2_name", "Player 2");

        // Set the Text With The Names
        p1Name.setText(p1);
        p2Name.setText(p2);

        // Tic Tac Toe Game Selection
        tictac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextScreen = new Intent(gameselect.this, tictactoe.class);
                startActivity(nextScreen);
            }
        });

        pong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextScreen = new Intent(gameselect.this, workinprogress.class);
                startActivity(nextScreen);
            }
        });

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextScreen = new Intent(gameselect.this, workinprogress.class);
                startActivity(nextScreen);
            }
        });
    }
}