package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoresTableActivity extends AppCompatActivity {
    private String winScore;

    private TextView winView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        SharedPreferences myScore = this.getSharedPreferences("Scores", Context.MODE_PRIVATE);
        winView = findViewById(R.id.viewWin);
        winScore = myScore.getString("records", "");
        winView.setText(winScore);



    }
    public void goToMenu(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
