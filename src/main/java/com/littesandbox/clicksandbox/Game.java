package com.littesandbox.clicksandbox;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.ArrayList;
import android.os.Environment;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Game 
{
	//存档路径
	String savePath="";
	static class saveMode
	{
		Enum inAndroidData;
		Enum inSdcard;
	}
	/*public static void setSaveMode(Enum mode)
	{
		File f=Environment.getDataDirectory();
		String path= f.getPath();
		if(mode.equals(Game.saveMode.inSdcard))
		{
		
		}
		if(mode.equals(saveMode.inAndroidData)
		{
			
		}
	}
	*/
   public static void save(ArrayList data,File file)
   {
	   
	   if(data.size()>0)
	   {
		   for(int i=0;i<data.size()-1;i++)
		   {
			   //每个元素
			  String tmp= (String) data.get(i);
			 //数组中每个元素存一行
			   try {
				   FileWriter writer=new FileWriter(file);
				   
			   } catch (IOException e) {
				   e.printStackTrace();
			   }
			   //  write_line(tmp);
		   }
	   }
	  // FileWriter writer=new FileWriter();
	  // writer.write();
   }
   public static ArrayList<String> load(File file) throws IOException
   {
	   ArrayList<String> currentStnArray=new ArrayList<String>();
	   int value = 0;
	   FileReader reader = null;
	   try {
		 reader=new FileReader(file);
	   } catch (FileNotFoundException e) 
	  {
		  e.printStackTrace();
	  }
	   BufferedReader buffer=new BufferedReader(reader);
	   int tmp;
	   String finalStr=new String();
	   while((tmp=buffer.read())!=-1)
	   {
		   char tmpChar=(char)tmp;
		 	Character ch=new Character(tmpChar);
			String str=ch.toString();
			finalStr.concat(str);
	   }
	   String[] splitStr=finalStr.split(",");
	   for(int i=0;i<splitStr.length;i++)
	   {
		   currentStnArray.add(splitStr[i]);
	   }
 	   return currentStnArray;
   }
	  // FileReader reader=new FileReader();
	   //reader.read();
	//   FileReader reader=new FileReader();
	//   int Lines=reader.getline();
	   /*for(int i=0;i<Lines;i++)
	   {
		   //每个元素读取一行
		  // FileReader reade=new FileReader();
	//	   String tmp=reader.readline(); 
		//  	currentStnArray.add(tmp);
	   }
	  return currentStnArray;*/
   
   
}
