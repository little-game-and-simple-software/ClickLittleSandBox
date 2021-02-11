package com.littesandbox.clicksandbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;

import com.littlesandbox.clicksandbox.R;

public class MoreGame extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moregame);
    //  WebView web=  (WebView) $.select_id(this,R.id.web);
    //  web.loadUrl("https://indienova.com/u/2439905184");
       
    }
    public void indienova(View v)
    {
        Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse("https://indienova.com/u/2439905184"));
        startActivity(i); 
    }
    public void taptap(View v)
    {
        Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.taptap.com/developer/83988"));
        startActivity(i); 
    }
    //冲击
    public void rush(View v)
    {
        Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.taptap.com/app/189201"));
        startActivity(i); 
    }
    public void fruit(View v)
    {
        Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.taptap.com/app/196602"));
        startActivity(i); 
        }
        public void music(View v)
        {
            Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.taptap.com/app/199683"));
            startActivity(i); 
        }
    
    
    
}
