package com.littlesandbox.clicksandbox;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

//import com.soulgame.sgsdk.tgsdklib.TGSDK;

import com.littlesandbox.clicksandbox.utils.ftz;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends Activity
{
    //当前已经收集的句子总数
    private int collected_senten = 0;
    private int clicks = 290;//初始0
	//句子索引
	int iindex = 0;
    int progress = 0;
    ProgressBar bar;
    TextView randView;
    TextView unlocked_stn;
    ListView list;
    TextView tmptext;
    //存档临时数据的arraylist
    ArrayList<String> stn = new ArrayList<String>();
    Context ctx;
    TextView len_T;
    //显示进度
    TextView showprogress;
	ArrayAdapter<String> adapter;
	TextView scoreT;
	int score=0;
	//解锁的句子个数
	private TextView unlockTv;
	//封装类
	Bgm bgm;
	EasySoundPool easySoundPool;
    //防止多次点击自动按钮
    boolean canClickAutoBtn;
    private ImageView sandbox;
    private ObjectAnimator knock_animation;
    private EasySoundPool pool2;//成就pool
    @Override
    protected void onCreate(Bundle savedInstanceState) 
	{
        //   requestPermissions();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        easySoundPool = new EasySoundPool(10);
        easySoundPool.load(MainActivity.this, R.raw.qubodup_crash,5);
        sandbox = findViewById(R.id.sandbox);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,new ArrayList<String>());
        // list.setAdapter(adapter);
        canClickAutoBtn=true;
		initView();
        try
        {
            initData();
        } catch(IOException e)
        {}
        list.setAdapter(adapter);
        init_sound();
		playBgm();
		//listadapter数据长度
		//创建数组适配器，4个参数 
    }
//初始化界面
	private void initView()
	{
        ctx = getApplicationContext();
		bar = findViewById(R.id.bar);
		showprogress = findViewById(R.id.progress);
        len_T = findViewById(R.id.len);
		scoreT = findViewById(R.id.score);
		unlocked_stn = findViewById(R.id.unlock_stn);
		unlockTv = findViewById(R.id.unlocked);
        randView = findViewById(R.id.rand);
        unlocked_stn = findViewById(R.id.unlock_stn);
        //显示有多少个句子
		list=findViewById(R.id.list);
	}
    //初始化数据
    public void initData() throws IOException  
    {
        //从xml文件载入所有的句子
        String[] all_sentens = getResources().getStringArray(R.array.sentens);
        String  dir = getFilesDir().getPath();
        File gameDataFile=new File(dir+"/test.txt");
        if(gameDataFile.exists())
        {
            Toast.makeText(ctx,"游戏文件存在",Toast.LENGTH_LONG).show();
            //载入所有的句子
            for(int i =0; i < all_sentens.length; i++)
            {
                stn.add(all_sentens[i]);
            }
            //加载已完成的句子
            new Game().load("test.txt",MainActivity.this,adapter,stn,all_sentens);
            collected_senten = Game.load_collect_senten_count(MainActivity.this);
            unlockTv.setText("解锁个数" +String.valueOf(collected_senten) +"/" +"28");
            //  Toast.makeText(ctx,"stn长度"+stn.size(),1000).show();
        }
        else
            {
                Toast.makeText(ctx,"游戏文件不存在",Toast.LENGTH_LONG).show();

                for(int i =0;i < all_sentens.length;i++)
                {
                    stn.add(all_sentens[i]);
                }
            }
    }
    public void init_sound()
    {
        //成就pool
        pool2 = new EasySoundPool(2);
        pool2.direct_load(MainActivity.this,R.raw.big_egg_collect,5);
    }
    //保存进度
    public void save(View v)
    {
        try
        {
            new Game().save(adapter,MainActivity.this);
            Game.save_clicks(MainActivity.this,clicks);
            Game.save_collect_senten_count(MainActivity.this,collected_senten);
            //Game.save_achivement(MainActivity.this,achivementStruct1,0);
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    //重置进度
    public void reset(View v)
    {
        EasyDialog tool = new EasyDialog();
        tool.init(MainActivity.this);
        tool.setMessage("注意!","确定要重置吗,将会清除数据所有游戏数据");
        tool.builder.setNegativeButton("确认",new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface p1,int p2)
                {
                    Toast.makeText(ctx,"外部调用",Toast.LENGTH_LONG).show();
                    String dir = getFilesDir().getPath();
                    File senten_file = new File(dir + "/test.txt");
                    File achivement1 = new File(dir + "/achivement0.json");
                    File achivement2 = new File(dir + "/achivement1.json");
                    File achivement3 = new File(dir + "/achivement2.json");
                    File achivement4 = new File(dir + "/achivement3.json");
                    if(senten_file.exists() && achivement1.exists() && achivement2.exists() && achivement4.exists())
                    {
                        boolean result= senten_file.delete();
                        achivement1.delete();
                        achivement2.delete();
                        achivement3.delete();
                        achivement4.delete();
                        Toast.makeText(ctx,"删除状态"+result,Toast.LENGTH_LONG).show();
                    }
                    else
                        {
                            Toast.makeText(MainActivity.this,"存档出错,请重装游戏解决！",Toast.LENGTH_LONG).show();
                        }
                    //重置界面
                    recreate();
                }
            });
        tool.show();
    }
	private void playBgm()
	{
	    bgm = new Bgm();
		bgm.init(ctx);
		//bgm.play();
	}
	@Override
	protected void onDestroy()
    {
		super.onDestroy();
		bgm.stop();
	}
    //自动点击
    public void auto(View v)
    {

    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        //File file = new File("/sdcard/Download/test.txt");
        /*      try{
         Game.save(stn, file);
         }catch(IOException e)
         {}*/
       // Toast.makeText(ctx,"如果不小心退出，请按图片重新进入主界面",Toast.LENGTH_LONG).show();
    }
	//点击成就按钮
    public void achievement(View v)
    {
        Intent i = new Intent(MainActivity.this,Achievement.class);
        startActivity(i);
    }
    //手动点击 View:小沙盒的图标
	public void clickit(View v)
    {
        easySoundPool.play(easySoundPool.click_stream_id);
        clicks += 1;
        progress += 5;
        bar.setProgress(progress);
        showprogress.setText(progress + "/" + "100");
        //盒子被敲打之后的抖动 动画
        knock_animation  = ObjectAnimator.ofFloat(sandbox, "translationX", 0, -100f ,-100, 0);
        knock_animation.setDuration(1000);
        knock_animation.start();
        //数组长度
        int size = stn.size();
        if(clicks == 300)
        {
            AchivementStruct achivementStruct = new AchivementStruct();
            achivementStruct.title = "手废了没";
            achivementStruct.lock_status = false;
            achivementStruct.current_progress = 300;
            ftz.send_Notification(MainActivity.this,achivementStruct.title);
            Game.save_achivement(MainActivity.this,achivementStruct,1);
            pool2.direct_play(1);
        }
        if(clicks == 1000)
        {
            AchivementStruct achivementStruct = new AchivementStruct();
            achivementStruct.title = "人型打桩机";
            achivementStruct.lock_status = false;
            achivementStruct.current_progress = 300;
            ftz.send_Notification(MainActivity.this,achivementStruct.title);
            Game.save_achivement(MainActivity.this,achivementStruct,2);
            pool2.direct_play(1);
        }
        if(clicks == 3000)
        {
            AchivementStruct achivementStruct = new AchivementStruct();
            achivementStruct.title = "持之以恒";
            achivementStruct.lock_status = false;
            achivementStruct.current_progress = 3000;
            ftz.send_Notification(MainActivity.this,achivementStruct.title);
            Game.save_achivement(MainActivity.this,achivementStruct,3);
            pool2.direct_play(1);
        }
        //可变句子数组
        /*if(size>0)
        {
            adapter.add(stn.get(0));
            stn.remove(0);
        }*/
        //unlockTv.setText("解锁句子个数" + adapter.getCount() + "/" + sentences.length);
        if (progress >= 100) {
            progress = 0;
            bar.setProgress(progress);
            score += 5;
            scoreT.setText("积分:" + score);
            collected_senten += 1;
            unlockTv.setText("解锁个数" +String.valueOf(collected_senten) +"/" +"28");
            //int len=sentences.length;
            //todo 添加一个进度满了的胜利音效 留住玩家
            if (size > 0) {
                String toadd = stn.get(0);
                adapter.add(toadd);
                adapter.notifyDataSetChanged();
                stn.remove(0);
            }
            /*randView.setText("当前数组长度" + size);
            int count = adapter.getCount();
            len_T.setText("adapter长度" + count);*/
            //Toast.makeText(ctx,"句子数组length"+len,1000).show();*/
        }
    }
}
	
