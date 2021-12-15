package com.chungjessica.lab09;

import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.widget.TextView;

public class Sprite extends RectF {
    private int dX, dY, color, count = 0, topBorder;
    private static final int BMP_COLUMNS = 3;
    private static final int BMP_ROWS = 8;
    private static final int DOWN = 4, LEFT = 5, RIGHT = 6, UP = 7;
    private boolean gameOver = false;
    private Bitmap bitmap;
    private int currentFrame = 0, iconWidth, iconHeight, animationDelay = 20;
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
            dX *= -1;
            count++;
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
        if(top + dY < topBorder){
            Paint paint = new Paint();
            paint.setColor(Color.argb(255, 242, 207, 78));
            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);
            top = topBorder - 5;
            dY = 0;
            gameOver = true;
        }
        if(bottom + dY > canvas.getHeight() - topBorder - 100){
            Paint paint = new Paint();
            paint.setColor(Color.argb(255, 242, 207, 78));
            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);
            bottom = canvas.getHeight() - topBorder + 5;
            dY = 0;
            gameOver = true;
        }
    }

    public void draw(Canvas canvas){
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
