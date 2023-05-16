package com.zybooks.gameportal;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class views extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // Determine which menu option was selected
        if (item.getItemId() == R.id.action_home) {
            Intent nextScreen = new Intent(this, gameselect.class);
            startActivity(nextScreen);
        }
        else if (item.getItemId() == R.id.action_help) {
            Intent nextScreen = new Intent(this, helpscreen.class);
            startActivity(nextScreen);
        }
        else if (item.getItemId() == R.id.action_players) {
            Intent nextScreen = new Intent(this, MainActivity.class);
            startActivity(nextScreen);
        }

        return super.onOptionsItemSelected(item);
    }
}
