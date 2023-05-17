package com.zybooks.gameportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends views {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find The Button ( Next Button )

        Button btn = (Button) findViewById(R.id.next);

        // Shared Preference Setup

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get The Edit Text Boxes Defined
                EditText p1_name_box = (EditText)findViewById(R.id.edit_player1);
                EditText p2_name_box = (EditText)findViewById(R.id.edit_player2);

                // Get The Data
                String p1_name = p1_name_box.getText().toString();
                String p2_name = p2_name_box.getText().toString();

                // If They're Empty Or A Space, Default The Names To Their Respective Player

                if (p1_name == "" || p1_name == " " || p1_name == null || p1_name.length() < 1) {
                    p1_name = "Player 1";
                }
                if (p2_name == "" || p2_name == " " || p2_name == null || p2_name.length() < 1) {
                    p2_name = "Player 2";
                }

                // Put The Names In Shared Preference
                editor.putString("p1_name", p1_name); // Storing string
                editor.putString("p2_name", p2_name); // Storing string

                // COMMIT
                editor.commit();

                // New Intent :)
                Intent nextScreen = new Intent(MainActivity.this, gameselect.class);
                startActivity(nextScreen);

            }
        });

    }
}