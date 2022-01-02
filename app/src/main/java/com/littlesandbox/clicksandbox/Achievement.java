package com.littlesandbox.clicksandbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.littlesandbox.clicksandbox.AdListener.BannerListener;
import com.zh.pocket.ads.banner.BannerAD;

public class Achievement extends Activity
{
    //立即获得成就按钮
    protected View get_achievement;
    protected LinearLayout achive_banner_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);
        achive_banner_layout = findViewById(R.id.achive_banner);
    }
    //立刻获得手废了没成就
    public void hand(View v)
    {
        BannerAD bannerAD = new BannerAD(Achievement.this,"53655");
        bannerAD.setBannerADListener(new BannerListener());
        bannerAD.loadAD(achive_banner_layout);
    }
    public void get_achievement(View v)
    {
     /*   get_achievement = v;
        boolean could= TGSDK.couldShowAd(Global.scene_achievement);
        if(could)
        {
            TGSDK.showAd(this,Global.sceneid);
        }
        else
        {
            Toast.makeText(this,"failed",Toast.LENGTH_LONG).show();
        }*/
    }

   /* @Override
    public void onShowSuccess(String s, String s1)
    {
        ViewGroup p = (ViewGroup)get_achievement.getParent();
        TextView achievement_status =(TextView) p.getChildAt(2);
        achievement_status.setText("已获得");
    }

    @Override
    public void onShowFailed(String s, String s1, String s2)
    {
        Toast.makeText(this,"展示广告失败!",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onADClick(String s, String s1)
    {

    }

    @Override
    public void onADClose(String s, String s1, boolean b)
    {

    }*/
}