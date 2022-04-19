package com.littlesandbox.clicksandbox;
import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Toast;

import com.littlesandbox.clicksandbox.AdListener.BannerListener;
import com.zh.pocket.ads.banner.BannerAD;

import java.io.File;

public class MainMenu extends Activity {
    private String tag;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
        tag = this.getClass().toString();
        //广告
        BannerAD ad = new BannerAD(MainMenu.this,Global.bannerid);
        ad.setBannerADListener(new BannerListener(this.getClass()));
        ad.loadAD(findViewById(R.id.mainmenu_banner_layout));
    }
    public void start_game(View v)
    {
        startActivity(new Intent(MainMenu.this,MainActivity.class));
    }
    //重置游戏
    public void reset(View v) {
        String dir = getFilesDir().getPath();
        File f = new File(dir + "/test.txt");
        boolean result = f.delete();
        Log.d(tag,"删除状态" + result);
        if(!result)
        {
            Log.e(tag,"删除未成功");
        }
        else {
               Toast.makeText(this,"重置完成！",Toast.LENGTH_LONG).show();
            }
    }
    public void about(View v)
    {
        EasyDialog tool=new EasyDialog();
        tool.init(this);
        tool.setMessage("关于此游戏","全力收集句子吧！，");
        tool.builder.setNegativeButton("确认",null);
        tool.show();
    }
    //联系作者
    public void contect(View v)
    {
        EasyDialog tool = new EasyDialog();
        tool.init(this);
        tool.setMessage("联系作者","请加qq2439905184");
        tool.builder.setNegativeButton("确认",null);
        tool.show();
    }
    //打开每日一句网站
    public void openEveryDay()
    {
        EasyDialog tool = new EasyDialog();
        tool.init(MainMenu.this);
        tool.setMessage("公告","此网站已关闭");
        tool.builder.setNegativeButton("确认",null);
        tool.show();
    }
    //展示更多游戏
    public void show_other_games(View v)
    {
        startActivity(new Intent(MainMenu.this,MoreGame.class));
    }

}
