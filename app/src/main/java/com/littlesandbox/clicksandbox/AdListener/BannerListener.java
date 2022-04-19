package com.littlesandbox.clicksandbox.AdListener;

import android.util.Log;

import com.zh.pocket.ads.banner.BannerADListener;
import com.zh.pocket.http.bean.ADError;
//常见复用类
public class BannerListener implements BannerADListener
{
    private String tag;
    private String caller;//调用者
    /**
     * @param p_caller 调用者
     * */
    public BannerListener(Class p_caller)
    {
        tag = this.getClass().toString();
        caller = p_caller.toString();
    }
    @Override
    public void onSuccess() {
        Log.d(tag,"Banner广告成功播放");
    }

    @Override
    public void onFailed(ADError adError)
    {
        Log.e(tag,"广告播放失败");
        Log.e(tag,"调用者" + caller);
        Log.e(tag,String.valueOf(adError.getCode()));
        Log.e(tag,adError.getMessage());
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
