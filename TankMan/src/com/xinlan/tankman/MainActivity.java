package com.xinlan.tankman;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.xinlan.tankman.main.MyGame;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends AndroidApplication {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//s
        this.initialize(new MyGame(), true);
    }
}//end class
