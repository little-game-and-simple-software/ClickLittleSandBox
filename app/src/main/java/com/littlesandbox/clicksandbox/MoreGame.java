package com.littlesandbox.clicksandbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.net.Uri;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.littlesandbox.clicksandbox.Adapters.GridAdapter;

import java.util.HashMap;
import java.util.Map;

public class MoreGame extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moregame);
    //  WebView web=  (WebView) $.select_id(this,R.id.web);
    //  web.loadUrl("https://indienova.com/u/2439905184");
        GridView gv = findViewById(R.id.grid);
        String view_text_array[] = {"冲击","水果下落消除","音乐键盘手"};
        String urls[] = {"https://www.taptap.com/app/189201","https://www.taptap.com/app/196602","https://www.taptap.com/app/199683"};
        int imgs[] = {R.drawable.rush,R.drawable.fruit,R.drawable.music};
        //ListAdapter ap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,view_text_array);
        GridAdapter gp = new GridAdapter(MoreGame.this,view_text_array);
        gp.set_img(imgs);
      //  gp.set_url(urls);
        gv.setAdapter(gp);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int positon, long l)
            {
                if(positon==0)
                {
                    Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.taptap.com/app/189201"));
                    startActivity(i);
                }
                if(positon==1)
                {
                    Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.taptap.com/app/196602"));
                    startActivity(i);
                }
                if(positon==2)
                {
                    Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.taptap.com/app/199683"));
                    startActivity(i);
                }
                //Toast.makeText(MoreGame.this,"位置:"+positon,Toast.LENGTH_SHORT).show();
            }
        });
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
    /*public void rush(View v)
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
    */
    
    
}
