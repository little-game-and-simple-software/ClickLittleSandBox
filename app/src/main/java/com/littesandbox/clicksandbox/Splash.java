package com.littesandbox.clicksandbox;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.littlesandbox.clicksandbox.R;
import com.soulgame.sgsdk.tgsdklib.TGSDK;
import com.soulgame.sgsdk.tgsdklib.TGSDKServiceResultCallBack;
import com.soulgame.sgsdk.tgsdklib.ad.ITGPreloadListener;

import java.util.Map;
public class Splash extends Activity {
    ImageView image;
    boolean preload_state ;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        image =  findViewById(R.id.splash_icon);
        setContentView(R.layout.splash);
        //初始化TGSDK
        TGSDK.initialize(Splash.this, Global.appid, new TGSDKServiceResultCallBack() {
            @Override
            public void onSuccess(Object o, Map<String, String> map)
            {
                //boolean sdk_init_state=true;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {
                        Toast.makeText(Splash.this,"初始化成功",Toast.LENGTH_SHORT).show();
                    }
                });
            }
            @Override
            public void onFailure(Object o, String s)
            {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {
                        Toast.makeText(Splash.this,"初始化失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        TGSDK.preloadAd(this, new ITGPreloadListener() {
            @Override
            public void onPreloadSuccess(String result)
            {
                // 广告配置获取成功
                preload_state=true;
                Toast.makeText(Splash.this,"广告预加载成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPreloadFailed(String scene, String error)
            {
                // 广告配置获取失败
                preload_state=false;
                Toast.makeText(Splash.this,"广告预加载失败"+error,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAwardVideoLoaded(String result)
            {
                //奖励视频已就绪
                Toast.makeText(Splash.this,"奖励视频已就绪",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInterstitialLoaded(String result) {
                //静态插屏已就绪
                Toast.makeText(Splash.this,"静态插屏已就绪",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInterstitialVideoLoaded(String result)
            {
                //插屏视频已就绪
                Toast.makeText(Splash.this,"插屏视频已就绪",Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void startGame(View v)
    {
        Intent i=new Intent(this,Error.class);
        if(preload_state)
        {
            startActivity(i);
        }
        else
            {
                Toast.makeText(this,"必要功能未加载完成，无法使用自动点击功能",Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
    }
}
