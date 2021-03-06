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
    //int topBorder = getTopBorder(), bottomBorder = getBottomBorder();
    int triangleWidth;
    int numTri = 6;
    Canvas canvas;
    int count = 0;
    int totalSpikes = getHeight()/12;
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        sprite = new Sprite(getWidth()/2, getHeight()/2,
                getWidth()/2 + 300, getHeight()/2 + 300, -6, 5, Color.WHITE);
        sprite.setTopBorder((int)(getHeight() * .1));
        triangleWidth =  (getWidth()/numTri);
        trianglePath = new Path();
        trianglePath.moveTo(0, sprite.getTopBorder());
        trianglePath.lineTo(triangleWidth/2, sprite.getTopBorder() + 100);
        trianglePath.lineTo( triangleWidth, sprite.getTopBorder());
        trianglePath.close();
    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(sprite.getCount() < 10)
            sprite.setTopBorder((int)(getHeight()*.1));
        else if(sprite.getCount() < 20)
            sprite.setTopBorder((int)(getHeight()*.15));
        else if(sprite.getCount() < 30)
            sprite.setTopBorder((int)(getHeight() * .2));
        triangleWidth =  (getWidth()/numTri);
        trianglePath = new Path();
        trianglePath.moveTo(0, sprite.getTopBorder());
        trianglePath.lineTo(triangleWidth/2, sprite.getTopBorder() + 100);
        trianglePath.lineTo( triangleWidth, sprite.getTopBorder());
        trianglePath.close();
        Paint spikes = new Paint();
        spikes.setColor(Color.argb(255, 62, 92, 33));
        spikes.setStyle(STROKE.FILL);
        canvas.drawRect(0, 0, getWidth(), sprite.getTopBorder(), spikes);
        canvas.drawRect(0, getHeight()-sprite.getTopBorder(), getWidth(), getHeight(), spikes);
        canvas.save();
        for(int i=0;i<numTri;i++){
            canvas.drawPath(trianglePath, spikes);
            canvas.translate(180,0);
        }
        canvas.restore();
        canvas.save();
        canvas.rotate(180, getWidth()/2, getHeight()/2);
        for(int i = 0; i < numTri; i++){
            canvas.drawPath(trianglePath, spikes);
            canvas.translate(180, 0);
        }
        canvas.restore();

//            x += 200;
//        }
//        canvas.drawCircle(100, 100, 100, p);
        sprite.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.anotherpokemon));
        sprite.draw(canvas);
        sprite.update(canvas);
        invalidate();
    }

}
