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
        drawView = findViewById(R.id.drawView);
        if(drawView.sprite != null && drawView.sprite.getGameOver()){
            textView.setAlpha(255f);
        }
        System.out.println(test);
    }

    @Override
    protected void onPause() {
        super.onPause();
        drawView.sprite.setdY(0);
        drawView.sprite.setdX(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        drawView.sprite.setdX(-6);
        drawView.sprite.setdY(5);
    }

    public void moveUp(View view) {
        drawView.sprite.offsetTo(drawView.sprite.getLeft(), drawView.sprite.getTop() - 150);
    }

}