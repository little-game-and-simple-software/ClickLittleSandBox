package com.littesandbox.clicksandbox;
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;

public class adActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		initYomob();
	//	Tgsdk.showad();
		startGame();
	}

	private void startGame()
	{
		Intent i=new Intent(getApplicationContext(),MainActivity.class);
		startActivity(i);
	}

	private void initYomob() 
	{
	//	Tgsdk.init();	
	}
	
}
