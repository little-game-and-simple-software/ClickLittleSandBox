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

public class MainActivity extends Activity
{
	//句子索引
	int iindex=0;
     int progress=0;
	 ProgressBar bar;
	 TextView randView;
	 TextView unlocked_stn;
	 ListView list;
	 String sentences[]={"靠天靠地不如靠自己","是金子总会发光"};
	 TextView tmptext;
	 ArrayList<String> stn=new ArrayList<String>();
	 Context ctx;
	 TextView len_T;
	 //显示进度
	 TextView showprogress;
	ArrayAdapter<String> adapter;
	Bgm bgm;
	TextView scoreT;
	int score=0;
	//解锁的句子个数
	TextView unlockT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar=findViewById(R.id.bar);
		showprogress=findViewById(R.id.progress);
		ctx=getApplicationContext();
		scoreT=findViewById(R.id.score);
		unlocked_stn=findViewById(R.id.unlock_stn);
		unlockT=findViewById(R.id.unlocked);
		playBgm();
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
		Toast.makeText(ctx,"对接yomob广告联盟或者其他广告联盟",Toast.LENGTH_SHORT).show();
	}
	//跳转到每日一句
	public void openWeb(View v)
	{
		Uri uri=Uri.parse("http://everydayonesentence.biu8.top");
		Intent i=new Intent(Intent.ACTION_VIEW,uri);
		startActivity(i);
	}
	public void showothergames(View v)
	{
		Toast.makeText(ctx,"功能没有做",Toast.LENGTH_LONG).show();
	}
	public void click(View v)
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
