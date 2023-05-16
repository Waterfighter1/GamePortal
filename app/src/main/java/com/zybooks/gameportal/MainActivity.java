package com.zybooks.gameportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.next);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText p1_name_box = (EditText)findViewById(R.id.edit_player1);
                String p1_name = p1_name_box.getText().toString();

                EditText p2_name_box = (EditText)findViewById(R.id.edit_player2);
                String p2_name = p2_name_box.getText().toString();

                if (p1_name == "" || p1_name == " " || p1_name == null || p1_name.length() < 1) {
                    p1_name = "Player 1";
                }

                if (p2_name == "" || p2_name == " " || p2_name == null || p2_name.length() < 1) {
                    p2_name = "Player 2";
                }

                editor.putString("p1_name", p1_name); // Storing string
                editor.putString("p2_name", p2_name); // Storing string

                editor.commit();

                Intent nextScreen = new Intent(MainActivity.this, gameselect.class);
                startActivity(nextScreen);

            }
        });

    }
}