package com.zybooks.gameportal;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class views extends AppCompatActivity {

    // Create The Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    // Menu On Clicks
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // If Home, Open Game Select
        if (item.getItemId() == R.id.action_home) {
            Intent nextScreen = new Intent(this, gameselect.class);
            startActivity(nextScreen);
        }
        // If Help, Open Game Help
        else if (item.getItemId() == R.id.action_help) {
            Intent nextScreen = new Intent(this, helpscreen.class);
            startActivity(nextScreen);
        }
        // If Players, Open Game Players
        else if (item.getItemId() == R.id.action_players) {
            Intent nextScreen = new Intent(this, MainActivity.class);
            startActivity(nextScreen);
        }

        return super.onOptionsItemSelected(item);
    }
}
