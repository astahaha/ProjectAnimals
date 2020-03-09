package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoresTableActivity extends AppCompatActivity {
    private int winScore = 0;
    private int loseScore = 0;
    private TextView winView;
    private TextView loseView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        SharedPreferences myScore = this.getSharedPreferences("Scores", Context.MODE_PRIVATE);
        winView = findViewById(R.id.viewWin);
        loseView = findViewById(R.id.viewLose);
        winScore = myScore.getInt("winScore", 0);
        loseScore = myScore.getInt("loseScore", 0);
        winView.setText(String.valueOf(winScore));
        loseView.setText(String.valueOf(loseScore));


    }
    public void goToMenu(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
