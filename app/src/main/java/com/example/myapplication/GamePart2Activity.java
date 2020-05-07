package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import static com.example.myapplication.EasyGmActivity.A;
import static com.example.myapplication.EasyGmActivity.S;

public class GamePart2Activity extends AppCompatActivity {
    private TextView resultText;
    private ImageButton picc1;
    private ImageButton picc2;
    private ImageButton picc3;
    private ImageButton picc4;
    private ImageButton picc5;
    private ImageButton picc6;
    private ImageButton picc7;
    private ImageButton picc8;
    private ImageButton picc9;
    private Button backMenu;
    private final Random rnd = new Random();
    private int[] mixAnimals = new int[9];
    private int[] rightAnimals = new int[S];
    private int rndSize = S;
    private int countRight = 0;
    private int countAnswers = 0;

    private long timeStart, timeEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_part2);
        resultText = findViewById(R.id.resultText);
        picc1 = findViewById(R.id.picc1);
        picc2 = findViewById(R.id.picc2);
        picc3 = findViewById(R.id.picc3);
        picc4 = findViewById(R.id.picc4);
        picc5 = findViewById(R.id.picc5);
        picc6 = findViewById(R.id.picc6);
        picc7 = findViewById(R.id.picc7);
        picc8 = findViewById(R.id.picc8);
        picc9 = findViewById(R.id.picc9);
        backMenu = findViewById(R.id.backMenu);

        formMixAnimals();

        picc1.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[0], "drawable", getApplicationContext())));
        picc2.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[1], "drawable", getApplicationContext())));
        picc3.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[2], "drawable", getApplicationContext())));
        picc4.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[3], "drawable", getApplicationContext())));
        picc5.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[4], "drawable", getApplicationContext())));
        picc6.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[5], "drawable", getApplicationContext())));

        picc7.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[6], "drawable", getApplicationContext())));
        picc8.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[7], "drawable", getApplicationContext())));
        picc9.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[8], "drawable", getApplicationContext())));
        backMenu.setVisibility(View.INVISIBLE);

        timeStart = System.currentTimeMillis();
    }


    private void formMixAnimals() {
        for (int i = 0; i < S; i++) {
            if (MainActivity.mode.equals("Expert")) {
                mixAnimals[i] = ExpertGmActivity.getElementByIndex(i);
                rightAnimals[i] = ExpertGmActivity.getElementByIndex(i);
            } else {
                mixAnimals[i] = EasyGmActivity.getElementByIndex(i);
                rightAnimals[i] = EasyGmActivity.getElementByIndex(i);
            }
        }
        for (int i = S; i < 9; i++) {
            while (true) {
                int flag = 0;
                int imgIndex = rnd.nextInt(10);
                for (int j = 0; j < i; j++) {
                    if (mixAnimals[j] == imgIndex) {
                        flag = 1; // повтор найден
                    }
                }
                if (flag == 0) {
                    mixAnimals[rndSize] = imgIndex;
                    rndSize++;
                    break;
                }
            }

        }
        //перемешать массив
        for (int i = 0; i < 36; i++) {
            int i1 = rnd.nextInt(9);
            int i2 = rnd.nextInt(9);
            int z;
            z = mixAnimals[i1];
            mixAnimals[i1] = mixAnimals[i2];
            mixAnimals[i2] = z;
        }
    }

    protected final static int getResourceID(final String resName, final String resType,
                                             final Context ctx) {
        return ctx.getResources().getIdentifier(resName, resType, ctx.getApplicationInfo().packageName);

    }

    public void picc1Handler(View v) {
        checkChoise(0);
        v.setEnabled(false);

    }

    public void picc2Handler(View v) {
        checkChoise(1);
        v.setEnabled(false);
    }

    public void picc3Handler(View v) {
        checkChoise(2);
        v.setEnabled(false);
    }

    public void picc4Handler(View v) {
        checkChoise(3);
        v.setEnabled(false);
    }

    public void picc5Handler(View v) {
        checkChoise(4);
        v.setEnabled(false);
    }

    public void picc6Handler(View v) {
        checkChoise(5);
        v.setEnabled(false);
    }

    public void picc7Handler(View v) {
        checkChoise(6);
        v.setEnabled(false);
    }

    public void picc8Handler(View v) {
        checkChoise(7);
        v.setEnabled(false);
    }

    public void picc9Handler(View v) {
        checkChoise(8);
        v.setEnabled(false);
    }

    public void backMenu(View v) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    private void saveScore() {
        long scoreValue = (timeEnd - timeStart) / 100;
        SharedPreferences myScore = this.getSharedPreferences("TableScores", Context.MODE_PRIVATE);
        String records = myScore.getString("records", "");
        records += (MainActivity.playerName + "," + scoreValue + ";");// "Ivan"
        SharedPreferences.Editor editor = getSharedPreferences("TableScores", Context.MODE_PRIVATE).edit();
        editor.putString("records", records);
        // editor.putString("records", ""); // сброс рекордов
        editor.commit();
    }

    private void checkChoise(int index) {
        countAnswers++;
        if (countAnswers == 6) {
            backMenu.setVisibility(View.VISIBLE);
            blockAllImages();
            resultText.setText("Take an L");

        }
        int flag = 0;
        for (int i = 0; i < S; i++) {
            if (rightAnimals[i] == mixAnimals[index]) {
                if (MainActivity.mode.equals("Hard")) {
                    resultText.setText("Take an L");
                    //scoreLoseUp();
                    blockAllImages();
                    backMenu.setVisibility(View.VISIBLE);
                    if (index == 0)
                        picc1.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "w", "drawable", getApplicationContext())));
                    else if (index == 1)
                        picc2.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "w", "drawable", getApplicationContext())));
                    else if (index == 2)
                        picc3.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "w", "drawable", getApplicationContext())));
                    else if (index == 3)
                        picc4.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "w", "drawable", getApplicationContext())));
                    else if (index == 4)
                        picc5.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "w", "drawable", getApplicationContext())));
                    else if (index == 5)
                        picc6.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "w", "drawable", getApplicationContext())));
                    else if (index == 6)
                        picc7.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "w", "drawable", getApplicationContext())));
                    else if (index == 7)
                        picc8.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "w", "drawable", getApplicationContext())));
                    else if (index == 8)
                        picc9.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "w", "drawable", getApplicationContext())));
                    return;
                }
                flag = 1;
                countRight++;
                if (countRight == 6) {
                    resultText.setText("WIN");
                    //обновление счета
                    timeEnd = System.currentTimeMillis();
                    saveScore();


                }
                if (index == 0)
                    picc1.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "r", "drawable", getApplicationContext())));
                else if (index == 1)
                    picc2.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "r", "drawable", getApplicationContext())));
                else if (index == 2)
                    picc3.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "r", "drawable", getApplicationContext())));
                else if (index == 3)
                    picc4.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "r", "drawable", getApplicationContext())));
                else if (index == 4)
                    picc5.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "r", "drawable", getApplicationContext())));
                else if (index == 5)
                    picc6.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "r", "drawable", getApplicationContext())));
                else if (index == 6)
                    picc7.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "r", "drawable", getApplicationContext())));
                else if (index == 7)
                    picc8.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "r", "drawable", getApplicationContext())));
                else if (index == 8)
                    picc9.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "r", "drawable", getApplicationContext())));


                break;

            }
        }

        if (flag == 0) {
            if (MainActivity.mode.equals("Hard")) {

                if (index == 0)
                    picc1.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "r", "drawable", getApplicationContext())));
                else if (index == 1)
                    picc2.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "r", "drawable", getApplicationContext())));
                else if (index == 2)
                    picc3.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "r", "drawable", getApplicationContext())));
                else if (index == 3)
                    picc4.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "r", "drawable", getApplicationContext())));
                else if (index == 4)
                    picc5.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "r", "drawable", getApplicationContext())));
                else if (index == 5)
                    picc6.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "r", "drawable", getApplicationContext())));
                else if (index == 6)
                    picc7.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "r", "drawable", getApplicationContext())));
                else if (index == 7)
                    picc8.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "r", "drawable", getApplicationContext())));
                else if (index == 8)
                    picc9.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "r", "drawable", getApplicationContext())));
                countRight++;
                if (countRight == 3) {
                    resultText.setText("WIN");
                    backMenu.setVisibility(View.VISIBLE);
                    blockAllImages();
                    saveScore();

                }
                return;

            }
            resultText.setText("Take an L");
            //scoreLoseUp();
            blockAllImages();
            backMenu.setVisibility(View.VISIBLE);
            if (index == 0)
                picc1.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "w", "drawable", getApplicationContext())));
            else if (index == 1)
                picc2.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "w", "drawable", getApplicationContext())));
            else if (index == 2)
                picc3.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "w", "drawable", getApplicationContext())));
            else if (index == 3)
                picc4.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "w", "drawable", getApplicationContext())));
            else if (index == 4)
                picc5.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "w", "drawable", getApplicationContext())));
            else if (index == 5)
                picc6.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "w", "drawable", getApplicationContext())));
            else if (index == 6)
                picc7.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "w", "drawable", getApplicationContext())));
            else if (index == 7)
                picc8.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "w", "drawable", getApplicationContext())));
            else if (index == 8)
                picc9.setImageDrawable(getResources().getDrawable(getResourceID("a" + mixAnimals[index] + "w", "drawable", getApplicationContext())));

        }


    }

    private void blockAllImages() {

        picc1.setEnabled(false);
        picc2.setEnabled(false);
        picc3.setEnabled(false);
        picc4.setEnabled(false);
        picc5.setEnabled(false);
        picc6.setEnabled(false);
        picc7.setEnabled(false);
        picc8.setEnabled(false);
        picc9.setEnabled(false);


    }

}
