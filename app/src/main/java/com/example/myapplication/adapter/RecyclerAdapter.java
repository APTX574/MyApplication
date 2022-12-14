package com.example.myapplication.adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Outline;
import android.view.*;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.util.DipUtils;
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
        holder.id = Integer.parseInt(Objects.requireNonNull(map.get("id")));

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnTouchListener {
        public TextView tv1;
        public TextView tv2;
        public int id;
        public int status = 0;
        ValueAnimator animator;


        public Holder(@NonNull @NotNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.card_title);
            tv2 = itemView.findViewById(R.id.card_text);
            itemView.setTag(this);
            itemView.setOnTouchListener(this);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            LinearLayout l_v = (LinearLayout) v;
            int action = event.getAction();
            System.out.println(action);
            int l = DipUtils.dip2px(context, 14);
            View c_v = l_v.findViewById(R.id.card_view);
            Holder tag1 = (Holder) v.getTag();
            ViewGroup.LayoutParams layoutParams = l_v.getLayoutParams();
            if (action == MotionEvent.ACTION_UP ||
                    action == MotionEvent.ACTION_OUTSIDE ||
                    action == MotionEvent.ACTION_CANCEL) {
                if (tag1.status == 0 && (animator == null || !animator.isRunning())
                &&(action != MotionEvent.ACTION_OUTSIDE && action != MotionEvent.ACTION_CANCEL)) {
                    animator = ValueAnimator.ofInt(0, l);
                    animator.setDuration(500);
                    animator.addUpdateListener(an -> {
                        int animatedValue = (int) an.getAnimatedValue();
                        layoutParams.height = layoutParams.height + animatedValue;
                        l_v.setLayoutParams(layoutParams);

                    });
                    animator.start();
                    tag1.status = 1;
                } else if (tag1.status==1&&(animator == null || !animator.isRunning())&&(action==MotionEvent.ACTION_UP)) {
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
                c_v.setBackgroundColor(Color.parseColor("#FFFFF5F8"));
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
                c_v.setBackgroundColor(Color.parseColor("#e6e6e6"));
                return true;
            }
            return true;
        }
    }
}


