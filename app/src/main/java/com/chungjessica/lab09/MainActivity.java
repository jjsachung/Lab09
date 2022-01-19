package com.chungjessica.lab09;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DrawView drawView;
    TextView textView;
    boolean test = false;
    int clickCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawView = findViewById(R.id.drawView);
        if(drawView.sprite != null && drawView.sprite.getGameOver()){
            textView.setAlpha(255f);
        }
        System.out.println(test);
    }

    public void moveUp(View view) {
        clickCount = drawView.sprite.getCount();
        if(clickCount == 0) {
            drawView.sprite.setdX(-6);
            drawView.sprite.setdY(5);
        }
        drawView.sprite.offsetTo(drawView.sprite.getLeft(), drawView.sprite.getTop() - 150);
        //clickCount++;
    }

}