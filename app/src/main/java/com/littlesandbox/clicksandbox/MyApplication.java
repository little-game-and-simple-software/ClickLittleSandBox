package com.littlesandbox.clicksandbox;

import android.app.ActivityManager;
import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.zh.pocket.PocketSdk;
import com.zh.pocket.common.config.ADConfig;

public class MyApplication extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
        ADConfig.setEnableLogger(true);
        //app启动时间统计
        long startTime = System.currentTimeMillis();
        PocketSdk.initSDK(getApplicationContext(), "TapTap", "10827");
        long time = System.currentTimeMillis()-startTime;
        Log.d("time_cost",String.valueOf(time)+"ms");
        Toast.makeText(this,"初始化时间"+String.valueOf(time),Toast.LENGTH_LONG).show();
        //initActivityManager();
    }
   /* private void initActivityManager()
    {
        long startTime = System.currentTimeMillis();
        long time = System.currentTimeMillis()-startTime;
        Log.d("initActivityManager",String.valueOf(time));

    }
*/
}
