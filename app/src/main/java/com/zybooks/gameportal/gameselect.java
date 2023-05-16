package com.zybooks.gameportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class gameselect extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameselect);

        ImageView tictac = (ImageView) findViewById(R.id.tictac);

        TextView p1Name = (TextView) findViewById(R.id.p1_display);
        TextView p2Name = (TextView) findViewById(R.id.p2_display);


        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();


        String p1 = pref.getString("p1_name", "Player 1"); // Storing string
        String p2 = pref.getString("p2_name", "Player 2");

        p1Name.setText(p1);
        p2Name.setText(p2);

        tictac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextScreen = new Intent(gameselect.this, tictactoe.class);
                startActivity(nextScreen);
            }
        });
    }
}