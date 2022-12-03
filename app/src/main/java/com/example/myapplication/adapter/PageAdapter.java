package com.example.myapplication.adapter;

import android.content.res.Resources;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.myapplication.R;

import static android.provider.Settings.System.getString;


/**
 * @author aptx
 * @date 2022/12/03 01:59
 */
public class PageAdapter extends FragmentPagerAdapter {
    Fragment[] fragments;
    String[] title;

    public PageAdapter(@NonNull FragmentManager fm, Fragment[] fragments,String[] title) {
        super(fm);
        this.fragments = fragments;
        this.title = title;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return this.fragments.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
