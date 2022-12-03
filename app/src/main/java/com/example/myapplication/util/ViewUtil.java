package com.example.myapplication.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @author aptx
 * @date 2022/09/18 17:45
 */
public class ViewUtil {
    public static void hideOneInputMethod(Activity activity, View view) {
        InputMethodManager systemService = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        systemService.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
