package com.a.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;

/**
 * @author alx1992
 */
public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        findViewById(R.id.button2).setOnClickListener(listener);
    }

    View.OnClickListener listener = view -> ActivityUtils.startActivity(ThirdActivity.this,FourthActivity.class);
}