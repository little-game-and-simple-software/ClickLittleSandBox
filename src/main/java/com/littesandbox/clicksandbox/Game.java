package com.littesandbox.clicksandbox;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.ArrayList;
import android.os.Environment;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedWriter;
import android.widget.Adapter;
import android.app.Activity;
import android.widget.Toast;

public class Game 
{
    //static FileWriter writer;

    public void save(Adapter data,Activity a) throws IOException
    { 
        FileWriter write;
        String result="";
        File dir=a.getFilesDir();
        String path=dir.getPath()+"/test.txt";
        File towrite=new File(path);
        Toast.makeText(a,towrite.toString(),100).show();
        write=new FileWriter(towrite);
        //writer=new FileWriter(file);
        if(data.getCount()>0) 
        {
            for(int i=0;i<data.getCount();i++)
            {
                //每个元素
                String tmp= (String) data.getItem(i);
                result+=tmp+"\n";
                //数组中每个元素存一行
                // writer.write(tmp); 
            }
            write.write(result);
        }
        write.close();  
    }
  public static ArrayList<String> load(File file,Activity a) throws IOException
   {
       String finalResult="";
	   ArrayList<String> currentStnArray=new ArrayList<String>();
       Toast.makeText(a,file.getPath(),1000).show();
	    String path=file.getPath()+"/test.txt";
        File toread=new File(path);
        FileReader fileReader=new FileReader(toread);
       BufferedReader reader = new BufferedReader(fileReader);
     int i;

         String tmp_line;
       while((tmp_line=reader.readLine())!=null)
       {
           currentStnArray.add(tmp_line);
       }
  //     BufferedWriter n;
    
   //处理
        Toast.makeText(a,finalResult,1000).show();
       return currentStnArray;
   }
      /* for(int index=0;i<finalResult.length()-1;index++)
       {
           //总字符串转每个字符串，存到arraylist
         Character c=  finalResult.charAt(index);
         String  tmp=c.toString();
         currentStnArray.add(tmp);
           
       }*/
        //currentStnArray.add();
       //读取
       /*  while((i=reader.read())!=-1)
        {
        Character tmp_char= (char)i;
        String tmp= tmp_char.toString();
        finalResult+=tmp;
        currentStnArray.add(tmp);
        }*/
 
         }

