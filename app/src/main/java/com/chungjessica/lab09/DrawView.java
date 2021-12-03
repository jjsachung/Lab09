package com.chungjessica.lab09;

import android.content.Context;
//import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawView extends View {
    Sprite sprite;

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        sprite = new Sprite(getWidth()/2, getHeight()/2,
                getWidth()/2 + 300, getHeight()/2 + 300, -2, 1, Color.WHITE);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Paint p = new Paint();
//        p.setColor(Color.MAGENTA);
//        canvas.drawCircle(100, 100, 100, p);
        sprite.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.anotherpokemon));
        sprite.draw(canvas);
        sprite.update(canvas);
        invalidate();
    }
}
