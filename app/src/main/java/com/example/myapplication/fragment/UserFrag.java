package com.example.myapplication.fragment;

import android.annotation.SuppressLint;
import android.content.*;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.myapplication.Login;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.receiver.MsgReceiver;
import com.example.myapplication.util.MyImageView;

import java.util.*;

/**
 * @author aptx
 * @date 2022/12/02 22:27
 */
public class UserFrag extends Fragment {
    Context context;
    List<Map<String, String>> itemList = null;
    SharedPreferences sp;
    TextView user_name;
    TextView user_phone;
    View view;
    BroadcastReceiver msgReceiver;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemList = new ArrayList<>();
        context = getActivity();
        Map<String, String> map = new HashMap<>();
        map.put("text", "我的预约");
        map.put("image", String.valueOf(R.drawable.fang));
        itemList.add(map);
        Map<String, String> map1 = new HashMap<>();
        map1.put("text", "点击登录");
        map1.put("image", String.valueOf(R.drawable.exit));
        itemList.add(map1);
        sp = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        msgReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                String action = intent.getAction();
                if ("flush".equals(action)) {
                    onResume();
                }
            }
        };
        IntentFilter intentFilter;
        intentFilter = new IntentFilter();
        intentFilter.addAction("flush");
        context.registerReceiver(msgReceiver, intentFilter);

    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //将xml文件转为为View对象
        if (!check_login()) {
            Intent intent = new Intent();
            intent.setClass(context, Login.class);
            startActivityForResult(intent, 888);
        }
        String username = sp.getString("username", "");
        String phone = sp.getString("phone", "");
        String image1 = sp.getString("image", "19212121");
        View view = inflater.inflate(R.layout.user_layout, container, false);
        MyImageView image = view.findViewById(R.id.image);

        ItemListAdapter itemListAdapter = new ItemListAdapter(itemList, context);
        image.setImageURL("https://avatars.githubusercontent.com/u/" + image1 + "?s=64&v=4");

        user_name = view.findViewById(R.id.user_name);
        user_phone = view.findViewById(R.id.user_phone);
        user_phone.setText("账号：" + phone);
        user_name.setText(username);
        Map<String, String> map = itemList.get(1);
        if (Objects.equals(username, "")) {
            map.put("text", "点击登录");
        } else {
            map.put("text", "退出登录");
        }
        ListView listView = view.findViewById(R.id.list);
        listView.setAdapter(itemListAdapter);
        this.view = view;
        return view;
    }

    private boolean check_login() {
        String userName = sp.getString("username", "");
        return !(Objects.equals(userName, ""));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onResume() {
        String username = sp.getString("username", "");
        String phone = sp.getString("phone", "");
        String image1 = sp.getString("image", "19212121");
        user_name = view.findViewById(R.id.user_name);
        user_phone = view.findViewById(R.id.user_phone);
        user_phone.setText("账号：" + phone);
        user_name.setText(username);
        Map<String, String> map = itemList.get(1);
        if (Objects.equals(username, "")) {
            map.put("text", "点击登录");
        } else {
            map.put("text", "退出登录");
        }
        MyImageView image = view.findViewById(R.id.image);
        image.setImageURL("https://avatars.githubusercontent.com/u/" + image1 + "?s=64&v=4");

        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        context.unregisterReceiver(msgReceiver);
    }
}
