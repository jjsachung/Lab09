package com.chungjessica.lab09;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class EndGameActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
    }
}
