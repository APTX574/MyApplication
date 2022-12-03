package com.example.myapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.example.myapplication.util.Util;

/**
 * @author aptx
 * @date 2022/12/04 00:00
 */
public class MsgReceiver extends BroadcastReceiver {
    Context context;


    public MsgReceiver(Context context) {
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        if ("flush".equals(action)) {
        }
    }
}
