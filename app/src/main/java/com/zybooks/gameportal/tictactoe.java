package com.zybooks.gameportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class tictactoe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe2);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();


        TextView p1Name = (android.widget.TextView) findViewById(R.id.p1_displayName);
        TextView p2Name = (TextView) findViewById(R.id.p2_displayName);

        String p1 = pref.getString("p1_name", "Player 1"); // Storing string
        String p2 = pref.getString("p2_name", "Player 2");

        p1Name.setText(p1);
        p2Name.setText(p2);

    }
}