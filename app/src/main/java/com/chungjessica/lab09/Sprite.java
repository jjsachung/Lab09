package com.chungjessica.lab09;

import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class Sprite extends RectF {
    private int dX, dY, color;
    private static final int BMP_COLUMNS = 3;
    private static final int BMP_ROWS = 6;
    private static final int down = 4, left = 5, right = 6, up = 7;
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
        if(bottom + dY < 0)
            offsetTo(left, canvas.getHeight());
        offset(dX, dY);
        if(animationDelay--<0){
            currentFrame = ++currentFrame % BMP_COLUMNS;
            animationDelay = 20;
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
            int srcY = getAnimationRow() * iconHeight;
            Rect src = new Rect(srcX, srcY, srcX + iconWidth, srcY + iconHeight);
        }
    }

    private int getAnimationRow() {
        if(Math.abs(dX) > Math.abs(dY)){
            if(Math.abs(dX)==dX)
                return right;
            else return left;
        }
        else if(Math.abs(dY) == dY)
            return down;
        else return up;
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
}
