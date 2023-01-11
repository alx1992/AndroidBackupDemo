package com.a.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;

/**
 * @author alx1992
 */
public class FourthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        findViewById(R.id.button3).setOnClickListener(listener);
    }

    View.OnClickListener listener = view -> ActivityUtils.startActivity(FourthActivity.this,MainActivity.class);
}