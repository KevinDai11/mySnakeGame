package com.example.mysnakegame;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class gameView extends View {
    public static int sizeOfMap = 75*constants.screen_WIDTH/1080;



    private Bitmap bmGrass1, bmGrass2, bmSnakey, bmApple;
    private int h = 21, w=12;
    private int bestScore;
    private ArrayList<Grass> arrGrass = new ArrayList<>();
    private Snakey snakey;
    private apple app;
    private boolean move = false;
    private float mx,my;
    private Handler handler;
    private Runnable r;
    private boolean start;
    private Context context;

    public gameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bmGrass1 = BitmapFactory.decodeResource(getResources(), R.drawable.grass);
        bmGrass1 = Bitmap.createScaledBitmap(bmGrass1,sizeOfMap,sizeOfMap,true);
        bmGrass2 = BitmapFactory.decodeResource(getResources(), R.drawable.grass03);
        bmGrass2 = Bitmap.createScaledBitmap(bmGrass2,sizeOfMap,sizeOfMap,true);
        bmSnakey = BitmapFactory.decodeResource(getResources(), R.drawable.snake1);
        bmSnakey = Bitmap.createScaledBitmap(bmSnakey,14*sizeOfMap,sizeOfMap,true);
        bmApple = BitmapFactory.decodeResource(getResources(), R.drawable.apple);
        bmApple = Bitmap.createScaledBitmap(bmApple,sizeOfMap,sizeOfMap,true);
        for(int x=0;x<h;x++){
            for(int y=0;y<w;y++){
                if((x+y)%2==0){
                    arrGrass.add(new Grass(bmGrass1, y*sizeOfMap + constants.screen_WIDTH/2-(w/2)*sizeOfMap,x*sizeOfMap+100*constants.screen_HEIGHT/1920,sizeOfMap,sizeOfMap));
                }
                else{
                    arrGrass.add(new Grass(bmGrass2, y*sizeOfMap + constants.screen_WIDTH/2-(w/2)*sizeOfMap,x*sizeOfMap+100*constants.screen_HEIGHT/1920,sizeOfMap,sizeOfMap));
                }
            }
        }
        snakey = new Snakey(bmSnakey, arrGrass.get(126).getX(),arrGrass.get(126).getY(),3);
        app = new apple(bmApple, arrGrass.get(randomInt()[0]).getX(),arrGrass.get(randomInt()[1]).getY());
        SharedPreferences sp = context.getSharedPreferences("gamesetting", Context.MODE_PRIVATE);
        if(sp!=null){
            bestScore = sp.getInt("bestscore",0);
        }

        this.context=context;
        start = false;
        handler = new Handler();
        r = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int a = event.getActionMasked();
        switch(a){
            case MotionEvent.ACTION_MOVE:{
                if(move == false){
                    mx = event.getX();
                    my = event.getY();
                    move = true;
                }
                else{
                    if(mx - event.getX() > 100*constants.screen_WIDTH/1080 && !snakey.isMoveRight()){
                        mx = event.getX();
                        my = event.getY();
                        snakey.setMoveLeft(true);

                    }
                    else if(event.getX() - mx> 100*constants.screen_WIDTH/1080 && !snakey.isMoveLeft()){
                        mx = event.getX();
                        my = event.getY();
                        snakey.setMoveRight(true);
                    }
                    else if(my - event.getY() > 100*constants.screen_WIDTH/1080 && !snakey.isMoveDown()){
                        mx = event.getX();
                        my = event.getY();
                        snakey.setMoveUp(true);
                    }
                    else if(event.getY() - my > 100*constants.screen_WIDTH/1080 && !snakey.isMoveUp()){
                        mx = event.getX();
                        my = event.getY();
                        snakey.setMoveDown(true);
                    }
                }
                Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                },200);
                break;
            }
            case MotionEvent.ACTION_UP:{
                mx =0;
                my=0;
                move = false;
                break;
            }
        }
        return true;
    }
    public int[] randomInt(){
        int[] xy = new int[2];
        Random r=  new Random();
        xy[0] = r.nextInt(arrGrass.size() -1);
        xy[1] = r.nextInt(arrGrass.size() -1);
        Rect re = new Rect(arrGrass.get(xy[0]).getX(), arrGrass.get(xy[1]).getY(),arrGrass.get(xy[0]).getX()+sizeOfMap, arrGrass.get(xy[1]).getY()+sizeOfMap);
        boolean check = true;
        while(check){
            check = false;
            for(int x=0;x<snakey.getArrPartSnake().size();x++){
                if(re.intersect(snakey.getArrPartSnake().get(x).getrBody())){
                    check = true;
                    xy[0] = r.nextInt(arrGrass.size() -1);
                    xy[1] = r.nextInt(arrGrass.size() -1);
                    re = new Rect(arrGrass.get(xy[0]).getX(), arrGrass.get(xy[1]).getY(),arrGrass.get(xy[0]).getX()+sizeOfMap, arrGrass.get(xy[1]).getY()+sizeOfMap);
                }
            }
        }
        return xy;
    }
    public void checkBodyCollison(){
        for(int x=1;x<snakey.getArrPartSnake().size();x++){
            if(snakey.getArrPartSnake().get(0).getrBody().intersect(snakey.getArrPartSnake().get(x).getrBody())){
                endgame();
            }
        }
    }
    public void checkOutOfBounds() {
        if(snakey.getArrPartSnake().get(0).getX()<arrGrass.get(0).getX() || snakey.getArrPartSnake().get(0).getY()<arrGrass.get(0).getY()
                || snakey.getArrPartSnake().get(0).getX()>arrGrass.get(arrGrass.size()-1).getX() || snakey.getArrPartSnake().get(0).getY()>arrGrass.get(arrGrass.size()-1).getY()){
            endgame();
        }
    }

    private void endgame() {
        start = false;
        if(MainActivity.appleCount > bestScore) {
            bestScore = MainActivity.appleCount;
            SharedPreferences sp = context.getSharedPreferences("gamesetting", Context.MODE_PRIVATE);
            SharedPreferences.Editor e = sp.edit();
            e.putInt("bestscore", bestScore);
            e.apply();
        }
        MainActivity.bestScore.setText(" x "+bestScore);
        MainActivity.startButton.setVisibility(VISIBLE);
        MainActivity.gameOver.setVisibility(VISIBLE);
    }

    public void reset(){
        snakey = new Snakey(bmSnakey, arrGrass.get(126).getX(),arrGrass.get(126).getY(),3);
        MainActivity.appleCount = 0;
        MainActivity.scor.setText(" x "+MainActivity.appleCount);
    }

    public void setStart(boolean s){
        start = s;
    }
    public int getBestScore(){
        return bestScore;
    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(0xFF1A6100);
        for(int x=0;x<arrGrass.size();x++){
            canvas.drawBitmap(arrGrass.get(x).getBm(),arrGrass.get(x).getX(),arrGrass.get(x).getY(),null);
        }
        if(start){
            snakey.update();
            app.draw(canvas);
            if(snakey.getArrPartSnake().get(0).getrBody().intersect(app.getR())){
                randomInt();
                app.reset(arrGrass.get(randomInt()[0]).getX(),arrGrass.get(randomInt()[1]).getY());
                snakey.addPart();
                MainActivity.appleCount+=1;

                MainActivity.scor.setText(" x "+MainActivity.appleCount);
                if(MainActivity.appleCount > bestScore){
                    MainActivity.bestScore.setText(" x "+MainActivity.appleCount);
                }
            }

            checkOutOfBounds();
            checkBodyCollison();
        }
        snakey.draw(canvas);
        handler.postDelayed(r, 150);
    }




}
