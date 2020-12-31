package com.littesandbox.clicksandbox;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.ArrayList;
import android.os.Environment;

public class Game 
{
   public static void save(ArrayList data)
   {
	   File f=Environment.getDataDirectory();
	  String path= f.getPath();
	   if(data.size()>0)
	   {
		   for(int i=0;i<data.size()-1;i++)
		   {
			   //每个元素
			  String tmp= (String) data.get(i);
			 //数组中每个元素存一行
	//		 FileWriter writer=new FileWriter();
			//  write_line(tmp);
		   }
	   }
	   
	   File file=new File("");
	  // FileWriter writer=new FileWriter();
	  // writer.write();
   }
   public static ArrayList<String> load()
   {
	   ArrayList<String> currentStnArray=new ArrayList<String>();
	   int value = 0;
   return null;
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
