package com.example.myapplication.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.example.myapplication.Login;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
    public  static BigInteger timeUtil(String s){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        java.util.Date date = null;
        try {
            date = df.parse(s);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        long timestamp = cal.getTimeInMillis();
        return BigInteger.valueOf(timestamp / 1000);
    }
}
