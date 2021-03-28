package com.littesandbox.clicksandbox;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.littlesandbox.clicksandbox.R;
import com.soulgame.sgsdk.tgsdklib.TGSDK;
import com.soulgame.sgsdk.tgsdklib.ad.ITGADListener;

public class MainActivity extends Activity
{
	//句子索引
	int iindex=0;
    int progress=0;
    /**自动点击的剩余时间 秒**/
    int timeleft=60;

    ProgressBar bar;
    TextView randView;
    TextView unlocked_stn;
    TextView timeleftView;
    ListView list;
    //原来的文本
    String aaaa_raw_about_text="小沙盒温馨提示，如果你点了赞助我，会播放一段视频广告，这些广告大多数都是虚假宣传，建议不要下载，我添加自愿赞助功能也是相当于接受捐赠。因为我是自学编程的学生，所以没有收入，而我又想做好的游戏，小软件工具，等，光靠用爱发电是早晚撑不住的，所以，你喜欢我的这个游戏或者开发的其他游戏，想提供一点帮助的话，可以通过观看广告来赞助我。目前我面临的是没有团队和资金的问题，我是学的多媒体制作专业，和编程没什么关系，但我喜欢编程";
    String sentences[]={"道可道，非常道","有空去看看《国际歌》，不要忘记了前人的努力","上学时，不要整天想着玩游戏，等放了假，随便玩。",".如果感到焦虑不安，可以试试正念疗法。","学历代表过去，学习能力代表将来","生于忧患，死于安乐","我的世界是一款自由的沙盒游戏，你可以试试","游戏是世界第九大艺术","金坷垃是检验神曲的唯一标准","github是全球最大的软件技术开源平台","在《光遇》遇见每一个温柔的人","一根葱这种零食挺好吃的","如果对网页制作感兴趣，可以试试easypage","真正有目标的人，不会整天看电视，只有闲人才会从早看到晚","学历不能代表一切，没有高学历，一样可以从事自己喜欢的事情并去开公司，只是会更加艰难一点。","读书不能死读书，要活学活用","身体简况是做其他事情的基础条件","要做大事，先从小事做起","在追求梦想的时候，不能被资本腐蚀了心志，要明白你转来的钱都是广大劳动人民的。","学习任何东西，不管是自学还是老师教，都要记得做笔记，如果不练习，不使用，很快就会遗忘","如果觉得思维混乱，可以试试思维导图",".透写台是个好工具，可以帮助你更好地临摹","到了初中以后，可以思考下学习是为了什么，不要变成学习机器，不能过于功利主义","字典上的字都是互相解释的","赚钱不能吃香太难看，不能恶心人，要取一种平衡状态","是金子总会发光","音乐最擅长表达的是情绪，但不擅长表达理性逻辑","出名的方式有很多种，出臭名还是出美名，我的观点是出美名更好"};
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
	TextView unlockT;
	//封装类
	Bgm bgm;
//	EasySoundPool tool;
    //防止多次点击自动按钮
    boolean canClickAutoBtn;
    public String sceneId2="TunK2jGMTlP7i2cFHgm";
    @Override
    protected void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,new ArrayList<String>());
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
        //NOTE:TGSDK 绑定Listener
        TGSDK.setADListener(new ITGADListener() {
            @Override
            public void onShowSuccess(String scene, String s1)
            {
                Toast.makeText(ctx,"展示成功",Toast.LENGTH_SHORT).show();
//                Toast.makeText(ctx,scene,Toast.LENGTH_LONG).show();
            }
            @Override
            public void onShowFailed(String s, String s1, String s2)
            {
                Toast.makeText(ctx,"展示失败",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onADClick(String s, String s1)
            {
                Toast.makeText(ctx,"点击了广告",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onADClose(String scene, String s1, boolean b)
            {
                Toast.makeText(ctx,"关闭了广告",Toast.LENGTH_SHORT).show();
                if(scene.equals(Global.sceneid))
                {
                    Toast.makeText(MainActivity.this,"开始自动点击",Toast.LENGTH_SHORT).show();
                    M_AutoClick();
                    playBgm();
                }
            }
        });
    }
    //自动点击
    public void M_AutoClick()
    {
        final Timer timer=new Timer();
        TimerTask task=new TimerTask()
        {
            @Override
            public void run()
            {
                progress+=2;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bar.setProgress(progress);
                        showprogress.setText(progress+"/"+"100");
                        timeleft-=1;
                        timeleftView.setText("剩余时间："+Integer.toString(timeleft));
                        if(progress>=100)
                        {
                            add_stn();
                        }
                        if(timeleft<=0)
                        {
                            timer.cancel();
                            timeleft=60;
                            timeleftView.setText("剩余时间：60");
                            Easy.tip(ctx,"时间到");
                        }
                        //Toast.makeText(ctx,"1秒到",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        timer.schedule(task,0,1000);
    }
    //handle处理
    public void myHandler()
    {
        Handler handler=new Handler();
       // handler.getMessageName();

    }
//初始化界面
	private void initView()
	{
        ctx=getApplicationContext();
		bar=findViewById(R.id.bar);
		showprogress=findViewById(R.id.progress);
        len_T=findViewById(R.id.len);
		scoreT=findViewById(R.id.score);
		unlocked_stn=findViewById(R.id.unlock_stn);
		unlockT=findViewById(R.id.unlocked);
        randView=findViewById(R.id.rand);
        timeleftView=findViewById(R.id.timeleft);
        unlocked_stn=findViewById(R.id.unlock_stn);
        //显示有多少个句子
		list=findViewById(R.id.list);
	}
    //初始化数据
    public void initData() throws IOException  
    {
        String  dir=getFilesDir().getPath();
        File gameDataFile=new File(dir+"/test.txt");
        if(gameDataFile.exists())
        {
            Toast.makeText(ctx,"游戏文件存在",Toast.LENGTH_LONG).show();
            for(int i=0;i<sentences.length;i++)
            {
                stn.add(sentences[i]);
            } 
            new Game().load("test.txt",MainActivity.this,adapter,stn,sentences);
            //  Toast.makeText(ctx,"stn长度"+stn.size(),1000).show();
        }
        if(gameDataFile.exists()==false)
        {
            Toast.makeText(ctx,"游戏文件不存在",Toast.LENGTH_SHORT).show();
            //原始数据
            for(int i=0;i<sentences.length;i++)
            {
                stn.add(sentences[i]);
            }
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
        EasyDialog tool= new EasyDialog();
        tool.init(MainActivity.this);
        tool.setMessage("注意!","确定要重置吗,将会清除数据所有游戏数据");
        tool.dialog.setPositiveButton("确认",new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface p1,int p2)
                {
                    Toast.makeText(ctx,"外部调用",Toast.LENGTH_SHORT).show();
                    String dir=getFilesDir().getPath();
                    File f=new File(dir+"/test.txt");
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
	    bgm=new Bgm();
		bgm.init(ctx);
		bgm.play();
	}
	//停止播放bgm
	public void stopBgm(View v)
    {
        bgm.stop();
    }
	@Override
	protected void onDestroy()
    {
		super.onDestroy();
		bgm.stop();
	}
    //关于此游戏
    public void about(View v)
    {
        EasyDialog tool=new EasyDialog();
        tool.init(this);
        tool.setMessage("关于此游戏","点击来增加进度，收集新的文本，争取把所有的文本都收集完成吧！。");
        tool.dialog.setPositiveButton("确认",null);
        tool.show();
    }
	//跳转到每日一句
	public void openWeb(View v)
	{
		Uri uri=Uri.parse("http://godotjstest.4uv.top/index.html");
		Intent i=new Intent(Intent.ACTION_VIEW,uri);
		startActivity(i);
	}
    //自动点击 对接tgsdk
    public void auto(View v)
    {
        EasyDialog dialog_builder=new EasyDialog();
        dialog_builder.init(this);
        dialog_builder.setMessage("观看视频，奖励自动点击","是否观看视频？");
        dialog_builder.dialog.setNegativeButton("观看", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                if(TGSDK.couldShowAd(sceneId2))
                {
                    Toast.makeText(ctx,"可以显示广告，即将显示广告",Toast.LENGTH_LONG).show();
                    bgm.stop();
                    TGSDK.showAd(MainActivity.this,sceneId2);
                }
                else{
                    Toast.makeText(ctx,"不能显示广告",Toast.LENGTH_LONG).show();
                }
            }
        });
        dialog_builder.dialog.setPositiveButton("不观看",null);
        dialog_builder.show();
       // Toast.makeText(this,"此功能未实现",Toast.LENGTH_LONG).show();

    }
    //展示更多游戏
	public void showothergames(View v)
	{
        Intent i=new Intent(ctx,MoreGame.class);
        startActivity(i);
		//Toast.makeText(ctx,"功能没有做",Toast.LENGTH_LONG).show();
	}
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        bgm.stop();
        //File file = new File("/sdcard/Download/test.txt");
        /*      try{
         Game.save(stn, file);
         }catch(IOException e)
         {}*/
        Toast.makeText(ctx,"如果不小心退出，请按图片重新进入主界面",Toast.LENGTH_LONG).show();
    }
	//联系作者
	public void contect(View v)
	{
		EasyDialog tool=new EasyDialog();
        tool.init(this);
        tool.setMessage("联系作者","请加qq2439905184");
        tool.dialog.setPositiveButton("确认",new DialogInterface.OnClickListener()
        {
                @Override
                public void onClick(DialogInterface p1,int p2)
                {
                }
            });
        tool.show();
	}
	//当进度值到达100
	public void add_stn()
    {
        if(progress>=100) {
            progress = 0;
            score += 5;
            scoreT.setText("积分:" + score);
            bar.setProgress(progress);
            int size=stn.size();
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
        }
    }
	//点击事件
	public void clickit(View v)
	{
		progress+=Global.progress_add_delta;
		bar.setProgress(progress);
		showprogress.setText(progress+"/"+"100");
		//todo 添加一个每次点击按钮时的音效 留住玩家
		//数组长度
		int size=stn.size();
        //     Toast.makeText(ctx,"临时数组长度"+size,500).show();
        //可变句子数组
//        if(size>0)
//        {
//            adapter.add(stn.get(0));
//            stn.remove(0);
//        }
        unlockT.setText("解锁句子个数"+adapter.getCount()+"/"+sentences.length);
		if(progress>=100) {
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
        }
         //Toast.makeText(ctx,"句子数组length"+len,1000).show();*/
    }	

}
	
