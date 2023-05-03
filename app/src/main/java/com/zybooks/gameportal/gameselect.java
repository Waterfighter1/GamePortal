package com.zybooks.gameportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class gameselect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameselect);

        ImageView tictac = (ImageView) findViewById(R.id.tictac);

        tictac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(gameselect.this, "tic tac toe selected", Toast.LENGTH_SHORT).show();

                Intent nextScreen = new Intent(gameselect.this, tictactoe.class);
                startActivity(nextScreen);
            }
        });
    }
}