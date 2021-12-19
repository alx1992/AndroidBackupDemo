package com.lixin.lm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FourthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        Button button = findViewById(R.id.btn_next);
        button.setOnClickListener(v->{
            startActivity(new Intent(this,SecondActivity.class));
        });
    }
}