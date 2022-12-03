package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;

import java.util.Date;

/**
 * @author aptx
 * @date 2022/09/15 00:09
 */
public class ReceiveActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout4);
        Bundle extras = getIntent().getExtras();
        String key = extras.getString("key");
        TextView viewById = findViewById(R.id.text1);
        viewById.setText(key);
        Intent intent = new Intent();
        Bundle bundle=new Bundle();
        bundle.putString("time",new Date().toString());
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK,intent);
//        finish();
    }
}
