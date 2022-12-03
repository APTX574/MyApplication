package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.util.BarColor;

/**
 * @author aptx
 * @date 2022/09/13 14:13
 */
public class Activity2 extends BarColor {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout2);
        TextView textView = findViewById(R.id.color);
        textView.setTextColor(Color.BLACK);
//        TextView color = findViewById(R.id.color);
//        TextView color2 = findViewById(R.id.color2);
//        RelativeLayout relative = findViewById(R.id.relative);
//        GridLayout grid = findViewById(R.id.grid);
//        ScrollView scale = findViewById(R.id.scale);
//        int height1 = color.getHeight();
//        int height2 = color2.getHeight();
//        int height3 = relative.getHeight();
//        int height5 = scale.getHeight();
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        this.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
//        int mPopupWindowWidth = (int) (displayMetrics.widthPixels / displayMetrics.density);
//        int mPopupWindowHeight = (int) (displayMetrics.heightPixels / displayMetrics.density);
//        grid.setMinimumHeight(mPopupWindowHeight-height1-height2-height3-height5);
    }

}
