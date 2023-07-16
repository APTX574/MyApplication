package com.example.myapplication;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import androidx.annotation.ColorInt;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.example.myapplication.adapter.PageAdapter;
import com.example.myapplication.fragment.MainFrag;
import com.example.myapplication.fragment.SettingUserFrag;
import com.example.myapplication.fragment.UserFrag;
import com.example.myapplication.util.BarColor;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends BarColor implements View.OnClickListener {
    ViewPager viewPager;
    View bar;
    TabLayout tabLayout;
    Fragment[] fragments;
    PageAdapter pageAdapter;


    BroadcastReceiver msgReceiver;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        fragments = new Fragment[3];
        fragments[0] = new SettingUserFrag();
        fragments[1] = new MainFrag();

        fragments[2] = new UserFrag();

        viewPager = findViewById(R.id.viewPage);
//        bar = findViewById(R.id.bar);
        String[] title = new String[3];
        title[0] = getString(R.string.tab0);
        title[1] = getString(R.string.tab1);
        title[2] = getString(R.string.tab2);

        pageAdapter = new PageAdapter(getSupportFragmentManager(), fragments, title);
        viewPager.setAdapter(pageAdapter);
        viewPager.setCurrentItem(1);
        tabLayout = findViewById(R.id.main_tab);
        tabLayout.setupWithViewPager(viewPager);
        msgReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                View tag = ((MainFrag) fragments[1]).page;
                View title = tag.findViewById(R.id.r_l);
                String action = intent.getAction();
                if ("show add card".equals(action)) {
//                    extracted(title, 255,221);

                }
                if ("hide add card".equals(action)) {
//                    extracted(title, 221, 255);

                }
            }
        };
        IntentFilter intentFilter;
        intentFilter = new IntentFilter();
        intentFilter.addAction("show add card");
        intentFilter.addAction("hide add card");
        registerReceiver(msgReceiver, intentFilter);
//        bar.setOnClickListener(this);

    }

    private void extracted(View title, int from, int to) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(from, to);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(animation -> {
            int animatedValue = (int) valueAnimator.getAnimatedValue();
//            int i = (animatedValue+animatedValue*256+animatedValue*65536)/16777216;
            int i = Color.argb(255, animatedValue, animatedValue-10, animatedValue-7);
            System.out.println(i);
            tabLayout.setBackgroundColor(i);
//            bar.setBackgroundColor(i);
            title.setBackgroundColor(i);
        });

        valueAnimator.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(msgReceiver);
    }


    @Override
    public void onClick(View v) {
        tabLayout.setBackgroundColor(Color.argb(255, 255, 255, 255));
        System.out.println("color"+Color.parseColor("#F2F9FA"));
    }
}