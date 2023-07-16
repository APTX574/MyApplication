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
        System.out.println("asdasdasF2F9FAF2F9FAF2F9FA");
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        List<Map<String, String>> list = new ArrayList<>();
        String[] title={"酸辣土豆丝","水蒸蛋","麻婆豆腐","辣子鸡","豆角炝茄子","招牌手撕鸡","特色小炒肉","香辣啤酒鸭","玉米排骨汤","米饭"};
        String[] text={"酸辣土豆丝，是用土豆、辣椒、白醋，葱，姜等制作而成的川菜菜肴，色泽光亮，酸辣可口。",
                "水蒸蛋是传统美食之一，是一道老少皆宜的家常食谱，鲜嫩爽滑、色泽光鲜、营养丰富。",
                "麻婆豆腐是四川地区传统名菜之一，将川菜麻辣味型的特点展现的淋漓尽致。",
                "辣子鸡是一道经典的中国家常菜，以整鸡为主料，加上葱、干辣椒、花椒、盐、胡椒、味精等多种材料精制而成。",
                "豆角炝茄子是一道家常炒菜，茄子和豆角两者搭配富有营养，且口感香辣爽口。",
                "手撕鸡是一道客家传统美食。金黄的鸡皮上晶莹而散发甘香，肉质细腻，油脂适中，鲜美含汁，嚼之既不油腻也不柴硬。",
                "农家小炒肉，也就是青辣椒炒肉，是一道以青椒、猪肉味主要原料，炒制而成的美味家常菜。",
                "香辣啤酒鸭是一道美食，主要材料有鸭、啤酒、青红椒，辅料有姜，大蒜，干红椒，八角，花椒等。",
                "玉米排骨汤是一道食补汤品，玉米可降低血液胆固醇浓度并防止其沉积于血管壁，促进人们对维生素和钙的吸收。",
                "米饭，是中国人日常饮食中的主角之一，主食之一，主要成分为淀粉。"};
        String[] price={"￥5.00","￥6.00","￥3.00","￥15.00","￥5.00","￥16.00","￥15.00","￥12.00","￥10.00","￥1.00"};
        String[] image={"https://img0.baidu.com/it/u=844385790,3799510220&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
                "https://img1.baidu.com/it/u=2911129865,424077321&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
                "https://img1.baidu.com/it/u=1356538101,26855475&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimage109.360doc.com%2FDownloadImg%2F2017%2F12%2F2901%2F120383182_8_20171229011256181.jpg&refer=http%3A%2F%2Fimage109.360doc.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1692066912&t=99a435dff0fb1f7b0a81ac5f40806ec6",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fci.xiaohongshu.com%2F7ed5e09c-e6a5-4a6a-d8c7-4ba4cd7bc9e4%3FimageView2%2F2%2Fw%2F1080%2Fformat%2Fjpg&refer=http%3A%2F%2Fci.xiaohongshu.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1692067005&t=3a657a81b58548d0c86a0012116cb1a9",
                "https://img1.baidu.com/it/u=1870440119,2316541974&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
                "https://img1.baidu.com/it/u=1852970580,3349739581&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fss2.meipian.me%2Fusers%2F81200151%2F59a8306ae73401852370540e43b9615d.jpg%3Fmeipian-raw%2Fbucket%2Fivwen%2Fkey%2FdXNlcnMvODEyMDAxNTEvNTlhODMwNmFlNzM0MDE4NTIzNzA1NDBlNDNiOTYxNWQuanBn%2Fsign%2F4b4115ac4f510d2de2a9c037c8259c05.jpg&refer=http%3A%2F%2Fss2.meipian.me&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1692067244&t=72d3468fdcbee99c08c4be880250baa3",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fci.xiaohongshu.com%2F0afcde3d-d51f-fdb9-a69e-c2545c61779e%3FimageView2%2F2%2Fw%2F1080%2Fformat%2Fjpg&refer=http%3A%2F%2Fci.xiaohongshu.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1692067334&t=6ef3b3db71dd26621194b9ca937c9403",
                "https://preview.qiantucdn.com/dazhi/97/46/08/52g58PICUYY7CW85bWQ8w_PIC2018.jpg%21w1024_new_0"};
        for (int i = 0; i < 10; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(i));
            map.put("title", title[i]);
            map.put("text", text[i]);
            map.put("image", image[i]);
            map.put("price", price[i]);
            list.add(map);

        }
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(context, list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerAdapter);
        ImageView plus = view.findViewById(R.id.plus);
        addCard = view.findViewById(R.id.add_card);
        addCard.setBackgroundColor(Color.parseColor("#FFF5F0"));
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
            float y = 295;
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
