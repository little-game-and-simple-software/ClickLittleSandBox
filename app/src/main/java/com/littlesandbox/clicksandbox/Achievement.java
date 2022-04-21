package com.littlesandbox.clicksandbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.littlesandbox.clicksandbox.AdListener.AchivementBannerListener;
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
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);
        achive_banner_layout = findViewById(R.id.achive_banner);
        //tv_achive_state1 = findViewById(R.id.achive_state1);
        setAchiveLayoutData();
    }
    private void setAchiveLayoutData()
    {
        achive_roots = findViewById(R.id.achives_root);
        View achive_release_hand_view = achive_roots.findViewById(R.id.include_1);
        ImageView achive_img1 = achive_release_hand_view.findViewById(R.id.achive_img);

        View achive_auto_view = achive_roots.findViewById(R.id.include_2);
        TextView achive_title2 = achive_auto_view.findViewById(R.id.achive_title);
        TextView achive_how_to_get2 = achive_auto_view.findViewById(R.id.achive_how_to_get);
        TextView achive_progress2 = achive_auto_view.findViewById(R.id.achive_progess);
        ImageView achive_img2 = achive_auto_view.findViewById(R.id.achive_img);

        View achive_hand_view = achive_roots.findViewById(R.id.include_3);
        TextView achive_title3 = achive_hand_view.findViewById(R.id.achive_title);
        TextView achive_how_to_get3 = achive_hand_view.findViewById(R.id.achive_how_to_get);
        TextView achive_progress3 = achive_hand_view.findViewById(R.id.achive_progess);
        ImageView achive_img3 = achive_hand_view.findViewById(R.id.achive_img);

        View achive_da_zhuang = achive_roots.findViewById(R.id.include_4);
        TextView achive_title4 = achive_da_zhuang.findViewById(R.id.achive_title);
        TextView achive_how_to_get4 = achive_da_zhuang.findViewById(R.id.achive_how_to_get);
        TextView achive_progress4 = achive_da_zhuang.findViewById(R.id.achive_progess);
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
        //String json = EasyRawJson.getJson(Achievement.this);
        Gson gson = new Gson();
    }
    //立刻获得成就
    public void hand(View v)
    {
        BannerAD bannerAD = new BannerAD(Achievement.this,"53655");
        String achive1 = getResources().getString(R.string.achivement1);
        //bannerAD.setBannerADListener(new AchivementBannerListener(this,achive1,tv_achive_state1));
        //bannerAD.destroy();
        //bannerAD.loadAD(achive_banner_layout);
    }
    public void get_achievement(View v)
    {

    }
}