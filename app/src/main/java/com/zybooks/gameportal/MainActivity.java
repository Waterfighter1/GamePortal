package com.zybooks.gameportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.next);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Moving On", Toast.LENGTH_SHORT).show();

                Intent nextScreen = new Intent(MainActivity.this, gameselect.class);
                startActivity(nextScreen);
                // TO DO: PASS DATA THROUGH SHARED PREFERENCES
            }
        });

    }
}