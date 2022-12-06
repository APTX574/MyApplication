package com.example.myapplication.fragment;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Outline;
import android.os.Bundle;
import android.view.*;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.example.myapplication.R;
import com.example.myapplication.util.DipUtils;

/**
 * @author aptx
 * @date 2022/12/03 02:15
 */
public class SettingUserFrag extends Fragment implements View.OnClickListener {
    Context context;
    SharedPreferences sp;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        sp = context.getSharedPreferences("user", Context.MODE_PRIVATE);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_layout, container, false);
        {
            CardView card0 = view.findViewById(R.id.setting_card_0);
            SettingHolder settingHolder = new SettingHolder(card0.findViewById(R.id.setting_text_0)
                    , card0.findViewById(R.id.setting_image_0), card0.findViewById(R.id.setting_et_0),
                    card0.findViewById(R.id.setting_linear_0), "https://sepolia.infura.io/v3/42e1865458574ae9b258fc5ac9ba2371",
                    R.drawable.eth0,
                    R.drawable.eth1, card0);
            card0.setTag(settingHolder);
            card0.setOnClickListener(this);
            CardView card1 = view.findViewById(R.id.setting_card_1);
            SettingHolder settingHolder1 = new SettingHolder(card1.findViewById(R.id.setting_text_1)
                    , card1.findViewById(R.id.setting_image_1), card1.findViewById(R.id.setting_et_1),
                    card1.findViewById(R.id.setting_linear_1), context.getResources().getString(R.string.hyaddress),
                    R.drawable.con0,
                    R.drawable.con1, card1);
            card1.setTag(settingHolder1);
            card1.setOnClickListener(this);

            CardView card2 = view.findViewById(R.id.setting_card_2);
            SettingHolder settingHolder2 = new SettingHolder(card2.findViewById(R.id.setting_text_2)
                    , card2.findViewById(R.id.setting_image_2), card2.findViewById(R.id.setting_et_2),
                    card2.findViewById(R.id.setting_linear_2), "0xbE447E5A634217ff1ed3284D11f49fEcd227d44e",
                    R.drawable.gy0,
                    R.drawable.gy1, card2);
            card2.setTag(settingHolder2);
            card2.setOnClickListener(this);
        }
        return view;
    }

    @Override
    public void onClick(View v) {

    }

    class SettingHolder implements View.OnTouchListener {
        TextView tv;
        ImageView im;
        EditText et;
        LinearLayout layout;
        int status = 0;
        int id = 0;
        String text_value;
        int img0;
        int img1;
        ValueAnimator animator = null;

        public SettingHolder(TextView tv, ImageView im, EditText et, LinearLayout layout, String text_value
                , int img0, int img1, View view) {
            this.img0 = img0;
            this.img1 = img1;
            this.tv = tv;
            this.im = im;
            this.et = et;
            this.text_value = text_value;
            this.layout = layout;
            view.setOnTouchListener(this);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int action = event.getAction();
            CardView c_v = (CardView) v;
            int l = DipUtils.dip2px(context, 4);
            ViewGroup.LayoutParams layoutParams = c_v.getLayoutParams();
            if (action == MotionEvent.ACTION_UP ||
                    action == MotionEvent.ACTION_OUTSIDE ||
                    action == MotionEvent.ACTION_CANCEL) {
                if (status == 0 && (animator == null || !animator.isRunning())
                        && (action != MotionEvent.ACTION_OUTSIDE && action != MotionEvent.ACTION_CANCEL)) {
                    animator = ValueAnimator.ofInt(0, l);
                    animator.setDuration(500);
                    animator.addUpdateListener(an -> {
                        int value = (int) an.getAnimatedValue();
                        layoutParams.height = layoutParams.height + value;
                        c_v.setLayoutParams(layoutParams);
                        tv.setTextColor(Color.argb(255 - (value * 255 / l), 0, 0, 0));
                        et.setTextColor(Color.argb((value * 255 / l), 0, 0, 0));
                        if (value == l / 2) {
                            im.setImageResource(img1);
                        }
                    });
                    animator.start();
                    status = 1;
                } else if (status == 1 && (animator == null || !animator.isRunning()) && (action == MotionEvent.ACTION_UP)) {
                    animator = ValueAnimator.ofInt(0, -l);
                    animator.setDuration(500);
                    animator.addUpdateListener(an -> {
                        int value = (int) an.getAnimatedValue();
                        layoutParams.height = layoutParams.height + value;
                        c_v.setLayoutParams(layoutParams);
                        et.setTextColor(Color.argb(255 - (-value * 255 / l), 0, 0, 0));
                        tv.setTextColor(Color.argb((-value * 255 / l), 0, 0, 0));
                        if (-value == l / 2) {
                            im.setImageResource(img0);
                        }
                    });
                    tv.setText(et.getText());
                    animator.start();
                    status = 0;
                }
                c_v.setBackgroundColor(Color.parseColor("#FFFFF5F8"));
                layout.setBackgroundColor(Color.parseColor("#FFFFF5F8"));
                c_v.setOutlineProvider(new ViewOutlineProvider() {
                    @Override
                    public void getOutline(View view, Outline outline) {
                        outline.setRoundRect(0, 0, view.getWidth(),
                                view.getHeight(), DipUtils.dip2px(context, 8));
                    }
                });
                c_v.setClipToOutline(true);
                if (!(action == MotionEvent.ACTION_OUTSIDE || action == MotionEvent.ACTION_CANCEL)) {
                    et.setText(sp.getString("url", text_value));
                    et.requestFocus();
                    c_v.performClick();
                }
                return true;
            } else if ((action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE)) {
                c_v.setBackgroundColor(Color.parseColor("#e6e6e6"));
                layout.setBackgroundColor(Color.parseColor("#e6e6e6"));

                return true;
            }
            return true;
        }
    }
}
