package com.lixin.lm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Button button = findViewById(R.id.btn_next);
        button.setOnClickListener(v->{
            Intent intent = new Intent(this, FourthActivity.class);
            startActivity(intent);
        });
    }
}