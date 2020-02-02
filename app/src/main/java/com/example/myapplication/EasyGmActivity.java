package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class EasyGmActivity extends AppCompatActivity {
    private final Random rnd = new Random();
    private static int[] randomAnimals = {0, 0, 0, 0, 0, 0};
    private int rndSize = 0;
    private ImageView pic1;
    private ImageView pic2;
    private ImageView pic3;
    private ImageView pic4;
    private ImageView pic5;
    private ImageView pic6;
    private TextView gmCheck;
    private Button goBtn;
    private TextView timerEasy;
    public static final int A = 11;
    public static final int S = 6;
    public static final int TIME = 5000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
        pic1 = findViewById(R.id.pic1);
        pic2 = findViewById(R.id.pic2);
        pic3 = findViewById(R.id.pic3);
        pic4 = findViewById(R.id.pic4);
        pic5 = findViewById(R.id.pic5);
        pic6 = findViewById(R.id.pic6);
        gmCheck = findViewById(R.id.gmCheck);
        goBtn = findViewById(R.id.goBtn);
        timerEasy = findViewById(R.id.timerEasy);

        pic1.setImageDrawable(getResources().getDrawable(getResourceID("a" + getUniNumber(),"drawable", getApplicationContext())));
        pic2.setImageDrawable(getResources().getDrawable(getResourceID("a" + getUniNumber(),"drawable", getApplicationContext())));
        pic3.setImageDrawable(getResources().getDrawable(getResourceID("a" + getUniNumber(),"drawable", getApplicationContext())));
        pic4.setImageDrawable(getResources().getDrawable(getResourceID("a" + getUniNumber(),"drawable", getApplicationContext())));
        pic5.setImageDrawable(getResources().getDrawable(getResourceID("a" + getUniNumber(),"drawable", getApplicationContext())));
        pic6.setImageDrawable(getResources().getDrawable(getResourceID("a" + getUniNumber(),"drawable", getApplicationContext())));

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                goBtn.setVisibility(View.VISIBLE);
                pic1.setVisibility(View.INVISIBLE);
                pic2.setVisibility(View.INVISIBLE);
                pic3.setVisibility(View.INVISIBLE);
                pic4.setVisibility(View.INVISIBLE);
                pic5.setVisibility(View.INVISIBLE);
                pic6.setVisibility(View.INVISIBLE);


            }
        }, TIME);
        new CountDownTimer(TIME,1000){
            public void onTick(long millisUntilFinished){
                timerEasy.setText(""+millisUntilFinished / 1000);
            }
            public void onFinish(){
                timerEasy.setText("");
            }
        }.start();
        gmCheck.setText(MainActivity.mode);
    }
    private int getUniNumber() {

        while(true) {
            int flag=0;
            int index = rnd.nextInt(A);
            for (int j = 0; j < S; j++) {
                if(randomAnimals[j]== index) {
                    flag=1; // повтор найден
                }
            }
            if (flag == 0) {
                randomAnimals[rndSize++] = index;
               return index;
            }
        }

    }

    protected final static int getResourceID(final String resName, final String resType, final Context ctx){
        return ctx.getResources().getIdentifier(resName, resType, ctx.getApplicationInfo().packageName);

    }
    public void goPart2(View v) {
        Intent intent = new Intent(this, GamePart2Activity.class);
        startActivity(intent);

    }
    public static int getElementByIndex(int index) {
        return randomAnimals[index];
    }
}
