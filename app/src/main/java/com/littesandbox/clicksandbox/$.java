package com.littesandbox.clicksandbox;
import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
public class $ {
    //仿jquery
    public static Object select_id(Activity activity,int id)
	{
	Object obj=	activity.findViewById(id);
		return obj;
	}
	public static void set_val(Object obj,Object val)
	{
		
	}
	//toast
    public static void tip(Context ctx,Object obj)
	{
		String str=obj.toString();
		Toast.makeText(ctx,str,Toast.LENGTH_SHORT).show();
	}
    
}

