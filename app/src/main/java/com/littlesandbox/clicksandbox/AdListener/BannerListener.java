package com.littlesandbox.clicksandbox.AdListener;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.littlesandbox.clicksandbox.Datas.Achivements;
import com.littlesandbox.clicksandbox.R;
import com.littlesandbox.clicksandbox.utils.ftz;
import com.zh.pocket.ads.banner.BannerADListener;
import com.zh.pocket.http.bean.ADError;

import java.util.Iterator;

public class BannerListener implements BannerADListener
{
    public String tag="MyBannerListener";
    public Context ctx;
    public String achive1;
    public TextView tv_achive_state1;
    public BannerListener(Context ctx)
    {
        this.ctx = ctx;
    }
    public BannerListener(Context ctx, String Achive1, TextView tv_achive_state1)
    {
        this.ctx = ctx;
        achive1 = Achive1;
        this.tv_achive_state1 = tv_achive_state1;
    }
    @Override
    public void onSuccess()
    {
        Log.d(tag,"Success");
        Toast.makeText(ctx,"success",Toast.LENGTH_LONG).show();
        //检测是否已存在元素避免重复添加
        if(Achivements.collected_ments.size()>0)
        {
            //迭代检测
            Iterator iter = Achivements.collected_ments.iterator();
            while(iter.hasNext())
            {
                String value = (String)iter.next();
                if(value.equals(achive1))
                {
                    Toast.makeText(ctx,"错误，检测到此成就已获得！不需要重复添加，breakIt!",Toast.LENGTH_LONG).show();
                    break;
                }
            }
        }
        Achivements.collected_ments.add(achive1);
        tv_achive_state1.setText(ctx.getString(R.string.yes_get));
        tv_achive_state1.setTextColor(Color.RED);
        ftz.send_Notification(ctx);
    }

    @Override
    public void onFailed(ADError adError) {
        Log.e("ADError", adError.getMessage());
        Log.e("ADError", String.valueOf(adError.getCode()));
        Toast.makeText(ctx,"Ad failed",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onADExposure()
    {
        Log.d(tag,"adExposure");
    }

    @Override
    public void onADClicked()
    {
        Log.d(tag,"adClick");
    }

    @Override
    public void onADClosed()
    {
        Log.d(tag,"adClosed");
    }
}
