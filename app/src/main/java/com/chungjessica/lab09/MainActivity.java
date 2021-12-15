package com.chungjessica.lab09;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DrawView drawView;
    TextView textView;
    boolean test = false;
    Sprite s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.gameOverText);
        drawView = findViewById(R.id.drawView);
        s = drawView.sprite;
        if(s != null && s.getGameOver()){
            textView.setAlpha(255f);
        }
        if(s == null)
            test = true;
        System.out.println(test);
    }

    public void moveUp(View view) {
        drawView.sprite.offsetTo(drawView.sprite.getLeft(), drawView.sprite.getTop() - 150);
    }


}