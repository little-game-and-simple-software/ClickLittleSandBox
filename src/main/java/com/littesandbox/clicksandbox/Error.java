package com.littesandbox.clicksandbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.app.*;
import android.content.Intent;
import java.io.File;
import android.widget.Toast;
import android.widget.LinearLayout;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import android.widget.TextView;
import java.util.ArrayList;
public class Error extends Activity {
   LinearLayout  root;
   TextView gameDataView;
   TextView arrayView;
   ArrayList<String> s=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.Error);
     root=   (LinearLayout) $.select_id(this,R.id.root);
     gameDataView=(TextView) $.select_id(this,R.id.gameData);
    arrayView= (TextView) $.select_id(this,R.id.arraylist);
    }
    public void reset(View v)
    {
        String dir=getFilesDir().getPath();
        File f=new File(dir+"/test.txt");
        boolean result= f.delete();
        Toast.makeText(this,"删除状态"+result,1000).show();
    }
   
    public void enter(View v)
    {
        startActivity(new Intent(this, MainActivity.class));
    }
    //获得文件信息
    public void get(View v) throws IOException
    {
        String  dir=getFilesDir().getPath();
        File gameDataFile=new File(dir+"/test.txt");
        FileReader fileReader=new FileReader(gameDataFile);
        BufferedReader reader = new BufferedReader(fileReader);
        String tmp_line;
        String result="";
        while((tmp_line=reader.readLine())!=null)
        {
            result+=tmp_line;
            s.add(tmp_line);
        }
        gameDataView.setText(result);
      //  s.add(result);
        arrayView.setText(s.toString()+"arrayLsit长度"+s.size());
    }
}
