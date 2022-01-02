package com.littlesandbox.clicksandbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

//import com.soulgame.sgsdk.tgsdklib.TGSDK;
//import com.soulgame.sgsdk.tgsdklib.ad.ITGADListener;

public class Achievement extends Activity //implements ITGADListener
{
    //立即获得成就按钮
    protected View get_achievement;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);
        //TGSDK.setADListener(this);
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