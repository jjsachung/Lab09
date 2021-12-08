package com.chungjessica.lab09;

import static android.graphics.Paint.Style.STROKE;

import android.content.Context;
//import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawView extends View {
    Sprite sprite;
    Path trianglePath;
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        sprite = new Sprite(getWidth()/2, getHeight()/2,
                getWidth()/2 + 300, getHeight()/2 + 300, -2, 1, Color.WHITE);
        trianglePath = new Path();
        trianglePath.moveTo(0, 150);
        trianglePath.lineTo(90, 250);
        trianglePath.lineTo( 180, 150);
        trianglePath.close();
    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint spikes = new Paint();
        spikes.setColor(Color.argb(255, 62, 92, 33));
        spikes.setStyle(STROKE.FILL);
        canvas.drawRect(0, 0, getWidth(), 150, spikes);
        canvas.drawRect(0, 1450, getWidth(), getHeight(), spikes);
        canvas.save();
        for(int i=0;i<getWidth()/180;i++){
            canvas.drawPath(trianglePath, spikes);
            canvas.translate(180,0);
        }
        canvas.restore();
        canvas.rotate(180, 90, 800);
        for(int i = 0; i < getWidth()/180; i++){
            canvas.drawPath(trianglePath, spikes);
            canvas.translate(-180, 0);
        }
        canvas.restore();

//            x += 200;
//        }
//        canvas.drawCircle(100, 100, 100, p);
//        sprite.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.anotherpokemon));
//        sprite.draw(canvas);
//        sprite.update(canvas);
//        invalidate();
    }
}
