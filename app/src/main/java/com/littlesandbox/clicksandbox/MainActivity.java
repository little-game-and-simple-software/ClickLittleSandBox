package com.littlesandbox.clicksandbox;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

//import com.soulgame.sgsdk.tgsdklib.TGSDK;
import com.zh.pocket.ads.interstitial.InterstitialAD;
import com.zh.pocket.ads.interstitial.InterstitialADListener;
import com.zh.pocket.http.bean.ADError;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends Activity
{
	//句子索引
	int iindex=0;
    int progress=0;
    ProgressBar bar;
    TextView randView;
    TextView unlocked_stn;
    ListView list;
    //String sentences[]={"道可道，非常道","有空去看看《国际歌》，不要忘记了前人的努力","上学时，不要整天想着玩游戏，等放了假，随便玩。","如果感到焦虑不安，可以试试正念疗法。","学历代表过去，学习能力代表将来","生于忧患，死于安乐","我的世界是一款自由的沙盒游戏，你可以试试。","游戏是世界第九大艺术","金坷垃是检验神曲的唯一标准","github是全球最大的软件技术开源平台","在《光遇》遇见每一个温柔的人","一根葱这种零食挺好吃的","如果对网页制作感兴趣，可以试试easypage","真正有目标的人，不会整天看电视，只有闲人才会从早看到晚","学历不能代表一切，没有高学历，一样可以从事自己喜欢的事情并去开公司，只是会更加艰难一点。","读书不能死读书，要活学活用","身体是革命的本钱","要做大事，先从小事做起","在追求梦想的时候，不能被资本腐蚀了心志，要明白你转来的钱都是广大劳动人民的。","学习任何东西，不管是自学还是老师教，都要记得做笔记，如果不练习，不使用，很快就会遗忘","如果觉得思维混乱，可以试试思维导图","透写台是个好工具，可以帮助你更好地临摹","到了初中以后，可以思考下学习是为了什么，不要变成学习机器，不能过于功利主义","字典上的字都是互相解释的","赚钱不能吃香太难看，不能恶心人，要取一种平衡状态","是金子总会发光","音乐最擅长表达的是情绪，但不擅长表达理性逻辑","出名的方式有很多种，出臭名还是出美名，我的观点是出美名更好"};
    TextView tmptext;
    //存档临时数据的arraylist
    ArrayList<String> stn=new ArrayList<String>();
    Context ctx;
    TextView len_T;
    //显示进度
    TextView showprogress;
	ArrayAdapter<String> adapter;
	TextView scoreT;
	int score=0;
	//解锁的句子个数
	TextView unlockTv;
	//封装类
	Bgm bgm;
	EasySoundPool tool;
    //防止多次点击自动按钮
    boolean canClickAutoBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
	{
        //   requestPermissions();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        String  dir = getFilesDir().getPath();
        File gameDataFile=new File(dir+"/test.txt");
        if(gameDataFile.exists())
        {
            Toast.makeText(ctx,"游戏文件存在",Toast.LENGTH_LONG).show();
            /*for(int i =0;i < sentences.length;i++)
            {
                stn.add(sentences[i]);
            } 
            new Game().load("test.txt",MainActivity.this,adapter,stn,sentences);
            //  Toast.makeText(ctx,"stn长度"+stn.size(),1000).show();*/

        }
        else
            {
                Toast.makeText(ctx,"游戏文件不存在",Toast.LENGTH_LONG).show();
                String[] all_sentens = getResources().getStringArray(R.array.sentens);
                //getString(R.string.senten1);
                /*for(int i =0;i < sentences.length;i++)
                {
                    stn.add(sentences[i]);
                }*/
            }
    }
    //保存进度
    public void save(View v)
    {
        try
        {
            new Game().save(adapter,MainActivity.this);
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
                    File f = new File(dir+"/test.txt");
                    if(f.exists())
                    {
                        boolean result= f.delete();
                        Toast.makeText(ctx,"删除状态"+result,Toast.LENGTH_LONG).show();
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
		bgm.play();
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
        Toast.makeText(ctx,"如果不小心退出，请按图片重新进入主界面",Toast.LENGTH_LONG).show();
    }
	//点击成就按钮
    public void achievement(View v)
    {
        Intent i = new Intent(MainActivity.this,Achievement.class);
        startActivity(i);
    }
    //手动点击
	public void clickit(View v) {
        progress += 5;
        bar.setProgress(progress);
        showprogress.setText(progress + "/" + "100");
        //todo 添加一个每次点击按钮时的音效 留住玩家
        //数组长度
        int size = stn.size();
        //Toast.makeText(ctx,"临时数组长度"+size,500).show();
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
            //int len=sentences.length;
            //todo 添加一个进度满了的胜利音效 留住玩家
            if (size > 0) {
                String toadd = stn.get(0);
                adapter.add(toadd);
                adapter.notifyDataSetChanged();
                stn.remove(0);
            }
            randView.setText("当前数组长度" + size);
            int count = adapter.getCount();
            len_T.setText("adapter长度" + count);
            //Toast.makeText(ctx,"句子数组length"+len,1000).show();*/
        }
    }
}
	
