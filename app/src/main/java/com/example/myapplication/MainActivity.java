package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static String mode = "";
    private TextView playerNameView;
    public static String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerNameView = findViewById(R.id.playerName);
        playerNameView.setText("Guest");
        playerName = playerNameView.getText().toString();


    }

    public void easyGm(View v) {
        Intent intent = new Intent(this, EasyGmActivity.class);
        startActivity(intent);
        mode = "Easy";
        playerName = playerNameView.getText().toString();
    }
    public void hardGm(View v) {
        Intent intent = new Intent(this, EasyGmActivity.class);
        startActivity(intent);
        mode = "Hard";
        playerName = playerNameView.getText().toString();
    }
    public void expertGm(View v) {
        Intent intent = new Intent(this, ExpertGmActivity.class);
        startActivity(intent);
        mode = "Expert";
        playerName = playerNameView.getText().toString();
    }
    public void goScoresTable(View v) {
        Intent intent = new Intent(this, ScoresTableActivity.class);
        startActivity(intent);
        playerName = playerNameView.getText().toString();
    }
}
