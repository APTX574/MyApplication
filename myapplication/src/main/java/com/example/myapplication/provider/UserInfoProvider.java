package com.example.myapplication.provider;

import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.myapplication.MyApplication;

import java.util.Map;

/**
 * @author aptx
 * @date 2022/09/22 21:21
 */
public class UserInfoProvider extends ContentProvider {
    Editor edit;

    @Override
    public boolean onCreate() {
        MyApplication instance = MyApplication.getInstance();
        edit = instance.preferences.edit();
        edit.putString("key", "你好我是内容提供者内容");
        edit.apply();
        return true;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        edit.putString(uri.toString(), contentValues.toString());
        return uri;
    }


    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }


    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
