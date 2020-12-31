package com.littesandbox.clicksandbox;
import java.io.File;
import android.media.SoundPool;

public class SoundEffect {
	String win="win";
	String button="button";
	File win_sound;
	File button_sound;
	//可复用
	
	SoundPool pool;
	
	public void init() {
		win_sound=new File("");
		button_sound=new File("");
		 pool=new SoundPool();
	}
	
	public void playSound(String sound) 
	{
		if (sound.equals(win)) 
		{
		pool.play(soundId);
		}
		if(sound.equals(button))
		{
			pool.play(soundId);
		}
	}
}
