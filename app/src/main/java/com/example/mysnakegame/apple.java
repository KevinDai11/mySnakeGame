package com.example.mysnakegame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class apple {
    private Bitmap bm;
    private int x,y;
    private Rect r;

    public apple(Bitmap bm, int x, int y) {
        this.bm = bm;
        this.x = x;
        this.y = y;
    }

    public Bitmap getBm() {
        return bm;
    }

    public void setBm(Bitmap bm) {
        this.bm = bm;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rect getR() {
        return new Rect(x, y, x+gameView.sizeOfMap,y+gameView.sizeOfMap);
    }

    public void setR(Rect r) {
        this.r = r;
    }

    public void draw(Canvas c){
        c.drawBitmap(bm, x,y,null);
    }

    public void reset(int x, int y) {
        this.x=x;
        this.y=y;
    }
}
