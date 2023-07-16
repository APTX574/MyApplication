package com.example.myapplication.adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Outline;
import android.view.*;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.util.DipUtils;
import com.example.myapplication.util.MyImageView;
import com.example.myapplication.util.Util;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.analysis.Value;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author aptx
 * @date 2022/12/04 22:10
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {
    List<Map<String, String>> list;
    Context context;
    View inflate;
    Button button;


    public RecyclerAdapter(Context context, List<Map<String, String>> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public Holder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        inflate = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
        Holder holder = new Holder(inflate);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Holder holder, int position) {
        Map<String, String> map = list.get(position);
        holder.tv1.setText(map.get("title"));
        holder.tv2.setText(map.get("text"));
        holder.price.setText(map.get("price"));
        holder.image.setImageURL(map.get("image"));
        holder.id = Integer.parseInt(Objects.requireNonNull(map.get("id")));

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnTouchListener, View.OnClickListener {
        public TextView tv1;
        public TextView tv2;
        public TextView price;
        public TextView num_v;
        public Button bts;
        public Button btp;
        public MyImageView image;
        public int id;
        public int status = 0;
        public Integer num = 0;
        ValueAnimator animator;
        ValueAnimator animator2;


        public Holder(@NonNull @NotNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.card_title);
            tv2 = itemView.findViewById(R.id.card_text);
            price = itemView.findViewById(R.id.card_price);
            btp = itemView.findViewById(R.id.card_btp);
            bts = itemView.findViewById(R.id.card_bts);
            num_v = itemView.findViewById(R.id.card_num);
            image = itemView.findViewById(R.id.image);
            bts.setOnClickListener(this);
            btp.setOnClickListener(this);
            itemView.setTag(this);
            itemView.setOnTouchListener(this);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            LinearLayout l_v = (LinearLayout) v;
            int action = event.getAction();
            System.out.println(action);
            int l = DipUtils.dip2px(context, 5);
            View c_v = l_v.findViewById(R.id.card_view);
            Holder tag1 = (Holder) v.getTag();
            ViewGroup.LayoutParams layoutParams = l_v.getLayoutParams();
            if (action == MotionEvent.ACTION_UP ||
                    action == MotionEvent.ACTION_OUTSIDE ||
                    action == MotionEvent.ACTION_CANCEL) {
                if (tag1.status == 0 && (animator == null || !animator.isRunning())
                        && (action != MotionEvent.ACTION_OUTSIDE && action != MotionEvent.ACTION_CANCEL)) {
                    animator = ValueAnimator.ofInt(0, l);
                    animator.setDuration(500);
                    animator.addUpdateListener(an -> {
                        int animatedValue = (int) an.getAnimatedValue();
                        layoutParams.height = layoutParams.height + animatedValue;
                        l_v.setLayoutParams(layoutParams);

                    });
                    animator.start();
                    tag1.status = 1;
                } else if (tag1.status == 1 && (animator == null || !animator.isRunning()) && (action == MotionEvent.ACTION_UP)) {
                    animator = ValueAnimator.ofInt(0, -l);
                    animator.setDuration(500);
                    animator.addUpdateListener(an -> {
                        int animatedValue = (int) an.getAnimatedValue();
                        layoutParams.height = layoutParams.height + animatedValue;
                        l_v.setLayoutParams(layoutParams);
                    });
                    animator.start();
                    tag1.status = 0;
                }
//                原先
                c_v.setBackgroundColor(Color.parseColor("#E1E6E6"));
                c_v.setOutlineProvider(new ViewOutlineProvider() {
                    @Override
                    public void getOutline(View view, Outline outline) {
                        outline.setRoundRect(0, 0, view.getWidth(),
                                view.getHeight(), DipUtils.dip2px(context, 8));
                    }
                });
                c_v.setClipToOutline(true);
                if (!(action == MotionEvent.ACTION_OUTSIDE || action == MotionEvent.ACTION_CANCEL)) {
                    c_v.performClick();
                }
                return true;
            } else if ((action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE)) {
//                按下去
                c_v.setBackgroundColor(Color.parseColor("#C4DAE6"));
                return true;
            }
            return true;
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.card_btp) {
                num++;
                num_v.setText(num.toString());
                float x = bts.getX();
                bts.setText("-");

                if (num == 1) {
                    bts.setX(btp.getX()-130);
                    System.out.println("11111111111");
                    ViewGroup.LayoutParams layoutParams = bts.getLayoutParams();
                    ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
                    animator.setDuration(500);
                    animator.addUpdateListener(an -> {
                        System.out.println("aaaaaa");
                        float animatedValue = (float) an.getAnimatedValue();
                        bts.setX(x - (x - 20) * animatedValue);
                        System.out.println(bts.getX());

                    });
                }
            }
            if (view.getId() == R.id.card_bts&&num!=0 ) {
                num--;
                num_v.setText(num.toString());
                float x = bts.getX();
                bts.setText("-");
                if (num == 0 ) {
                    num_v.setText("");
                    bts.setX(btp.getX());
                    bts.setText("+");
                    System.out.println("11111111111");
                    ViewGroup.LayoutParams layoutParams = bts.getLayoutParams();
                    ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
                    animator.setDuration(500);
                    animator.addUpdateListener(an -> {
                        System.out.println("aaaaaa");
                        float animatedValue = (float) an.getAnimatedValue();
                        bts.setX(x - (x - 20) * animatedValue);
                        System.out.println(bts.getX());

                    });
                }

            }
        }
    }
}


