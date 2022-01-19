package com.chungjessica.lab09;

import static android.graphics.Paint.Style.STROKE;

import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Sprite extends RectF {
    private int dX, dY, color, count = 0, topBorder, alpha = 255;
    private static final int BMP_COLUMNS = 3;
    private static final int BMP_ROWS = 8;
    private static final int DOWN = 4, LEFT = 5, RIGHT = 6, UP = 7;
    private boolean gameOver = false;
    private Bitmap bitmap;
    Path[] spikesRight = new Path[12];
    Path[] spikesLeft = new Path[12];
    ArrayList<Integer> currentSpike= new ArrayList<Integer>();
    private int currentFrame = 0, iconWidth, iconHeight, animationDelay = 20, numSpikes = 0;
    public Sprite(float left, float top, float right, float bottom, int dX, int dY, int color) {
        super(left, top, right, bottom);
        this.dX = dX;
        this.dY = dY;
        this.color = color;

    }

    public Sprite(float left, float top, float right, float bottom) {
        this(left, top, right, bottom, 1, 1, Color.MAGENTA);
    }

    public Sprite(int dX, int dY, int color) {
        this(1, 1, 110, 110, dX, dY, color);
    }

    public Sprite() {
        this(2, 3, Color.GREEN);
    }

    public void update(Canvas canvas){
        if(left + dX < 0 || right + dX > canvas.getWidth()) {
            currentSpike.removeAll(currentSpike);
            dX *= -1;
            count++;
            numSpikes = ((int) (Math.random() *4) + 1);
            for (int i = 0; i < numSpikes; i++) {
                currentSpike.add((int) (Math.random() * 7) + 3);
            }
        }
        if(top + dY > canvas.getHeight())
            offsetTo(left, -height());
        if(bottom + dY < 0)
            offsetTo(left, canvas.getHeight());
        offset(dX, dY);
        if(animationDelay--<0){
            currentFrame = ++currentFrame % BMP_COLUMNS;
            animationDelay = 20;
        }
        if(top + dY < topBorder || bottom + dY > canvas.getHeight() - topBorder - 100){
            offsetTo(canvas.getWidth()/2 - iconWidth, canvas.getHeight()/2 - iconHeight);
            count = 0;
            dY = 0;
            dX = 0;
        }
    }
    int score = 0;
    public void draw(Canvas canvas){
        if(spikesRight[0]==null) {
            for (int i = 0; i < 12; i++) {
                Path spike = new Path();
                spike.moveTo(canvas.getWidth(), i * (canvas.getHeight() / 12));
                spike.lineTo(canvas.getWidth() - 100, (i * (canvas.getHeight() / 12)) + canvas.getWidth() / 12);
                spike.lineTo(canvas.getWidth(), (i * (canvas.getHeight() / 12)) + canvas.getWidth() / 6);
                spikesRight[i] = spike;
            }
        }
        if(spikesLeft[0]==null) {
            for (int i = 0; i < 12; i++) {
                Path spike = new Path();
                spike.moveTo(0, i * (canvas.getHeight() / 12));
                spike.lineTo(100, (i * (canvas.getHeight() / 12)) + canvas.getWidth() / 12);
                spike.lineTo(0, (i * (canvas.getHeight() / 12)) + canvas.getWidth() / 6);
                spikesLeft[i] = spike;
            }
        }
//        Paint paint = new Paint();
//        paint.setColor(color);
//        canvas.drawCircle(centerX(), centerY(), width()/2, paint);
        if(bitmap == null){
            Paint paint = new Paint();
            paint.setColor(color);
            canvas.drawCircle(centerX(), centerY(), width() / 2, paint);
        }
        else{
            iconWidth = bitmap.getWidth() / BMP_COLUMNS / 4;
            iconHeight = bitmap.getHeight() / BMP_ROWS;
            int srcX = currentFrame * iconWidth;
            int srcY = getAnimationRow() * iconHeight + 5;
            Rect src = new Rect(srcX, srcY, srcX + iconWidth, srcY + iconHeight);
            canvas.drawBitmap(bitmap, src, this, null);
        }
        //i didn't test this new code


//        ArrayList<Integer> currentSpike = new ArrayList<Integer>();
        Paint spikes = new Paint();
        spikes.setColor(Color.argb(255, 62, 92, 33));
        spikes.setStyle(STROKE.FILL);
        if(count > score) {


            System.out.println("numSpikes: " + numSpikes);
//            for (int i = 0; i < numSpikes; i++) {

//                System.out.println("currentSpike: " + currentSpike.get(i));
//            }

            score++;
        }
        if(count % 2 == 1) {
            for (int i = 0; i < currentSpike.size(); i++) {
                canvas.drawPath(spikesRight[currentSpike.get(i)], spikes);
            }
        }
        else{
            for(int i = 0; i < currentSpike.size(); i++){
                canvas.drawPath(spikesLeft[currentSpike.get(i)], spikes);
            }
        }
    }

    private int getAnimationRow() {
        if(Math.abs(dX) > Math.abs(dY)){
            if(Math.abs(dX)==dX)
                return RIGHT;
            else return LEFT;
        }
        else if(Math.abs(dY) == dY)
            return DOWN;
        else return UP;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public boolean getGameOver(){
        return gameOver;
    }

    public int getTopBorder() {
        return topBorder;
    }

    public void setTopBorder(int topBorder) {
        this.topBorder = topBorder;
    }

    public int getCount() {
        return count;
    }

    public int getdX() {
        return dX;
    }

    public void setdX(int dX) {
        this.dX = dX;
    }

    public int getdY() {
        return dY;
    }

    public void setdY(int dY) {
        this.dY = dY;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public float getLeft() {
        return left;
    }

    public float getTop(){
        return top;
    }
}
