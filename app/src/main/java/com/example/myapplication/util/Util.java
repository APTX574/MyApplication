package com.example.myapplication.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.example.myapplication.Login;

/**
 * @author aptx
 * @date 2022/12/03 20:10
 */
public class Util {
    public static void toast(String msg, Context context) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
            //放在UI线程弹Toast
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        });
    }
}
