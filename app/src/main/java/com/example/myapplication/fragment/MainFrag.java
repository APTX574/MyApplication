package com.example.myapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.adapter.RecyclerAdapter;
import com.example.myapplication.util.Util;
import com.example.myapplication.util.Web3Util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author aptx
 * @date 2022/12/03 02:08
 */
public class MainFrag extends Fragment implements View.OnClickListener {
    Context context;
    Button button;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_layout, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(i));
            map.put("title", "我是第" + i+"个疫苗");
            map.put("text", "截至时间：2022年12月5日 14:20\n总名额：12人");
            list.add(map);

        }
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(context, list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerAdapter);

        //        button = view.findViewById(R.id.main_btn);
//        button.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
//        if (v.getId() == R.id.main_btn) {
////            BigInteger concent = Web3Util.addUser(context,"1212","1212");
//            String concent = Web3Util.getUser(context,"1212");
//            Util.toast(String.valueOf(concent), context);
//
//        }
    }

}
