package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.util.Util;

import java.util.Calendar;
import java.util.Random;

/**
 * @author aptx
 * @date 2022/12/03 17:09
 */
public class Login extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, View.OnClickListener {
    Button time;
    EditText name;
    EditText idCode;
    EditText phone;
    String time_str;
    SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.login);
        super.onCreate(savedInstanceState);
        sp = this.getSharedPreferences("user", MODE_PRIVATE);
        time = findViewById(R.id.time_b);
        time.setOnClickListener(this);
        name = findViewById(R.id.name);
        idCode = findViewById(R.id.id_code);
        phone = findViewById(R.id.phone);
        findViewById(R.id.btn).setOnClickListener(this);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        time_str = String.format("%d-%d-%d", year, month + 1, day);
        time.setText(time_str);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.time_b) {
            //获取实例，包含当前年月日
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog dialog = new DatePickerDialog(this, this,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MARCH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        }
        if (v.getId() == R.id.btn) {
            Editable text_name = name.getText();
            Editable text_id = idCode.getText();
            Editable text_phone = phone.getText();
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("username", text_name.toString());
            edit.putString("id", text_id.toString());
            edit.putString("phone", text_phone.toString());
            edit.putString("birthday", time_str);
            edit.putString("image", String.valueOf(new Random().nextInt(1000000000) / 10000000));
            edit.apply();
            Util.toast("注册成功",Login.this);
            setResult(888);
            this.finish();
        }
    }
}
