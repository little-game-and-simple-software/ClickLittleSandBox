package com.littlesandbox.clicksandbox.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EasyFile
{
    private File f;
    public EasyFile(String file_path)
    {
        f = new File(file_path);
    }
    public EasyFile(File p_file)
    {
        f = p_file;
    }
    public String readAll() throws FileNotFoundException, IOException
    {
        FileReader fr= new FileReader(f);
        BufferedReader br= new BufferedReader(fr);
        String result="";
        String line;
        while ((line=br.readLine())!=null)
        {
            result+=line;
        }
        return result;
    }
}
