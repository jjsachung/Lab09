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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.gameOverText);
        drawView = findViewById(R.id.drawView);
        if(drawView.sprite != null && drawView.sprite.getGameOver()){
            textView.setAlpha(255f);
        }
        System.out.println(test);
    }

    public void moveUp(View view) {
        drawView.sprite.offsetTo(drawView.sprite.getLeft(), drawView.sprite.getTop() - 150);
    }




    public void restart(View view) {
        drawView.sprite.setAlpha(0);
        drawView.sprite.offsetTo(drawView.getWidth()/2, drawView.getHeight()/2);
        drawView.sprite.setdY(5);
        //drawView.sprite.setTopBorder((int)(drawView.getHeight() * .1));
    }
}