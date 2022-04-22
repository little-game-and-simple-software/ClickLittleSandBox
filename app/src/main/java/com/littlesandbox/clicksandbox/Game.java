package com.littlesandbox.clicksandbox;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.content.Context;
import android.util.Log;
import android.widget.Adapter;
import android.app.Activity;
import android.widget.Toast;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.littlesandbox.clicksandbox.utils.EasyFile;

//存档读档
public class Game 
{
    //保存成就 由于不会拼接json 所以分文件存储
    public static void save_achivement(Context ctx, AchivementStruct data, int id)
    {
        File filesdir =  ctx.getFilesDir();
        File achive_file = new File(filesdir.getPath() + "/achivement" + String.valueOf(id) + ".json");
        Gson gson = new Gson();
        String json_1 = gson.toJson(data);
        try {
            FileWriter fw = new FileWriter(achive_file);
            fw.write(json_1);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //读取 收集了几个句子
    public static int load_collect_senten_count(Context ctx) throws IOException
    {
        File filesdir =  ctx.getFilesDir();
        File achive_file = new File(filesdir.getPath() + "/sentence.txt");
        FileReader fileReader = new FileReader(achive_file);
        int result = fileReader.read();
        return result;
    }
    //保存 收集了几个句子
    public static void save_collect_senten_count(Context ctx,int counts)
    {
        File filesdir =  ctx.getFilesDir();
        File achive_file = new File(filesdir.getPath() + "/sentence.txt");
        try {
            FileWriter fileWriter = new FileWriter(achive_file);
            fileWriter.write(counts);
            fileWriter.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public static AchivementStruct load_achivement(Context ctx, int id)
    {
        Gson gson = new Gson();
        AchivementStruct jsonObjData = null;
        File filesdir =  ctx.getFilesDir();
        File achive_file = new File(filesdir.getPath() + "/achivement" + String.valueOf(id) + ".json");
        if(achive_file.exists())
        {
            EasyFile easyFile = new EasyFile(achive_file);
            String loaded_json = null;
            try {
                 loaded_json = easyFile.readAll();
            } catch (IOException e) {
                e.printStackTrace();
            }
            jsonObjData = gson.fromJson(loaded_json, AchivementStruct.class);
        }
        return jsonObjData;
    }
    public void save(Adapter data,Activity a) throws IOException
    { 
        FileWriter write;
        String result="";
        File dir=a.getFilesDir();
        String path=dir.getPath()+"/test.txt";
        File towrite=new File(path);
        Toast.makeText(a,towrite.toString(),Toast.LENGTH_SHORT).show();
        write = new FileWriter(towrite);
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
	   ArrayList<String> currentStnArray = new ArrayList<String>();
       File dir = a.getFilesDir();
       String path = dir.getPath()+"/"+filename;
       File toread = new File(path);
       FileReader fileReader = new FileReader(toread);
       BufferedReader reader = new BufferedReader(fileReader);
       String tmp_line;
       while((tmp_line=reader.readLine()) != null)
       {
           adapter.add(tmp_line);
        //   stn.add(tmp_line);
       }
       //获得最后一个值 原始数组
     String t =  (String) adapter.getItem(adapter.getCount()-1);
     //获取最后一个值在原始数组的位置
     for(int i =0;i < sentence.length;i++)
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

