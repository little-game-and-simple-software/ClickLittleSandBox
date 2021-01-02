package com.littesandbox.clicksandbox;
 
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView;
import android.widget.Adapter;
import android.content.Intent;
import android.net.Uri;
import android.app.AlertDialog;
import android.os.Build;
import android.app.Dialog;
import android.content.DialogInterface;
import java.io.File;
import java.io.IOException;
/*所有的句子

 1道可道，非常道
 2.有空去看看《国际歌》，不要忘记了前人的努力
 3.上学时，不要整天想着玩游戏，等放了假，随便玩。
 4.如果感到焦虑不安，可以试试正念疗法。
 5.学历代表过去，学习能力代表将来
 6.生于忧患，死于安乐
 7.我的世界是一款自由的沙盒游戏，你可以试试
 8.游戏是世界第九大艺术
 9.金坷垃是检验神曲的唯一标准
 10.github是全球最大的软件技术开源平台
 11.在《光遇》遇见每一个温柔的人
 12.一根葱这种零食挺好吃的
 13.如果对网页制作感兴趣，可以试试easypage
 14.真正有目标的人，不会整天看电视，只有闲人才会从早看到晚
 15.学历不能代表一切，没有高学历，一样可以从事自己喜欢的事情并去开公司，只是会更加艰难一点。
 16.读书不能死读书，要活学活用
 17.身体简况是做其他事情的基础条件
 18.要做大事，先从小事做起
 19.在追求梦想的时候，不能被资本腐蚀了心志，要明白你转来的钱都是广大劳动人民的。
 20学习任何东西，不管是自学还是老师教，都要记得做笔记，如果不练习，不使用，很快就会遗忘。
 21.如果觉得思维混乱，可以试试思维导图
 22.透写台是个好工具，可以帮助你更好地临摹
 23.到了初中以后，可以思考下学习是为了什么，不要变成学习机器，不能过于功利主义
 24.字典上的字都是互相解释的
 25.赚钱不能吃香太难看，不能恶心人，要取一种平衡状态
 26.是金子总会发光
 27.音乐最擅长表达的是情绪，但不擅长表达理性逻辑
 28.出名的方式有很多种，出臭名还是出美名，我的观点是出美名更好*/
public class MainActivity extends Activity
{
	//句子索引
	int iindex=0;
     int progress=0;
	 ProgressBar bar;
	 TextView randView;
	 TextView unlocked_stn;
	 ListView list;
	 String sentences[]={"道可道，非常道","有空去看看《国际歌》，不要忘记了前人的努力","上学时，不要整天想着玩游戏，等放了假，随便玩。",".如果感到焦虑不安，可以试试正念疗法。","学历代表过去，学习能力代表将来","生于忧患，死于安乐","我的世界是一款自由的沙盒游戏，你可以试试","游戏是世界第九大艺术","金坷垃是检验神曲的唯一标准","github是全球最大的软件技术开源平台","在《光遇》遇见每一个温柔的人","一根葱这种零食挺好吃的","如果对网页制作感兴趣，可以试试easypage","真正有目标的人，不会整天看电视，只有闲人才会从早看到晚","学历不能代表一切，没有高学历，一样可以从事自己喜欢的事情并去开公司，只是会更加艰难一点。","读书不能死读书，要活学活用","身体简况是做其他事情的基础条件","要做大事，先从小事做起","在追求梦想的时候，不能被资本腐蚀了心志，要明白你转来的钱都是广大劳动人民的。","学习任何东西，不管是自学还是老师教，都要记得做笔记，如果不练习，不使用，很快就会遗忘","如果觉得思维混乱，可以试试思维导图",".透写台是个好工具，可以帮助你更好地临摹","到了初中以后，可以思考下学习是为了什么，不要变成学习机器，不能过于功利主义","字典上的字都是互相解释的","赚钱不能吃香太难看，不能恶心人，要取一种平衡状态","是金子总会发光","音乐最擅长表达的是情绪，但不擅长表达理性逻辑","出名的方式有很多种，出臭名还是出美名，我的观点是出美名更好"};
	 TextView tmptext;
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
	EasySoundPool tool;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		initView();
        
		playBgm();
		tool=initSoundPool();
		//todo 添加一个循环背景音乐，留住玩家
		for(int i=0;i<sentences.length;i++)
		{
			stn.add(sentences[i]);
		}
		
		//显示随机数
		randView=	findViewById(R.id.rand);
		unlocked_stn=findViewById(R.id.unlock_stn);
		list=findViewById(R.id.list);
		//listadapter数据长度
		len_T=findViewById(R.id.len);
		//创建数组适配器，4个参数
            adapter = new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,getData());
			list.setAdapter(adapter);
    }

	private void initView()
	{
		bar=findViewById(R.id.bar);
		showprogress=findViewById(R.id.progress);
		ctx=getApplicationContext();
		
		scoreT=findViewById(R.id.score);
		unlocked_stn=findViewById(R.id.unlock_stn);
		unlockT=findViewById(R.id.unlocked);
	}

	private EasySoundPool initSoundPool() 
	{
		EasySoundPool tool=new EasySoundPool();
		return tool;
	}

	private void playBgm()
	{
	    bgm=new Bgm();
		bgm.init(ctx);
		bgm.play();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		bgm.stop();
	}
	
	private List<String> getData(){
        List<String> data = new ArrayList<String>();
       /* data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");
        data.add("测试数据4");j*/
        return data;
    }
	//赞助作者的按钮
	public void showad(View v)
	{
        AlertDialog builder=new AlertDialog.Builder(this).create();
        builder.setTitle("请选择赞助方式");
        builder.setMessage("1.微信赞助,2.观看广告赞助作者");
       builder.setButton("微信赞助",new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface p1, int p2)
                {
                     Intent i =new Intent(ctx,wechat.class);
                    startActivity(i);
                }
       });
       builder.setButton2("观看广告",new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface p1, int p2) 
                {
                    Toast.makeText(ctx,"对接yomob广告联盟或者其他广告联盟",Toast.LENGTH_SHORT).show();
                }
       });
        builder.show();
		Toast.makeText(ctx,"对接yomob广告联盟或者其他广告联盟",Toast.LENGTH_SHORT).show();
	}
	//跳转到每日一句
	public void openWeb(View v)
	{
		Uri uri=Uri.parse("http://everydayonesentence.biu8.top");
		Intent i=new Intent(Intent.ACTION_VIEW,uri);
		startActivity(i);
	}
    //自动点击 对接tgsdk
    public void auto(View v)
    {
       /* TGSDK.init(MainActivity.this,appid,null);
        TGSDK.preload(MainActivity.this);
        TGSDK.showTestView(sceneid);*/
    }
    //展示更多游戏
	public void showothergames(View v)
	{
        Intent i=new Intent(ctx,MoreGame.class);
        startActivity(i);
		//Toast.makeText(ctx,"功能没有做",Toast.LENGTH_LONG).show();
	}

    //保存进度
    public void save(View v)
    {
   //     File file = new File("/sdcard/test.txt");
           String result= new Game().save(adapter);
           len_T.setText(result);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        
        //File file = new File("/sdcard/Download/test.txt");
  /*      try{
        Game.save(stn, file);
        }catch(IOException e)
        {}*/
        Toast.makeText(ctx, "如果不小心退出，请按图片重新进入主界面", Toast.LENGTH_LONG).show();
    }
    
	//联系作者
	public void contect(View v)
	{
		/*AlertDialog builder=new AlertDialog.Builder(this).create();
		builder.setMessage("请加qq2439905184");
		builder.show();*/
		new EasyDialog().show(this,"联系作者","请加qq2439905184");
		
	//EasyDialog tool=new EasyDialog();
	//tool.show(ctx,"请加qq2439905184");
	}
	public void clickit(View v)
	{
		progress+=5;
		bar.setProgress(progress);
		showprogress.setText(progress+"/"+"100");
		//todo 添加一个每次点击按钮时的音效 留住玩家
		//数组长度
		int size=stn.size();
		
		if(progress>=100)
		{
			
			progress=0;
			bar.setProgress(progress);
			score+=5;
			scoreT.setText("积分:"+score);
			int len=sentences.length;
			//todo 添加一个进度满了的胜利音效 留住玩家
			if(size>0)
			{
				String toadd=stn.get(iindex);
				adapter.add(toadd);
				stn.remove(iindex);
			}
			randView.setText("当前数组长度"+size);
			int count=adapter.getCount();
			len_T.setText("adapter长度"+count);
			unlockT.setText("解锁句子个数"+count+"/"+"20");
			//Toast.makeText(ctx,"句子数组length"+len,1000).show();
			}	
			}
	}
	/*废弃代码*/
		/*	double index=Math.random()*2;
			
			double floor_index=Math.floor(index);
			randView.setText("随机值:"+index+"\n"+"处理后"+(int)floor_index);
			int result=(int)floor_index;
			//满足条件后生成的句子
			String gen_stn=sentences[result];
			Toast.makeText(getApplicationContext(),gen_stn,Toast.LENGTH_LONG).show();
			 tmptext=new TextView(getApplicationContext());
			tmptext.setText(gen_stn);
		//	adapter.add(gen_stn);*/				
