package com.example.myapplication;

import android.annotation.SuppressLint;
import android.view.WindowManager;
import android.os.Bundle;
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
    TabLayout tabLayout;
    Fragment[] fragments;
    PageAdapter pageAdapter;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragments = new Fragment[3];
        fragments[0] = new SettingUserFrag();
        fragments[1] = new MainFrag();
        fragments[2] = new UserFrag();
        viewPager = findViewById(R.id.viewPage);
        String[] title = new String[3];
        title[0]=getString(R.string.tab0);
        title[1]=getString(R.string.tab1);
        title[2]=getString(R.string.tab2);
        pageAdapter = new PageAdapter(getSupportFragmentManager(), fragments,title);
        viewPager.setAdapter(pageAdapter);
        viewPager.setCurrentItem(1);
        tabLayout = findViewById(R.id.main_tab);
        tabLayout.setupWithViewPager(viewPager);
    }


}