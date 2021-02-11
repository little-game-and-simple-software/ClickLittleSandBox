package com.littesandbox.clicksandbox;
import java.io.File;
import android.media.MediaPlayer;
import android.content.Context;

import com.littlesandbox.clicksandbox.R;

public class Bgm 
{
	File bgmfile;
	MediaPlayer player;
	public void init(Context ctx)
	{
		//bgmfile=new File("");
		player=MediaPlayer.create(ctx, R.raw.greippi);
		// bgm.setLooping(true);
	}
	public void pause()
	{
		player.pause();
	}
     public void play()
	 {
		 player.start();
	 }
	public void stop()
	{
		player.stop();
	}
	
}
