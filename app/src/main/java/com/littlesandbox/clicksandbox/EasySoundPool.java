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
	public static boolean click_sound_load_status = false ;
	private String tag;
	public EasySoundPool(int maxStreams, AudioAttributes audioAttributes)
	{
		tag = this.getClass().toString();
		if(android.os.Build.VERSION.SDK_INT >= 21)
		{
			SoundPool.Builder spb = new SoundPool.Builder();
			spb.setMaxStreams(10);
			spb.setAudioAttributes(null);
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
