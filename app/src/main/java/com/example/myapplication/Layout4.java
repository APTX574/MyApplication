package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.util.ViewUtil;

/**
 * @author aptx
 * @date 2022/09/18 13:21
 */
public class Layout4 extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, View.OnFocusChangeListener, TextWatcher {
    View viewById;
    int i = 0;
    String[] sexes = new String[]{
            "男的", "女的", "不知道性别"
    };
    private TextView textView;
    private TextView textView5;
    private EditText editText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.layout5);
        super.onCreate(savedInstanceState);
        viewById = findViewById(R.id.view);
        viewById.setBackgroundResource(R.drawable.yuan);
        Button button = findViewById(R.id.changeShape);
        button.setOnClickListener(this);
        RadioGroup radio = findViewById(R.id.radio);
        radio.setOnCheckedChangeListener(this);
        textView = findViewById(R.id.sex);
        textView5 = findViewById(R.id.test5);
        editText = findViewById(R.id.edit_text);
        editText.setOnFocusChangeListener(this);
        editText.addTextChangedListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.changeShape -> {
                if (i == 0) {
                    viewById.setBackgroundResource(R.drawable.fang);
                    i = 1;
                } else {
                    viewById.setBackgroundResource(R.drawable.yuan);
                    i = 0;
                }
            }
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("hello word");
        builder.setMessage("114514");
        builder.setPositiveButton("114514", (dialogInterface, i) -> {
            textView5.setText("114514");

        });
        builder.setNegativeButton("1919810", (dialogInterface, i) -> {
            textView5.setText("1919810");

        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id) {
        int index = switch (id) {
            case R.id.man -> 0;
            case R.id.woman -> 1;
            case R.id.unknown -> 2;
            default -> 2;
        };
        textView.setText(String.format("你竟然是一个%s", sexes[index]));
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (!b) {
            Toast.makeText(this, "失去焦点", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "获得焦点", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.toString().length() == 11) {
            ViewUtil.hideOneInputMethod(this, editText);
        }
    }
}
