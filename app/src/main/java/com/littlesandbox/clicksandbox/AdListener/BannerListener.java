package com.littlesandbox.clicksandbox.AdListener;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.littlesandbox.clicksandbox.R;
import com.zh.pocket.ads.banner.BannerADListener;
import com.zh.pocket.http.bean.ADError;
//常见复用类
public class BannerListener implements BannerADListener
{
    private String tag;
    private String caller;//调用者
    public int buttonid;
    public ImageView achive_img;
    public Context ctx;
    /**
     * @param p_caller 调用者
     * */
    public BannerListener(Class p_caller)
    {
        tag = this.getClass().toString();
        caller = p_caller.toString();
    }
    public BannerListener()
    {

    }
    //第几个按钮
    public BannerListener(Context ctx)
    {
        this.ctx = ctx;
    }
    @Override
    public void onSuccess() {
        Log.d(tag,"Banner广告成功播放");
        if(buttonid == 0)
        {
            achive_img.setImageResource(R.drawable.auto);
        }
        if(buttonid == 1)
        {
            achive_img.setImageResource(R.drawable.hand);
        }
        if(buttonid == 2)
        {
            achive_img.setImageResource(R.drawable.dzj);
        }
        if(buttonid == 3)
        {
            achive_img.setImageResource(R.drawable.czyh);
        }
    }

    @Override
    public void onFailed(ADError adError)
    {
        Log.e(tag,"广告播放失败");
        Log.e(tag,"调用者" + caller);
        Log.e(tag,String.valueOf(adError.getCode()));
        Log.e(tag,adError.getMessage());
        Toast.makeText(ctx,"广告加载失败，请打开网络",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onADExposure()
    {
        Log.d(tag,"广告被曝光");
    }

    @Override
    public void onADClicked()
    {
        Log.d(tag,"广告被曝光");
    }

    @Override
    public void onADClosed()
    {
        Log.d(tag,"广告被关闭");
    }
}
