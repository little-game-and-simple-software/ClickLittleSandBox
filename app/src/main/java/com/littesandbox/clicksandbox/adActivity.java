package com.littesandbox.clicksandbox;
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;

import com.soulgame.sgsdk.tgsdklib.TGSDK;

public class adActivity extends Activity
{
	String appid="";
	String sceneid="";
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
		//TGSDK.initialize(this);


	}
	
}
