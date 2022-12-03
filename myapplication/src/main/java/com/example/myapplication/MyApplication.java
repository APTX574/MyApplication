package com.example.myapplication;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * @author aptx
 * @date 2022/09/22 21:44
 */
public class MyApplication extends Application {
    static MyApplication application;
    public SharedPreferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        preferences = getSharedPreferences("data", MODE_PRIVATE);

    }

    public static MyApplication getInstance() {
        return application;
    }

}
