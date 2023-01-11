package com.a.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MinApi23DemoActivity extends AppCompatActivity {
    CircleProgressView cp;
    TextView tv_progress;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_min_api23_demo);
        cp = findViewById(R.id.cp_progress);
        tv_progress = findViewById(R.id.tv_progress);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cp.setProgress(100,100000);
            }
        });


    }
}