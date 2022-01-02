package com.littlesandbox.clicksandbox.AdListener;

import android.util.Log;

import com.zh.pocket.ads.banner.BannerADListener;
import com.zh.pocket.http.bean.ADError;

public class BannerListener implements BannerADListener
{
    public String tag="MyBannerListener";
    @Override
    public void onSuccess()
    {
        Log.d(tag,"Success");
    }

    @Override
    public void onFailed(ADError adError) {
        Log.d("ADError", adError.getMessage());
        Log.d("ADError", String.valueOf(adError.getCode()));
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
