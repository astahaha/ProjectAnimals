package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoresTableActivity extends AppCompatActivity {
    //private String score;
    String[] records = {"1", "2"};
    String score = "Ivan,50;Dan,1;Ivan,1;Grisha,3;";
    List<Record> recordScores = new ArrayList<>();
    // map нужен чтобы не было двух пользователей в рекордах
    Map<String, Integer> map = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        SharedPreferences myScore = this.getSharedPreferences("TableScores", Context.MODE_PRIVATE);
        score = myScore.getString("records", "");


        ArrayList<String> finalRecords = new ArrayList<>();
        if (!score.equals("")) {
            // Парсинг истории игры
            String[] gameScores = score.split(";");
            for (String s : gameScores) {
                String[] items = s.split(","); // items = ["ivan", "5"], items[0]
                String name = items[0];
                Integer score = Integer.parseInt(items[1]);
                //Добавляем в map только если нет такого пользователя либо score ниже имеющегося
                if (map.containsKey(name) == false || map.get(name) > score) {
                    map.put(name, score);
                }
            }

            // сортировка рекордов
            recordScores = map.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
                    .map(e -> new Record(e.getKey(), e.getValue()))
                    .collect(Collectors.toList());


            for (Record r : recordScores) {
                finalRecords.add(r.getName() + "  -  " + r.getScore());
            }
        }

        ListView recordsList = findViewById(R.id.recordsList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, finalRecords);
        recordsList.setAdapter(adapter);


    }
    public void goToMenu(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
