package com.littlesandbox.clicksandbox;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.littlesandbox.clicksandbox.AdListener.AchivementBannerListener;
import com.littlesandbox.clicksandbox.AdListener.BannerListener;
import com.littlesandbox.clicksandbox.Datas.Achivements;
import com.littlesandbox.clicksandbox.utils.EasyRawJson;
import com.zh.pocket.ads.banner.BannerAD;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class Achievement extends Activity
{
    //立即获得成就按钮
    protected View get_achievement;
    protected LinearLayout achive_banner_layout;
    protected TextView tv_achive_state1;
    private LinearLayout achive_roots;
    public BannerAD bannerAD;
    public BannerListener bannerListener;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);
        achive_banner_layout = findViewById(R.id.achive_banner);
        bannerAD = new BannerAD(this,Global.bannerid);
        bannerListener = new BannerListener(Achievement.this);
        bannerAD.setBannerADListener(bannerListener);

        bannerAD.loadAD(achive_banner_layout);
        setAchiveLayoutData();
        RadioGroup achive_filter_group = findViewById(R.id.achive_filter);
        achive_filter_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i)
            {
                Toast.makeText(Achievement.this,"功能未完成",Toast.LENGTH_LONG).show();
                /*RadioButton clicked = (RadioButton) radioGroup.getChildAt(i);
                clicked.setTextColor(Color.YELLOW);
                clicked.setBackgroundColor(Color.rgb(139,195,74));*/
            }
        });
    }
    public void change_view_mode(View v)
    {
        Toast.makeText(Achievement.this,"功能未完成",Toast.LENGTH_LONG).show();
    }
    private void setAchiveLayoutData()
    {
        achive_roots = findViewById(R.id.achives_root);
        View achive_release_hand_view = achive_roots.findViewById(R.id.include_1);
        ImageView achive_img1 = achive_release_hand_view.findViewById(R.id.achive_img);
        Button achive_now1 = achive_release_hand_view.findViewById(R.id.achive_now);

        View achive_auto_view = achive_roots.findViewById(R.id.include_2);
        TextView achive_title2 = achive_auto_view.findViewById(R.id.achive_title);
        TextView achive_how_to_get2 = achive_auto_view.findViewById(R.id.achive_how_to_get);
        TextView achive_progress2 = achive_auto_view.findViewById(R.id.achive_progess);
        Button achive_now2 = achive_auto_view.findViewById(R.id.achive_now);
        ImageView achive_img2 = achive_auto_view.findViewById(R.id.achive_img);

        View achive_hand_view = achive_roots.findViewById(R.id.include_3);
        TextView achive_title3 = achive_hand_view.findViewById(R.id.achive_title);
        TextView achive_how_to_get3 = achive_hand_view.findViewById(R.id.achive_how_to_get);
        TextView achive_progress3 = achive_hand_view.findViewById(R.id.achive_progess);
        Button achive_now3 = achive_hand_view.findViewById(R.id.achive_now);
        ImageView achive_img3 = achive_hand_view.findViewById(R.id.achive_img);

        View achive_da_zhuang = achive_roots.findViewById(R.id.include_4);
        TextView achive_title4 = achive_da_zhuang.findViewById(R.id.achive_title);
        TextView achive_how_to_get4 = achive_da_zhuang.findViewById(R.id.achive_how_to_get);
        TextView achive_progress4 = achive_da_zhuang.findViewById(R.id.achive_progess);
        Button achive_now4 = achive_da_zhuang.findViewById(R.id.achive_now);
        ImageView achive_img4 = achive_da_zhuang.findViewById(R.id.achive_img);

        achive_img1.setImageResource(R.drawable.unknown);

        achive_title2.setText("手废了没");
        achive_how_to_get2.setText("点击次数超300次获得");
        achive_progress2.setText("300");
        achive_img2.setImageResource(R.drawable.unknown);

        achive_title3.setText("人型打桩机");
        achive_how_to_get3.setText("点击次数超过1000次获得");
        achive_how_to_get3.setText("1000");
        achive_img3.setImageResource(R.drawable.unknown);

        achive_title4.setText("持之以恒");
        achive_how_to_get4.setText("获得所有句子后获得");
        achive_img4.setImageResource(R.drawable.unknown);
        
        //AchiveNowListener achiveNowListener = new AchiveNowListener();
        //2022-4-21 bug 闪退 在OnClick监听已经修复 未在banner监听测试
        //Log.d("img",achive_img1.toString());
        achive_now1.setOnClickListener(new AchiveNowListener(0,achive_img1));
        achive_now2.setOnClickListener(new AchiveNowListener(1,achive_img2));
        achive_now3.setOnClickListener(new AchiveNowListener(2,achive_img3));
        achive_now4.setOnClickListener(new AchiveNowListener(3,achive_img4));
        //String json = EasyRawJson.getJson(Achievement.this);
        Gson gson = new Gson();
    }
    class AchiveNowListener implements View.OnClickListener
    {
        int button_id;
        ImageView img;
        public AchiveNowListener(){}
        public AchiveNowListener(int id, ImageView p_img)
        {
            this.button_id = id;
            this.img = p_img;
        }
        @Override
        public void onClick(View view)
        {
            //这里就不较真了 反正调用广告次数过多出现问题
            if(button_id == 0)
            {
               // Log.d("img",img.toString());
                img.setImageResource(R.drawable.auto);
            }
            if(button_id == 1)
            {
                img.setImageResource(R.drawable.hand);
            }
            if(button_id == 2)
            {
                img.setImageResource(R.drawable.dzj);
            }
            if(button_id == 3)
            {
                img.setImageResource(R.drawable.czyh);
            }
            //bannerAD.loadAD(achive_banner_layout);
        }
    }
}