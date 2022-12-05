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
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.example.myapplication.adapter.PageAdapter;
import com.example.myapplication.fragment.MainFrag;
import com.example.myapplication.fragment.SettingUserFrag;
import com.example.myapplication.fragment.UserFrag;
import com.example.myapplication.util.BarColor;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends BarColor {
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
        bar = findViewById(R.id.bar);
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
                    extracted(title, 255,59);
                    tabLayout.setBackgroundColor(Color.parseColor("#DDDDDD"));
                    bar.setBackgroundColor(Color.parseColor("#DDDDDD"));
                    title.setBackgroundColor(Color.parseColor("#DDDDDD"));

                }
                if ("hide add card".equals(action)) {
                    extracted(title, 59, 255);
                    tabLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    bar.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    title.setBackgroundColor(Color.parseColor("#FFFFFF"));

                }
            }
        };
        IntentFilter intentFilter;
        intentFilter = new IntentFilter();
        intentFilter.addAction("show add card");
        intentFilter.addAction("hide add card");
        registerReceiver(msgReceiver, intentFilter);

    }

    private void extracted(View title, int from, int to) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(from, to);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(animation -> {
            int animatedValue = (int) valueAnimator.getAnimatedValue();
            int i = animatedValue+animatedValue*16*16+animatedValue*16*16*16*16;
            tabLayout.setBackgroundColor(i);
            bar.setBackgroundColor(i);
            title.setBackgroundColor(i);
        });
        System.out.println(title);
        System.out.println(bar);
        System.out.println(tabLayout);
        valueAnimator.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(msgReceiver);
    }

}