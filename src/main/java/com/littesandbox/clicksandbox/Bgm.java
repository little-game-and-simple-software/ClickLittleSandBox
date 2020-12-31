package com.littesandbox.clicksandbox;
import java.io.File;
import android.media.MediaPlayer;

public class Bgm 
{
	File bgmfile;
	MediaPlayer bgm;
	public void init()
	{
		bgmfile=new File("");
		 bgm=new MediaPlayer();
		 bgm.setLooping(true);
		//bgm.setsrc
	}
     public void play()
	 {
		 bgm.start();
	 }
	
	
}
