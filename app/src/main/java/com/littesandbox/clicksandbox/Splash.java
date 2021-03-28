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

import java.util.Map;

public class Splash extends Activity {
    ImageView image;
//    boolean m_sdk_init_state ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        image.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View p1) {
                    Intent i=new Intent(getApplicationContext(), Error.class);
//                    if(m_sdk_init_state)
//                    {
//                        startActivity(i);
//                    }
//                    else
//                        {
//                            Toast.makeText(Splash.this,"初始化失败,自动点击功能不可用",Toast.LENGTH_SHORT).show();
//                        }
                }
            });
    }

}
