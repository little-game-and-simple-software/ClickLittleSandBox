package com.littesandbox.clicksandbox;
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Toast;

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
		//TGSDK.initialize(this,appid,sceneid,null);
		boolean could=TGSDK.couldShowAd(sceneid);
		if(could)
		{
			TGSDK.showAd(this,sceneid);
			Toast.makeText(this,"可以展示广告",Toast.LENGTH_LONG).show();
		}
		else
			{
				Toast.makeText(this,"不可以展示广告",Toast.LENGTH_LONG).show();
			}
	}
	
}
