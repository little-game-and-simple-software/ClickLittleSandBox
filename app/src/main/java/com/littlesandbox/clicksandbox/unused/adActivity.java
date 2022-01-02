package com.littlesandbox.clicksandbox.unused;
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;

import com.littlesandbox.clicksandbox.MainActivity;

//import com.soulgame.sgsdk.tgsdklib.TGSDK;


public class adActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		initYomob();
	//	Tgsdk.showad();
		//startGame();
	}
	private void startGame()
	{
		Intent i=new Intent(getApplicationContext(), MainActivity.class);
		startActivity(i);
	}

	private void initYomob()
	{
		/*TGSDK.setDebugModel(true);
		TGSDK.initialize(this,Global.appid,null);
        TGSDK.preloadAd(this);
		boolean could= TGSDK.couldShowAd(Global.sceneid);
		if(could)
		{
			//TGSDK.showAd(this,Global.sceneid);
			Toast.makeText(this,"可以展示广告",Toast.LENGTH_LONG).show();
		}
		else
			{
				Toast.makeText(this,"不可以展示广告",Toast.LENGTH_LONG).show();
			}
			*/
	}

}
