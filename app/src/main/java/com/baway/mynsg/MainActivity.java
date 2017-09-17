package com.baway.mynsg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import activity.TwoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, TwoActivity.class);
                startActivity(intent);
            }
        };
        timer.schedule(task,3000);
    }
}
