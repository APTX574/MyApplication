package com.example.myapplication.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.Login;
import com.example.myapplication.R;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author aptx
 * @date 2022/12/03 16:22
 */
public class ItemListAdapter extends BaseAdapter implements View.OnClickListener {
    List<Map<String, String>> itemList;
    Context context;
    Holder holder;

    public ItemListAdapter(List<Map<String, String>> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
            holder = new Holder();
            holder.tv = convertView.findViewById(R.id.item_text);
            holder.image = convertView.findViewById(R.id.item_image);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();

        }
        holder.tv.setText(itemList.get(position).get("text"));
        holder.image.setImageResource(Integer.parseInt(Objects.requireNonNull(itemList.get(position).get("image"))));
        convertView.setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        Holder tag = (Holder) v.getTag();
        CharSequence text = tag.tv.getText();
        if (text.toString().equals("退出登录") || text.toString().equals("点击登录")) {
            SharedPreferences user = context.getSharedPreferences("user", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = user.edit();
            edit.putString("username", "");
            edit.putString("phone", "");
            edit.putString("id", "");
            edit.putString("birthday", "");
            edit.apply();
            Intent intent = new Intent();
            intent.setClass(context, Login.class);
            context.startActivity(intent);
        }
        if (text.toString().equals("我的预约")) {

        }
    }
}

class Holder {
    public TextView tv;
    public ImageView image;
}

