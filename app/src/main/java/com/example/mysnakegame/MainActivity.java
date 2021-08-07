package com.example.mysnakegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static int appleCount;
    public static LinearLayout info;
    public static TextView scor, startButton, bestScore, gameOver;

    private gameView gv;
    private boolean checkStart = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = getWindow().getDecorView();

        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        constants.screen_HEIGHT = dm.heightPixels;
        constants.screen_WIDTH = dm.widthPixels;
        setContentView(R.layout.activity_main);

        gv = findViewById(R.id.gv);
        info = findViewById(R.id.info);
        scor = findViewById(R.id.score);
        startButton = findViewById(R.id.startButton);
        bestScore = findViewById(R.id.bestScore);
        bestScore.setText(" x "+ gv.getBestScore());
        gameOver = findViewById(R.id.gameOver);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkStart){
                    gv.setStart(true);
                    info.setVisibility(View.VISIBLE);
                    startButton.setVisibility(View.INVISIBLE);
                    startButton.setText("Play Again");
                    startButton.setTextSize(16);
                    checkStart=!checkStart;
                }
                else{
                    startButton.setVisibility(View.INVISIBLE);
                    gameOver.setVisibility(View.INVISIBLE);
                    gv.reset();
                    gv.setStart(true);
                }
            }
        });
    }

}