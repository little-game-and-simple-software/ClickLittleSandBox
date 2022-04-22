package com.littlesandbox.clicksandbox;
import java.io.File;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.content.Context;
import android.util.Log;

import com.littlesandbox.clicksandbox.Listeners.EasySoundPoolListener;

public class EasySoundPool
{
	int stream_id;
	//toddo 写一些通用封装代码
	String win="win";
	String button="button";
	File win_sound;
	File click_sound_file;
	//可复用
	SoundPool pool;
	public static int click_stream_id = 1;
	public static int get_achivemanet_id = 2;//获得成就的音效
	public static boolean click_sound_load_status = false ;
	private String tag;
	public EasySoundPool(int maxStreams)
	{
		tag = this.getClass().toString();
		if(android.os.Build.VERSION.SDK_INT >= 21)
		{
			SoundPool.Builder spb = new SoundPool.Builder();
			spb.setMaxStreams(10);
			AudioAttributes.Builder builder = new AudioAttributes.Builder();
			builder.setUsage(AudioAttributes.USAGE_GAME);
			builder.setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION);//当内容类型是用于伴随用户操作（如表示按键单击的哔哔声或声音效果）或事件（如游戏中收到的奖励的声音类型）时要使用的内容类型值。这些声音大多是合成的或短的Foley声音。
			spb.setAudioAttributes(builder.build());
			pool = spb.build();
		}
		pool.setOnLoadCompleteListener(new EasySoundPoolListener());
	}
	/*public void init(int maxStreams,int streamType,int srcOuality)
	{
		win_sound=new File("");

		click_sound_file =new File("");
		if(android.os.Build.VERSION.SDK_INT >= 21)
		{
			SoundPool.Builder spb = new SoundPool.Builder();
			spb.setMaxStreams(10);
			spb.setAudioAttributes(null);
			pool = spb.build();
		}
		else{
			pool=new SoundPool(maxStreams, streamType,srcOuality) ;
		}

	}*/
	//priority sound_quality音质
	public void load(Context ctx, int resId, int sound_quality)
	{
		pool.load(ctx, R.raw.qubodup_crash, sound_quality);
	}
	public void direct_load(Context ctx, int resId, int sound_quality)
	{
		pool.load(ctx,resId,sound_quality);
	}
	//通用方法
	public void play(int soundId)
	{
		//'最后一个参数是优先级
		if(soundId == click_stream_id)
		{
			if(click_sound_load_status == true)
			{
				pool.play(soundId, 1f,1f,0,0,1f);
			}
			else
				{
					Log.e(tag,soundId + "未加载成功！");
				}
		}
	}
	public void direct_play(int soundId)
	{
		pool.play(soundId,1f,1f,0,0,1f);
	}
	public void pause(int soundId)
	{
		pool.pause(soundId);
	}
	
	public void stop(int soundId)
	{
		pool.stop(soundId);
		pool.release();
	}

}
