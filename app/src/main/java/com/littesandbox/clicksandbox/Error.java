package com.littesandbox.clicksandbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.app.*;
import android.content.Intent;
import java.io.File;
import android.widget.Toast;
import android.widget.LinearLayout;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import android.widget.TextView;

import com.littlesandbox.clicksandbox.R;
import com.soulgame.sgsdk.tgsdklib.TGSDK;

import java.util.ArrayList;
public class Error extends Activity {
   LinearLayout  root;
   TextView gameDataView;
   TextView arrayView;
   public String appid="t837I7Bf404466Bv6n0N";
   public String sceneId="s2q4S9t06AYt8wpr4Zc";
   public String sceneId2="TunK2jGMTlP7i2cFHgm";
   ArrayList<String> s=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error);
        root=   (LinearLayout) $.select_id(this,R.id.root);
        gameDataView=(TextView) $.select_id(this,R.id.gameData);
        arrayView= (TextView) $.select_id(this,R.id.arraylist);
        new Thread(){
            @Override
            public void run()
            {
                //初始化tgsdk
                TGSDK.initialize(Error.this,appid,null);
                TGSDK.preloadAd(Error.this);
                if(TGSDK.couldShowAd(sceneId2))
                {
                    Toast.makeText(Error.this,"初始化完毕",Toast.LENGTH_LONG).show();
                }
                else{
                    //Toast.makeText(Error.this,"不能显示广告",Toast.LENGTH_LONG).show();
                }
                //super.run();
            }
        }.run();
    }
    //测试广告
    public void testAd(View v)
    {
        TGSDK.showTestView(Error.this,sceneId2);
    }
    //重置游戏
    public void reset(View v)
    {
        String dir=getFilesDir().getPath();
        File f=new File(dir+"/test.txt");
        boolean result= f.delete();
        Toast.makeText(this,"删除状态"+result,Toast.LENGTH_LONG).show();
    }
    //进入游戏
    public void enter(View v)
    {
        startActivity(new Intent(this, MainActivity.class));
    }
    //获得文件信息
    public void get(View v) throws IOException
    {
        String  dir=getFilesDir().getPath();
        File gameDataFile=new File(dir+"/test.txt");
        FileReader fileReader=new FileReader(gameDataFile);
        BufferedReader reader = new BufferedReader(fileReader);
        String tmp_line;
        String result="";
        while((tmp_line=reader.readLine())!=null)
        {
            result+=tmp_line;
            s.add(tmp_line);
        }
        gameDataView.setText(result);
      //  s.add(result);
        arrayView.setText(s.toString()+"arrayLsit长度"+s.size());
    }
}
