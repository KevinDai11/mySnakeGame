package com.example.mysnakegame;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class partSnakey {
    private Bitmap bm;
    private int x,y;
    private Rect rBody,rtop, rBottom, rRight, rLeft;

    public partSnakey(Bitmap bm, int x, int y) {
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

    public Rect getrBody() {
        return new Rect(x,y,x+gameView.sizeOfMap,this.y+gameView.sizeOfMap);
    }

    public void setrBody(Rect rBody) {
        this.rBody = rBody;
    }

    public Rect getRtop() {
        return new Rect(x,y-10*constants.screen_HEIGHT/1920,x+gameView.sizeOfMap,this.y);
    }

    public void setRtop(Rect rtop) {
        this.rtop = rtop;
    }

    public Rect getrBottom() {
        return new Rect(x,y+gameView.sizeOfMap,x+gameView.sizeOfMap,this.y+gameView.sizeOfMap+10*constants.screen_HEIGHT/1920);
    }

    public void setrBottom(Rect rBottom) {
        this.rBottom = rBottom;
    }

    public Rect getrRight() {
        return new Rect(x+gameView.sizeOfMap,y,x+gameView.sizeOfMap+10*constants.screen_WIDTH/1080,this.y+gameView.sizeOfMap);

    }

    public void setrRight(Rect rRight) {
        this.rRight = rRight;
    }

    public Rect getrLeft() {
        return new Rect(x-10*constants.screen_WIDTH/1080,y,x,this.y+gameView.sizeOfMap);
    }

    public void setrLeft(Rect rLeft) {
        this.rLeft = rLeft;
    }
}
