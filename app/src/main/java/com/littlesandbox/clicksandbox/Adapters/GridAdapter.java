package com.littlesandbox.clicksandbox.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.littlesandbox.clicksandbox.R;

import java.util.ArrayList;
import java.util.Map;

public class GridAdapter extends BaseAdapter
{
    private int count;
    private ArrayList<String> game_name;
    private ArrayList<Integer> game_img;
    private ArrayList<String> game_url;
    private Context ctx;
    //布局模板view
    private View v;
    //添加数据，每一张图片对应一个text和一个url
    public GridAdapter(Context p_ctx,String[] data)
    {
        this.ctx = p_ctx;
        game_name = new ArrayList<String>();
        game_img = new ArrayList<Integer>();
        game_url = new ArrayList<String>();
        for(int i=0;i<data.length;i++)
        {
            game_name.add(data[i]);
        }

    }
    public void set_img(int[] img)
    {
        for(int i=0;i<img.length;i++)
        {
            game_img.add(img[i]);
        }
    }
    public void set_url(String[] url)
    {
        for(int i=0;i<url.length;i++)
        {
            game_url.add(url[i]);
        }
    }
    @Override
    public int getCount() {
        return game_name.size();
    }

    @Override
    public Object getItem(int i) {
        return game_name.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    //由安卓系统自动执行，获取布局并循环设置属性
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.d("inflate","index"+i);
        LayoutInflater inflater = LayoutInflater.from(ctx);
        v = inflater.inflate(R.layout.item,null);
        TextView textView =  v.findViewById(R.id.list_text);
        textView.setText(game_name.get(i));
        ImageView imageView =  v.findViewById(R.id.list_img);
        imageView.setImageResource(game_img.get(i));
        return v;
    }
}
