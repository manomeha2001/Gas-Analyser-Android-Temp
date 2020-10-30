package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Gears extends AppCompatActivity {

    private TextView analyze_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gears);


        ImageView img = (ImageView)findViewById(R.id.gear_image);
        Animation aniRotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        img.startAnimation(aniRotate);


        new Handler().postDelayed(new Runnable(){
            public void run(){
                Intent intent=new Intent(Gears.this,Extract.class);
                startActivity(intent);
            }
        }, 8000);



    }
}