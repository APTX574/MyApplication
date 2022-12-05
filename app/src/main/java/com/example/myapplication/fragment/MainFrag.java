package com.example.myapplication.fragment;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Outline;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.adapter.RecyclerAdapter;
import com.example.myapplication.util.DipUtils;
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
    float plusX = 0;
    float plusY = 0;
    float x1;
    public View page;

    CardView addCard;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_layout, container, false);
        page = view;
        System.out.println("asdasdasddddddddddddddddd");
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(i));
            map.put("title", "我是第" + i + "个疫苗");
            map.put("text", "截至时间：2022年12月5日 14:20\n总名额：12人");
            list.add(map);

        }
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(context, list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerAdapter);
        ImageView plus = view.findViewById(R.id.plus);
        addCard = view.findViewById(R.id.add_card);
        addCard.setBackgroundColor(Color.parseColor("#FFFFFF"));
        addCard.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(),
                        view.getHeight(), DipUtils.dip2px(context, 10));
            }
        });
        plus.setOnClickListener(this);
        plus.setTag(0);
        addCard.post(() -> {
            x1 = addCard.getX();
        });
        plus.post(() -> {
            plusX = plus.getX();
            plusY = plus.getY();
        });
        return view;
    }

    @Override
    public void onClick(View v1) {
        ImageView v=(ImageView) v1;
        if (v.getId() == R.id.plus && ((int) v.getTag()) == 0) {
            float x = 860;
            float y = 310;
            System.out.println(x1 + "aaaaaaaa");
            System.out.println(DipUtils.dip2px(context, 20));
            ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
            animator.setDuration(500);
            animator.addUpdateListener(animation -> {
                float value = (float) animation.getAnimatedValue();
                addCard.setX(x1 - (x1 - DipUtils.dip2px(context, 20)) * value);
                v.setX(plusX - (plusX - x) * value);
                v.setY(plusY - (plusY - y) * value);
                v.setRotation(405 * value);
                if (Math.abs((value-0.5))<0.1){
                    v.setImageResource(R.drawable.plus1);
                }
            });
            context.sendBroadcast(new Intent("show add card"));
            animator.start();
            v.setTag(1);
        } else if (v.getId() == R.id.plus && ((int) v.getTag()) == 1) {
            float x = v.getX();
            float y = v.getY();
            ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
            animator.setDuration(500);
            animator.addUpdateListener(animation -> {
                float value = (float) animation.getAnimatedValue();
                v.setX(x - (x - plusX) * value);
                v.setY(y - (y - plusY) * value);
                v.setRotation(-360 * value);
                if (Math.abs(value-0.5)<0.1){
                    v.setImageResource(R.drawable.plus);
                }
                addCard.setX(DipUtils.dip2px(context, 20) -
                        (DipUtils.dip2px(context, 20) - x1) * value);

            });
            context.sendBroadcast(new Intent("hide add card"));
            animator.start();
            v.setTag(0);
        }
    }

}
