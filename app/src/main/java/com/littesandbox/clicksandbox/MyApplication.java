package com.littesandbox.clicksandbox;

import android.app.Application;

import com.zh.pocket.PocketSdk;
import com.zh.pocket.common.config.ADConfig;

public class MyApplication extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
        ADConfig.setEnableLogger(true);
        //PocketSdk.initSDK(this, "ylh", "10827");
        PocketSdk.initSDK(getApplicationContext(), "TapTap", "10827");
    }

}
