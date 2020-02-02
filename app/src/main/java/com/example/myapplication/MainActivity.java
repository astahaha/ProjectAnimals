package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static String mode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void easyGm(View v) {
        Intent intent = new Intent(this, EasyGmActivity.class);
        startActivity(intent);
        mode = "Easy";

    }
    public void hardGm(View v) {
        Intent intent = new Intent(this, EasyGmActivity.class);
        startActivity(intent);
        mode = "Hard";

    }
}
