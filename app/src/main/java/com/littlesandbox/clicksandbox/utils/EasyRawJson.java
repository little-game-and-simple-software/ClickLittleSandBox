package com.littlesandbox.clicksandbox.utils;

import android.content.Context;

import com.littlesandbox.clicksandbox.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class EasyRawJson
{
    public static String getJson(Context ctx)
    {
        InputStream is = ctx.getResources().openRawResource(R.raw.achivements);//把文件转换为输入流
        StringBuffer response = new StringBuffer();				   //创建StringBuffer实例
        BufferedReader br = new BufferedReader(new InputStreamReader(is));	//根据is创建缓冲字符输入流
        String s = null;							//创建s变量
        try {							//try语句捕获异常
            while ((s = br.readLine()) != null) {		//把这一行的值赋值给变量s，并判断是否有值
                response.append(s);						//把值添加进StringBuffer
                response.append("\n");					//再添加一个换行符
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block			//catch异常处理
            e.printStackTrace();		//得到错误的实例， 调用方法在命令行打印程序出错的位置及原因
        } finally {				//finally try语句大多数情况下都会执行的代码块
            try {
                if (is != null) {		//如果文件输入流不为空
                    is.close();			//调用close函数关掉输入流
                }
                if (br != null) {		//如果缓冲字符输入流不为空
                    br.close();			//调用close函数关掉缓冲字符输入流
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return response.toString();
    }
}
