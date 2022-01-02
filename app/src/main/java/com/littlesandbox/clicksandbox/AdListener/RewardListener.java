package com.littlesandbox.clicksandbox.AdListener;

import android.util.Log;

import com.zh.pocket.ads.reward_video.RewardVideoADListener;
import com.zh.pocket.http.bean.ADError;

public class RewardListener implements RewardVideoADListener
{
    @Override
    public void onADLoaded() {

    }

    @Override
    public void onVideoCached() {

    }

    @Override
    public void onADShow() {

    }

    @Override
    public void onADExpose() {

    }

    @Override
    public void onReward() {

    }

    @Override
    public void onADClicked() {

    }

    @Override
    public void onVideoComplete() {

    }

    @Override
    public void onADClosed() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailed(ADError adError) {
        Log.d("ADError", adError.getMessage());
        Log.d("ADError", String.valueOf(adError.getCode()));
    }

    @Override
    public void onSkippedVideo() {

    }

    @Override
    public void onPreload() {

    }
}
