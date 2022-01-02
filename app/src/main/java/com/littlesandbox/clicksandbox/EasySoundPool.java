package com.littlesandbox.clicksandbox;
import java.io.File;
import android.media.SoundPool;
import android.content.Context;

public class EasySoundPool {
	int StreamId;
	//toddo 写一些通用封装代码
	String win="win";
	String button="button";
	File win_sound;
	File button_sound;
	//可复用
	SoundPool pool;
	public void init(int maxStreams,int streamType,int srcOuality) 
	{
		win_sound=new File("");
		button_sound=new File("");
		if(android.os.Build.VERSION.SDK_INT>=21)
		{
			pool=new SoundPool.Builder().setMaxStreams(1).build();
		}
		else{
			pool=new SoundPool(maxStreams, streamType,srcOuality) ;
		}
		 
	}
	//通用方法
	public void common_play(Context ctx,int resourceId)
	{
		//'最后一个参数是优先级
		int sound_id=pool.load(ctx,resourceId,0);
		StreamId=pool.play(sound_id,1.0f,1.0f,1,0,1.0f);
	}
	public void pause()
	{
		pool.pause(StreamId);
	}
	
	public void stop()
	{
		pool.stop(StreamId);
		pool.release();
	}
	
	public void playSound(Context ctx,String sound) 
	{
		if (sound.equals(win)) 
		{
		/*int sound_id=pool.load(ctx,R.raw.win);
		pool.play(sound_id,1.0f,1.0f,1,0,1.0f);j*/
		}
		if(sound.equals(button))
		{
/*int sound_id=pool.load(ctx,R.raw.button);
		
			pool.play(sound_id,1.0f,1.0f,1,0,1.0f);*/
		}
	}
}
