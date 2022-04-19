package com.littlesandbox.clicksandbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.littlesandbox.clicksandbox.AdListener.AchivementBannerListener;
import com.zh.pocket.ads.banner.BannerAD;

public class Achievement extends Activity
{
    //立即获得成就按钮
    protected View get_achievement;
    protected LinearLayout achive_banner_layout;
    protected TextView tv_achive_state1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);
        achive_banner_layout = findViewById(R.id.achive_banner);
        tv_achive_state1 = findViewById(R.id.achive_state1);
    }
    //立刻获得手废了没成就
    public void hand(View v)
    {
        BannerAD bannerAD = new BannerAD(Achievement.this,"53655");
        String achive1 = getResources().getString(R.string.achivement1);
        bannerAD.setBannerADListener(new AchivementBannerListener(this,achive1,tv_achive_state1));
        //bannerAD.destroy();
        bannerAD.loadAD(achive_banner_layout);
    }
    public void get_achievement(View v)
    {

    }
}