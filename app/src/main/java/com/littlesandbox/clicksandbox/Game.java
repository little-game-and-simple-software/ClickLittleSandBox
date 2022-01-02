package com.littlesandbox.clicksandbox;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.widget.Adapter;
import android.app.Activity;
import android.widget.Toast;
import android.widget.ArrayAdapter;

//存档读档
public class Game 
{
    public void save(Adapter data,Activity a) throws IOException
    { 
        FileWriter write;
        String result="";
        File dir=a.getFilesDir();
        String path=dir.getPath()+"/test.txt";
        File towrite=new File(path);
        Toast.makeText(a,towrite.toString(),Toast.LENGTH_SHORT).show();
        write=new FileWriter(towrite);
        //writer=new FileWriter(file);
        if(data.getCount()>0) 
        {
            for(int i=0;i<data.getCount();i++)
            {
                //每个元素
                String tmp= (String) data.getItem(i);
                result+=tmp+"\n";
            }
            write.write(result);
        }
        write.close();  
    }
  public  void load(String filename,Activity a,ArrayAdapter adapter,ArrayList<String> stn,String[] sentence) throws FileNotFoundException, IOException 
   {
      // String finalResult="";
	   ArrayList<String> currentStnArray=new ArrayList<String>();
       File dir=a.getFilesDir();
       String path=dir.getPath()+"/"+filename;
        File toread=new File(path);
        FileReader fileReader=new FileReader(toread);
       BufferedReader reader = new BufferedReader(fileReader);
         String tmp_line;
       while((tmp_line=reader.readLine())!=null)
       {
           adapter.add(tmp_line);
        //   stn.add(tmp_line);
       }
       //获得最后一个值 原始数组
     String t=  (String) adapter.getItem(adapter.getCount()-1);
     //获取最后一个值在原始数组的位置
     for(int i=0;i<sentence.length;i++)
     {
         currentStnArray.add(sentence[i]);
     }
   //  int index=currentStnArray.indexOf(t);
     Toast.makeText(a,"最后"+t,Toast.LENGTH_SHORT).show();
   int index=  currentStnArray.indexOf(t);
   Toast.makeText(a,"最后一个句子在原始数组的位置"+index,Toast.LENGTH_SHORT).show();
   //重置数组
   stn.clear();
   for(int ii=index+1;ii<sentence.length;ii++)
   {
       stn.add(sentence[ii]);
   }
     reader.close();

   }
         }

