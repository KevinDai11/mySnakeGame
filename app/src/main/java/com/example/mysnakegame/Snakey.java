package com.example.mysnakegame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;


public class Snakey {

    private boolean moveLeft, moveRight, moveUp, moveDown;
    private Bitmap bm, bmHeadUp, bmHeadDown, bmHeadLeft, bmHeadRight, bmBodyVert, bmBodyHori, bmBodyTopRight,
            bmBodyTopLeft, bmBodyBottomRight, bmBodyBottomLeft, bmTailRight, bmTailLeft, bmTailUp,bmTailDown;

    private int x,y,length;
    private ArrayList<partSnakey> arrPartSnake = new ArrayList<>();

    public Snakey(Bitmap bm, int x, int y, int length) {
        this.bm = bm;
        this.x = x;
        this.y = y;
        this.length = length;
        bmBodyBottomLeft = Bitmap.createBitmap(bm,0,0,gameView.sizeOfMap,gameView.sizeOfMap);
        bmBodyBottomRight = Bitmap.createBitmap(bm,gameView.sizeOfMap,0,gameView.sizeOfMap,gameView.sizeOfMap);
        bmBodyHori = Bitmap.createBitmap(bm,2*gameView.sizeOfMap,0,gameView.sizeOfMap,gameView.sizeOfMap);
        bmBodyTopLeft = Bitmap.createBitmap(bm,3*gameView.sizeOfMap,0,gameView.sizeOfMap,gameView.sizeOfMap);
        bmBodyTopRight = Bitmap.createBitmap(bm,4*gameView.sizeOfMap,0,gameView.sizeOfMap,gameView.sizeOfMap);
        bmBodyVert = Bitmap.createBitmap(bm,5*gameView.sizeOfMap,0,gameView.sizeOfMap,gameView.sizeOfMap);
        bmHeadDown = Bitmap.createBitmap(bm,6*gameView.sizeOfMap,0,gameView.sizeOfMap,gameView.sizeOfMap);
        bmHeadLeft = Bitmap.createBitmap(bm,7*gameView.sizeOfMap,0,gameView.sizeOfMap,gameView.sizeOfMap);
        bmHeadRight = Bitmap.createBitmap(bm,8*gameView.sizeOfMap,0,gameView.sizeOfMap,gameView.sizeOfMap);
        bmHeadUp = Bitmap.createBitmap(bm,9*gameView.sizeOfMap,0,gameView.sizeOfMap,gameView.sizeOfMap);
        bmTailUp = Bitmap.createBitmap(bm,10*gameView.sizeOfMap,0,gameView.sizeOfMap,gameView.sizeOfMap);
        bmTailRight = Bitmap.createBitmap(bm,11*gameView.sizeOfMap,0,gameView.sizeOfMap,gameView.sizeOfMap);
        bmTailLeft = Bitmap.createBitmap(bm,12*gameView.sizeOfMap,0,gameView.sizeOfMap,gameView.sizeOfMap);
        bmTailDown = Bitmap.createBitmap(bm,13*gameView.sizeOfMap,0,gameView.sizeOfMap,gameView.sizeOfMap);
        arrPartSnake.add(new partSnakey(bmHeadRight, x,y));
        for(int i=1;i<length-1;i++){
            arrPartSnake.add(new partSnakey(bmBodyHori,arrPartSnake.get(i-1).getX()-gameView.sizeOfMap,y));
        }
        arrPartSnake.add(new partSnakey(bmTailRight,arrPartSnake.get(length-2).getX()-gameView.sizeOfMap,y));
        setMoveRight(true);

    }

    public void update(){
        for(int x = length-1; x>0;x--){
            arrPartSnake.get(x).setX(arrPartSnake.get(x-1).getX());
            arrPartSnake.get(x).setY(arrPartSnake.get(x-1).getY());
        }
        if(moveRight){
            arrPartSnake.get(0).setX(arrPartSnake.get(0).getX()+gameView.sizeOfMap);
            arrPartSnake.get(0).setBm(bmHeadRight);
        }
        else if(moveLeft){
            arrPartSnake.get(0).setX(arrPartSnake.get(0).getX()-gameView.sizeOfMap);
            arrPartSnake.get(0).setBm(bmHeadLeft);
        }
        else if(moveUp){
            arrPartSnake.get(0).setY(arrPartSnake.get(0).getY()-gameView.sizeOfMap);
            arrPartSnake.get(0).setBm(bmHeadUp);
        }
        else if(moveDown){
            arrPartSnake.get(0).setY(arrPartSnake.get(0).getY()+gameView.sizeOfMap);
            arrPartSnake.get(0).setBm(bmHeadDown);
        }
        for(int x=1;x<length-1;x++){
            if(arrPartSnake.get(x).getrLeft().intersect(arrPartSnake.get(x+1).getrBody()) && arrPartSnake.get(x).getrBottom().intersect(arrPartSnake.get(x-1).getrBody())
            || arrPartSnake.get(x).getrLeft().intersect(arrPartSnake.get(x-1).getrBody()) && arrPartSnake.get(x).getrBottom().intersect(arrPartSnake.get(x+1).getrBody())){
                arrPartSnake.get(x).setBm(bmBodyBottomLeft);
            }
            else if(arrPartSnake.get(x).getrRight().intersect(arrPartSnake.get(x+1).getrBody()) && arrPartSnake.get(x).getrBottom().intersect(arrPartSnake.get(x-1).getrBody())
                    || arrPartSnake.get(x).getrRight().intersect(arrPartSnake.get(x-1).getrBody()) && arrPartSnake.get(x).getrBottom().intersect(arrPartSnake.get(x+1).getrBody())){
                arrPartSnake.get(x).setBm(bmBodyBottomRight);
            }
            else if(arrPartSnake.get(x).getrLeft().intersect(arrPartSnake.get(x+1).getrBody()) && arrPartSnake.get(x).getRtop().intersect(arrPartSnake.get(x-1).getrBody())
                    || arrPartSnake.get(x).getrLeft().intersect(arrPartSnake.get(x-1).getrBody()) && arrPartSnake.get(x).getRtop().intersect(arrPartSnake.get(x+1).getrBody())){
                arrPartSnake.get(x).setBm(bmBodyTopLeft);
            }
            else if(arrPartSnake.get(x).getrRight().intersect(arrPartSnake.get(x+1).getrBody()) && arrPartSnake.get(x).getRtop().intersect(arrPartSnake.get(x-1).getrBody())
                    || arrPartSnake.get(x).getrRight().intersect(arrPartSnake.get(x-1).getrBody()) && arrPartSnake.get(x).getRtop().intersect(arrPartSnake.get(x+1).getrBody())){
                arrPartSnake.get(x).setBm(bmBodyTopRight);
            }
            else if(arrPartSnake.get(x).getRtop().intersect(arrPartSnake.get(x+1).getrBody()) && arrPartSnake.get(x).getrBottom().intersect(arrPartSnake.get(x-1).getrBody())
                    || arrPartSnake.get(x).getRtop().intersect(arrPartSnake.get(x-1).getrBody()) && arrPartSnake.get(x).getrBottom().intersect(arrPartSnake.get(x+1).getrBody())){
                arrPartSnake.get(x).setBm(bmBodyVert);
            }
            else if(arrPartSnake.get(x).getrLeft().intersect(arrPartSnake.get(x+1).getrBody()) && arrPartSnake.get(x).getrRight().intersect(arrPartSnake.get(x-1).getrBody())
                    || arrPartSnake.get(x).getrLeft().intersect(arrPartSnake.get(x-1).getrBody()) && arrPartSnake.get(x).getrRight().intersect(arrPartSnake.get(x+1).getrBody())){
                arrPartSnake.get(x).setBm(bmBodyHori);
            }
        }
        if(arrPartSnake.get(length-1).getrRight().intersect(arrPartSnake.get(length-2).getrBody())){
            arrPartSnake.get(length-1).setBm(bmTailRight);
        }
        else if(arrPartSnake.get(length-1).getrLeft().intersect(arrPartSnake.get(length-2).getrBody())){
            arrPartSnake.get(length-1).setBm(bmTailLeft);
        }
        else if(arrPartSnake.get(length-1).getRtop().intersect(arrPartSnake.get(length-2).getrBody())){
            arrPartSnake.get(length-1).setBm(bmTailUp);
        }
        else if(arrPartSnake.get(length-1).getrBottom().intersect(arrPartSnake.get(length-2).getrBody())){
            arrPartSnake.get(length-1).setBm(bmTailDown);
        }
    }

    public void addPart(){

        partSnakey p = arrPartSnake.get(length-1);
        length +=1;
        if(p.getBm() == bmTailRight){
            arrPartSnake.add(new partSnakey(bmTailRight,p.getX()-gameView.sizeOfMap, p.getY()));
        }
        else if(p.getBm() == bmTailLeft){
            arrPartSnake.add(new partSnakey(bmTailLeft,p.getX()+gameView.sizeOfMap, p.getY()));
        }
        else if(p.getBm() == bmTailUp){
            arrPartSnake.add(new partSnakey(bmTailUp,p.getX(), p.getY()+gameView.sizeOfMap));
        }
        else if(p.getBm() == bmTailDown){
            arrPartSnake.add(new partSnakey(bmTailDown,p.getX(), p.getY() - gameView.sizeOfMap));
        }

    }

    public void draw(Canvas c){
        for(int x=0;x<length;x++){
            c.drawBitmap(arrPartSnake.get(x).getBm(),arrPartSnake.get(x).getX(),arrPartSnake.get(x).getY(),null);
        }
    }

    public Bitmap getBm() {
        return bm;
    }

    public void setBm(Bitmap bm) {
        this.bm = bm;
    }

    public Bitmap getBmHeadUp() {
        return bmHeadUp;
    }

    public void setBmHeadUp(Bitmap bmHeadUp) {
        this.bmHeadUp = bmHeadUp;
    }

    public Bitmap getBmHeadDown() {
        return bmHeadDown;
    }

    public void setBmHeadDown(Bitmap bmHeadDown) {
        this.bmHeadDown = bmHeadDown;
    }

    public Bitmap getBmHeadLeft() {
        return bmHeadLeft;
    }

    public void setBmHeadLeft(Bitmap bmHeadLeft) {
        this.bmHeadLeft = bmHeadLeft;
    }

    public Bitmap getBmHeadRight() {
        return bmHeadRight;
    }

    public void setBmHeadRight(Bitmap bmHeadRight) {
        this.bmHeadRight = bmHeadRight;
    }

    public Bitmap getBmBodyVert() {
        return bmBodyVert;
    }

    public void setBmBodyVert(Bitmap bmBodyVert) {
        this.bmBodyVert = bmBodyVert;
    }

    public Bitmap getBmBodyHori() {
        return bmBodyHori;
    }

    public void setBmBodyHori(Bitmap bmBodyHori) {
        this.bmBodyHori = bmBodyHori;
    }

    public Bitmap getBmBodyTopRight() {
        return bmBodyTopRight;
    }

    public void setBmBodyTopRight(Bitmap bmBodyTopRight) {
        this.bmBodyTopRight = bmBodyTopRight;
    }

    public Bitmap getBmBodyTopLeft() {
        return bmBodyTopLeft;
    }

    public void setBmBodyTopLeft(Bitmap bmBodyTopLeft) {
        this.bmBodyTopLeft = bmBodyTopLeft;
    }

    public Bitmap getBmBodyBottomRight() {
        return bmBodyBottomRight;
    }

    public void setBmBodyBottomRight(Bitmap bmBodyBottomRight) {
        this.bmBodyBottomRight = bmBodyBottomRight;
    }

    public Bitmap getBmBodyBottomLeft() {
        return bmBodyBottomLeft;
    }

    public void setBmBodyBottomLeft(Bitmap bmBodyBottomLeft) {
        this.bmBodyBottomLeft = bmBodyBottomLeft;
    }

    public Bitmap getBmTailRight() {
        return bmTailRight;
    }

    public void setBmTailRight(Bitmap bmTailRight) {
        this.bmTailRight = bmTailRight;
    }

    public Bitmap getBmTailLeft() {
        return bmTailLeft;
    }

    public void setBmTailLeft(Bitmap bmTailLeft) {
        this.bmTailLeft = bmTailLeft;
    }

    public Bitmap getBmTailUp() {
        return bmTailUp;
    }

    public void setBmTailUp(Bitmap bmTailUp) {
        this.bmTailUp = bmTailUp;
    }

    public Bitmap getBmTailDown() {
        return bmTailDown;
    }

    public void setBmTailDown(Bitmap bmTailDown) {
        this.bmTailDown = bmTailDown;
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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public ArrayList<partSnakey> getArrPartSnake() {
        return arrPartSnake;
    }

    public void setArrPartSnake(ArrayList<partSnakey> arrPartSnake) {
        this.arrPartSnake = arrPartSnake;
    }

    public boolean isMoveLeft() {
        return moveLeft;
    }

    public void setMoveLeft(boolean moveLeft) {
        s();
        this.moveLeft = moveLeft;
    }

    public boolean isMoveRight() {
        return moveRight;
    }

    public void setMoveRight(boolean moveRight) {
        s();
        this.moveRight = moveRight;
    }

    public boolean isMoveUp() {
        return moveUp;
    }

    public void setMoveUp(boolean moveUp) {
        s();
        this.moveUp = moveUp;
    }

    public boolean isMoveDown() {
        return moveDown;
    }

    public void setMoveDown(boolean moveDown) {
        s();
        this.moveDown = moveDown;
    }

    public void s(){
        moveLeft=false;
        moveRight=false;
        moveUp=false;
        moveDown=false;
    }
}
