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
            map.put("text", "这个是疫苗的介绍：九价HPV疫苗，是用于预防人乳头瘤病毒（HPV）感染引起的宫颈癌，可以预防6、 11、 16、 18、 31、 33、 45、 52和58型共9种病毒，涵盖90%以上的宫颈癌。 疫苗于2014年在美国获批上市。 2018年4月28日，国家药品监督管理局有条件批准用于预防宫颈癌的九价HPV疫苗在中国上市。 接种疫苗是一级预防，筛查是二级预防，二者同等重要。 因此，不可因接种疫苗，而放松宫颈癌筛查措施。 截至2022年8月，我国批准上市的九价HPV疫苗，其接种适应年龄范围是9至45岁适龄女性女性。");
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
