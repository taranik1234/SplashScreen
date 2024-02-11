package com.example.registrationsplashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.ImageView;
import java.lang.Thread;
public class MainActivity extends AppCompatActivity {
    Handler handler;
    Runnable runnable;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img=findViewById(R.id.img);
        img.animate().alpha(1.0f).setDuration(4000);


        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent dsp = new Intent( MainActivity.this,LoginActivity.class);
                startActivity(dsp);
                finish();
            }
        },4000);

    }}